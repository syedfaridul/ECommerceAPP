package com.flover.rifaecom.operation.adminaddnewcategoryactivityoperation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.flover.rifaecom.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AdminAddNewCategoryActivityAddNewCategoryButtonOperation extends AdminAddNewCategoryActivityData implements AdminAddNewCategoryActivityOperation{
    private EditText productDescription;
    private EditText productPrice;
    private EditText productName;

    private String productDescriptionString;
    private String productPriceString;
    private String productNameString;

    private static Uri productImageUriC;
    private ProgressDialog loadingBar;
    private String productRandomKey;


    public AdminAddNewCategoryActivityAddNewCategoryButtonOperation(Activity adminAddNewCategoryActivity) {
        this.adminAddNewCategoryActivity = adminAddNewCategoryActivity;

        productName = adminAddNewCategoryActivity.findViewById(R.id.productName);
        productDescription = adminAddNewCategoryActivity.findViewById(R.id.productDescription);
        productPrice = adminAddNewCategoryActivity.findViewById(R.id.productPrice);
        loadingBar = new ProgressDialog(adminAddNewCategoryActivity);
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
            String saveCurretTime = currentTime.format(calendar.getTime());

            productRandomKey = saveCurrentDate + saveCurretTime;
        }
    }


    // Want to find alternative way
    public static void performAfterGotTheUri(Uri productImageUri){
        productImageUriC = productImageUri;
    }
}
