package com.flover.rifaecom.operation.adminaddnewcategoryactivityoperation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.flover.rifaecom.R;
import com.flover.rifaecom.repository.FirebaseStorageRepository;
import com.flover.rifaecom.repository.Repository;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class AdminAddNewCategoryActivityAddNewCategoryButtonOperation extends AdminAddNewCategoryActivityData implements AdminAddNewCategoryActivityOperation, Observer {
    private EditText productDescription;
    private EditText productPrice;
    private EditText productName;

    private String productDescriptionString;
    private String productPriceString;
    private String productNameString;

    private static Uri productImageUriC;
    private ProgressDialog loadingBar;
    private String productRandomKey;

    private Map dataSet;

    private Repository firebaseStorageRepository;
    private StorageReference productImagesRef;
    private String downloadImageUrl;


    public AdminAddNewCategoryActivityAddNewCategoryButtonOperation(Activity adminAddNewCategoryActivity) {
        this.adminAddNewCategoryActivity = adminAddNewCategoryActivity;

        productName = adminAddNewCategoryActivity.findViewById(R.id.productName);
        productDescription = adminAddNewCategoryActivity.findViewById(R.id.productDescription);
        productPrice = adminAddNewCategoryActivity.findViewById(R.id.productPrice);
        loadingBar = new ProgressDialog(adminAddNewCategoryActivity);

        productImagesRef = FirebaseStorage.getInstance().getReference().child("ProductImages");
        dataSet = new HashMap();
    }

    @Override
    public void perform() {
        productDescriptionString = productDescription.getText().toString();
        productNameString = productName.getText().toString();
        productPriceString = productPrice.getText().toString();

        if (productImageUriC==null){
            Toast.makeText(adminAddNewCategoryActivity, "Product image is mandatory!", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(productNameString)){
            Toast.makeText(adminAddNewCategoryActivity, "Product name is mandatory!", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(productDescriptionString)){
            Toast.makeText(adminAddNewCategoryActivity, "Product description is mandatory!", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(productPriceString)){
            Toast.makeText(adminAddNewCategoryActivity, "Product price is mandatory!", Toast.LENGTH_SHORT).show();
        }else {
            loadingBar.setTitle("Please wait!");
            loadingBar.setMessage("Please wait while we are adding the new product!");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyy");
            String saveCurrentDate = currentDate.format(calendar.getTime());

            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
            String saveCurrentTime = currentTime.format(calendar.getTime());

            productRandomKey = saveCurrentDate + saveCurrentTime;

            final StorageReference filePath = productImagesRef.child(productRandomKey);


            final UploadTask uploadTask = filePath.putFile(productImageUriC);

            dataSet.put("productRootReference", "ProductImages");
            dataSet.put("productRandomKey", productRandomKey);
            dataSet.put("productImageUri", productImageUriC);

            firebaseStorageRepository = new FirebaseStorageRepository();
            ((FirebaseStorageRepository)firebaseStorageRepository).addObserver(this);


            firebaseStorageRepository.updateData(dataSet);
        }
    }


    // Want to find alternative way
    public static void performAfterGotTheUri(Uri productImageUri){
        productImageUriC = productImageUri;
    }

    @Override
    public void update(Observable observable, Object o) {
        Map allFlags = firebaseStorageRepository.returnAllFlags();
        loadingBar.dismiss();
    }
}
