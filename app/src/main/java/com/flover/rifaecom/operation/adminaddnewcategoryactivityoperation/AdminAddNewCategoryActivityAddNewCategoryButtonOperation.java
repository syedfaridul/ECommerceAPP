package com.flover.rifaecom.operation.adminaddnewcategoryactivityoperation;

import android.app.Activity;
import android.widget.EditText;

import com.flover.rifaecom.R;

public class AdminAddNewCategoryActivityAddNewCategoryButtonOperation extends AdminAddNewCategoryActivityData implements AdminAddNewCategoryActivityOperation{
    private EditText productDescription;
    private EditText productPrice;
    private EditText productName;

    private String productDescriptionString;
    private String productPriceString;
    private String productNameString;


    public AdminAddNewCategoryActivityAddNewCategoryButtonOperation(Activity adminAddNewCategoryActivity) {
        this.adminAddNewCategoryActivity = adminAddNewCategoryActivity;

        productName = adminAddNewCategoryActivity.findViewById(R.id.productName);
        productDescription = adminAddNewCategoryActivity.findViewById(R.id.productDescription);
        productPrice = adminAddNewCategoryActivity.findViewById(R.id.productPrice);
    }

    @Override
    public void perform() {
        productPriceString = productDescription.getText().toString();
        productNameString = productName.getText().toString();
        productPriceString = productPrice.getText().toString();
    }
}
