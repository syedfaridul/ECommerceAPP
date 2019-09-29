package com.flover.rifaecom.operation.mainactivityoperation;

import android.app.Activity;

import com.flover.rifaecom.R;

public class MainActivityOperationFactory {
    private boolean isAdmin = false;
    public MainActivityOperation getInstance(int anyButtonId, Activity mainActivity){
        MainActivityOperation anyOperationInstance = null;

        if(anyButtonId==R.id.signUpButton){
            anyOperationInstance = new MainActivitySignUpButtonOperation(mainActivity);
        }else if(anyButtonId==R.id.signInButton){
            anyOperationInstance = new MainActivitySignInButtonOperation(mainActivity, isAdmin);
        }else if (anyButtonId==R.id.yesIam){
            isAdmin = true;
            anyOperationInstance = new MainActivityAdminButtonOperation(mainActivity);
        }else if (anyButtonId==R.id.noIamNot){
            isAdmin = false;
            anyOperationInstance = new MainActivityUserButtonOperation(mainActivity);
        }else if (anyButtonId==R.id.rememberMeText){
            anyOperationInstance = new MainActivityRememberMeTextClickOperation(mainActivity);
        }

        return anyOperationInstance;
    }
}
