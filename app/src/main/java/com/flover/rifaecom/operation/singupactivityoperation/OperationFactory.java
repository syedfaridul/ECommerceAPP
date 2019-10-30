package com.flover.rifaecom.operation.singupactivityoperation;


import android.app.Activity;

import com.flover.rifaecom.R;
import com.flover.rifaecom.operation.Operation;

public class OperationFactory {
    private boolean isAdmin = false;
    private Activity signUpActivity;

    public OperationFactory(Activity signUpActivity) {
        this.signUpActivity = signUpActivity;
    }

    public Operation getInstance(int anyButtonId){
        Operation anyOperationInstance = null;
        if (R.id.createAccountButton==anyButtonId){
            anyOperationInstance = new CreateButtonOperation(signUpActivity, isAdmin);
        }else if (anyButtonId==R.id.yesIam){
            isAdmin = true;
            anyOperationInstance = new AdminButtonOperation(signUpActivity);
        }else if (anyButtonId==R.id.noIamNot){
            isAdmin = false;
            anyOperationInstance = new UserButtonOperation(signUpActivity);
        }


        return anyOperationInstance;
    }
}
