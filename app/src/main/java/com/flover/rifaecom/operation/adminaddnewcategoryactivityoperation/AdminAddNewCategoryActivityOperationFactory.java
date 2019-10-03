package com.flover.rifaecom.operation.adminaddnewcategoryactivityoperation;

import android.app.Activity;

import com.flover.rifaecom.R;

public class AdminAddNewCategoryActivityOperationFactory {
    private Activity adminAddNewCategoryActivity;

    public AdminAddNewCategoryActivityOperationFactory(Activity adminAddNewCategoryActivity) {
        this.adminAddNewCategoryActivity = adminAddNewCategoryActivity;
    }

    public AdminAddNewCategoryActivityOperation getInstance(int anyButtonId){
        AdminAddNewCategoryActivityOperation anyOperationInstance = null;

        if (anyButtonId==R.id.selectProductImage){
            anyOperationInstance = new AdminAddNewCategoryActivitySelectProductImageOperation(adminAddNewCategoryActivity);
        }else if (anyButtonId==R.id.addNewCategoryButton){
            anyOperationInstance = new AdminAddNewCategoryActivityAddNewCategoryButtonOperation(adminAddNewCategoryActivity);
        }
        return anyOperationInstance;
    }
}
