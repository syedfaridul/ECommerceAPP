package com.flover.rifaecom.operation.addnewcategoryoperation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.flover.rifaecom.R;
import com.flover.rifaecom.operation.Operation;
import com.flover.rifaecom.repository.FirebaseDataBaseRepository;
import com.flover.rifaecom.repository.FirebaseStorageRepository;
import com.flover.rifaecom.repository.Repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNewCategoryButtonOperation extends Data implements Operation, Observer {
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

    private String downloadImageUrl;

    private String productNameFlag = "productName";
    private String productDescriptionFlag = "productDescription";
    private String productPriceFlag = "productPrice";

    private String productCategoryFlag = "productCategory";
    private String productRootRef = "PRODUCTS";

    private Repository productDataRepository;



    public AddNewCategoryButtonOperation(Activity adminAddNewCategoryActivity) {
        this.adminAddNewCategoryActivity = adminAddNewCategoryActivity;

        productName = adminAddNewCategoryActivity.findViewById(R.id.productName);
        productDescription = adminAddNewCategoryActivity.findViewById(R.id.productDescription);
        productPrice = adminAddNewCategoryActivity.findViewById(R.id.productPrice);
        loadingBar = new ProgressDialog(adminAddNewCategoryActivity);

        productCategory = adminAddNewCategoryActivity.getIntent().getExtras().get(extraRoof).toString();

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
            String special_charactersRegX = "[.|#|$|\\[|\\]]";
            Pattern special_characterPattern = Pattern.compile(special_charactersRegX);
            Matcher special_characterMatcher = special_characterPattern.matcher(productRandomKey);

            productRandomKey = special_characterMatcher.replaceAll("");


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
        if (observable instanceof FirebaseStorageRepository){
            Map allFlags = firebaseStorageRepository.returnAllFlags();

            downloadImageUrl = allFlags.get("downloadFileUrl").toString();
            if ((Boolean) allFlags.get("isUploadSuccess")){
                Toast.makeText(adminAddNewCategoryActivity, "Product image uploaded successfully!", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(adminAddNewCategoryActivity, "Error occurred while uploading!", Toast.LENGTH_SHORT).show();
            }

            Map allDatabaseData = new HashMap();

            allDatabaseData.put(productCategoryFlag, productCategory);
            allDatabaseData.put(productNameFlag, productNameString);
            allDatabaseData.put(productDescriptionFlag, productDescriptionString);
            allDatabaseData.put(productPriceFlag, productPriceString);
            allDatabaseData.put("productImageDownloadUrl", downloadImageUrl);

            productDataRepository = new FirebaseDataBaseRepository(productRootRef, productRandomKey);
            ((FirebaseDataBaseRepository)productDataRepository).addObserver(this);
            productDataRepository.updateData(allDatabaseData);
        }else {
            Map allFlags = productDataRepository.returnAllFlags();
            if ((Boolean) allFlags.get("isUpdateDataTaskComplete")){
                Toast.makeText(adminAddNewCategoryActivity, "Your product is uploaded successfully!", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(adminAddNewCategoryActivity, "Error occurred while uploading!", Toast.LENGTH_SHORT).show();
            }
        }

        loadingBar.dismiss();
        Thread destroyActivityThread = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                adminAddNewCategoryActivity.finish();
            }
        };

        destroyActivityThread.start();
    }
}
