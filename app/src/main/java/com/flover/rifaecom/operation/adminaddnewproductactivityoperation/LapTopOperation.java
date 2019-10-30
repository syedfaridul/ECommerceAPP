package com.flover.rifaecom.operation.adminaddnewproductactivityoperation;

import android.app.Activity;
import android.content.Intent;

import com.flover.rifaecom.admin.AdminAddNewCategoryActivity;

public class LapTopOperation implements Operation {
    private Activity adminActivity;
    private String category = "Hat";
    private String categoryRef = "category";


    public LapTopOperation(Activity adminActivity) {
        this.adminActivity = adminActivity;
    }

    @Override
    public void perform() {
        Intent addNewCategoryIntent = new Intent(adminActivity, AdminAddNewCategoryActivity.class);
        addNewCategoryIntent.putExtra(categoryRef, category);
        adminActivity.startActivity(addNewCategoryIntent);
    }
}
