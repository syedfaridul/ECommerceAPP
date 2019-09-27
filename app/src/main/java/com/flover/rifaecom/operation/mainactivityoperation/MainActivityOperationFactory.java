package com.flover.rifaecom.operation.mainactivityoperation;

import android.app.Activity;

import com.flover.rifaecom.R;

public class MainActivityOperationFactory {
    public MainActivityOperation getInstance(int anyButtonId, Activity mainActivity){
        MainActivityOperation anyOperationInstance = null;
        if(anyButtonId==R.id.signUpButton){
            anyOperationInstance = new MainActivitySignUpButtonOperation(mainActivity);
        }else if(anyButtonId==R.id.signInButton){
            anyOperationInstance = new MainActivitySignInButtonOperation(mainActivity);
        }

        return anyOperationInstance;
    }
}
