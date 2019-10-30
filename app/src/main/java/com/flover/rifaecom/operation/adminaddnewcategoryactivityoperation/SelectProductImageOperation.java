package com.flover.rifaecom.operation.adminaddnewcategoryactivityoperation;

import android.app.Activity;
import android.content.Intent;
import com.flover.rifaecom.operation.Operation;

public class SelectProductImageOperation extends Data implements Operation {

    public SelectProductImageOperation(Activity adminAddNewCategoryActivity) {
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
