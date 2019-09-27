package com.flover.rifaecom.operation.singupactivityoperation;


import android.app.Activity;

import com.flover.rifaecom.R;

public class SignUpActivityOperationFactory {
    public SignUpActivityOperation getInstance(int anyButtonId, Activity signUpActivity){
        SignUpActivityOperation anyOperationInstance = null;
        if (R.id.createAccountButton==anyButtonId){
            anyOperationInstance = new SignUpActivityCreateButtonOperation(signUpActivity);
        }


        return anyOperationInstance;
    }
}
