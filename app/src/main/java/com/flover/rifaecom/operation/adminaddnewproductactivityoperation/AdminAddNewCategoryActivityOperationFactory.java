package com.flover.rifaecom.operation.adminaddnewproductactivityoperation;

import android.app.Activity;

import com.flover.rifaecom.R;

public class AdminAddNewCategoryActivityOperationFactory {
    private Activity adminActivity;

    public AdminAddNewCategoryActivityOperationFactory(Activity adminActivity) {
        this.adminActivity = adminActivity;
    }

    public AdminAddNewProductActivityOperation getInstance(int anyButtonId){
        AdminAddNewProductActivityOperation anyOperationInstance = null;
        if (anyButtonId== R.id.tShirt){
            anyOperationInstance = new AdminAddNewProductActivityTShirtOperation(adminActivity);
        }else if (anyButtonId==R.id.sweather){
            anyOperationInstance = new AdminAddNewProductActivitySweaterOperation(adminActivity);
        }else if (anyButtonId==R.id.sportTShirt){
            anyOperationInstance = new AdminAddNewProductActivitySportTShirtOperation(adminActivity);
        }else if (anyButtonId==R.id.femaleDress){
            anyOperationInstance = new AdminAddNewProductActivityFemaleDressOperation(adminActivity);
        }else if (anyButtonId==R.id.glass){
            anyOperationInstance = new AdminAddNewProductActivityGlassOperation(adminActivity);
        }else if (anyButtonId==R.id.purseAndBag){
            anyOperationInstance = new AdminAddNewProductActivityPurseAndBagOperation(adminActivity);
        }else if (anyButtonId==R.id.shoe){
            anyOperationInstance = new AdminAddNewProductActivityShoeOperation(adminActivity);
        }else if (anyButtonId==R.id.lapTop){
            anyOperationInstance = new AdminAddNewProductActivityLapTopOperation(adminActivity);
        }else if (anyButtonId==R.id.headPhone){
            anyOperationInstance = new AdminAddNewProductActivityHeadPhoneOperation(adminActivity);
        }else if (anyButtonId==R.id.watch){
            anyOperationInstance = new AdminAddNewProductActivityWatchOperation(adminActivity);
        }else if (anyButtonId==R.id.mobile){
            anyOperationInstance = new AdminAddNewProductActivityMobileOperation(adminActivity);
        }else if (anyButtonId==R.id.hat){
            anyOperationInstance = new AdminAddNewProductActivityHatOperation(adminActivity);
        }

        return anyOperationInstance;
    }
}
