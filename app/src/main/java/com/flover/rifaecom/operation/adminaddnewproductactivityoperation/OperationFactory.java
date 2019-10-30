package com.flover.rifaecom.operation.adminaddnewproductactivityoperation;

import android.app.Activity;

import com.flover.rifaecom.R;

public class OperationFactory {
    private Activity adminActivity;

    public OperationFactory(Activity adminActivity) {
        this.adminActivity = adminActivity;
    }

    public Operation getInstance(int anyButtonId){
        Operation anyOperationInstance = null;
        if (anyButtonId== R.id.tShirt){
            anyOperationInstance = new TShirtOperation(adminActivity);
        }else if (anyButtonId==R.id.sweather){
            anyOperationInstance = new SweaterOperation(adminActivity);
        }else if (anyButtonId==R.id.sportTShirt){
            anyOperationInstance = new SportTShirtOperation(adminActivity);
        }else if (anyButtonId==R.id.femaleDress){
            anyOperationInstance = new FemaleDressOperation(adminActivity);
        }else if (anyButtonId==R.id.glass){
            anyOperationInstance = new GlassOperation(adminActivity);
        }else if (anyButtonId==R.id.purseAndBag){
            anyOperationInstance = new PurseAndBagOperation(adminActivity);
        }else if (anyButtonId==R.id.shoe){
            anyOperationInstance = new ShoeOperation(adminActivity);
        }else if (anyButtonId==R.id.lapTop){
            anyOperationInstance = new LapTopOperation(adminActivity);
        }else if (anyButtonId==R.id.headPhone){
            anyOperationInstance = new HeadPhoneOperation(adminActivity);
        }else if (anyButtonId==R.id.watch){
            anyOperationInstance = new WatchOperation(adminActivity);
        }else if (anyButtonId==R.id.mobile){
            anyOperationInstance = new MobileOperation(adminActivity);
        }else if (anyButtonId==R.id.hat){
            anyOperationInstance = new HatOperation(adminActivity);
        }

        return anyOperationInstance;
    }
}
