package com.flover.rifaecom.operation.adminaddnewcategoryactivityoperation;

import android.app.Activity;

import com.flover.rifaecom.R;

public class OperationFactory {
    private Activity adminAddNewCategoryActivity;

    public OperationFactory(Activity adminAddNewCategoryActivity) {
        this.adminAddNewCategoryActivity = adminAddNewCategoryActivity;
    }

    public Operation getInstance(int anyButtonId){
        Operation anyOperationInstance = null;

        if (anyButtonId==R.id.selectProductImage){
            anyOperationInstance = new SelectProductImageOperation(adminAddNewCategoryActivity);
        }else if (anyButtonId==R.id.addNewCategoryButton){
            anyOperationInstance = new AddNewCategoryButtonOperation(adminAddNewCategoryActivity);
        }
        return anyOperationInstance;
    }
}
