package com.flover.rifaecom.operation.singupactivityoperation;


import android.app.Activity;

import com.flover.rifaecom.R;

public class SignUpActivityOperationFactory {
    private boolean isAdmin = false;
    public SignUpActivityOperation getInstance(int anyButtonId, Activity signUpActivity){
        SignUpActivityOperation anyOperationInstance = null;
        if (R.id.createAccountButton==anyButtonId){
            anyOperationInstance = new SignUpActivityCreateButtonOperation(signUpActivity, isAdmin);
        }else if (anyButtonId==R.id.yesIam){
            isAdmin = true;
            anyOperationInstance = new SignUpActivityAdminButtonOperation(signUpActivity);
        }else if (anyButtonId==R.id.noIamNot){
            isAdmin = false;
            anyOperationInstance = new SignUpActivityUserButtonOperation(signUpActivity);
        }


        return anyOperationInstance;
    }
}
