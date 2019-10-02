package com.flover.rifaecom.operation.adminaddnewcategoryactivityoperation;

import android.app.Activity;
import android.content.Intent;

public class AdminAddNewCategoryActivitySelectProductImageOperation extends AdminAddNewCategoryActivityData implements AdminAddNewCategoryActivityOperation{

    public AdminAddNewCategoryActivitySelectProductImageOperation(Activity adminAddNewCategoryActivity) {
        this.adminAddNewCategoryActivity = adminAddNewCategoryActivity;
        productCategory = adminAddNewCategoryActivity.getIntent().getExtras().get(extraRoof).toString();
    }

    @Override
    public void perform() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType(imageType);
        adminAddNewCategoryActivity.startActivityForResult(galleryIntent, galleryPick);
    }
}
