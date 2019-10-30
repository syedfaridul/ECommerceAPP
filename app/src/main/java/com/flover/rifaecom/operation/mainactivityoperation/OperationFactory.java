package com.flover.rifaecom.operation.mainactivityoperation;

import android.app.Activity;

import com.flover.rifaecom.R;

public class OperationFactory {
    private boolean isAdmin = false;
    private Activity mainActivity;

    public OperationFactory(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public Operation getInstance(int anyButtonId){
        Operation anyOperationInstance = null;

        if(anyButtonId==R.id.signUpButton){
            anyOperationInstance = new SignUpButtonOperation(mainActivity);
        }else if(anyButtonId==R.id.signInButton){
            anyOperationInstance = new SignInButtonOperation(mainActivity, isAdmin);
        }else if (anyButtonId==R.id.yesIam){
            isAdmin = true;
            anyOperationInstance = new AdminButtonOperation(mainActivity);
        }else if (anyButtonId==R.id.noIamNot){
            isAdmin = false;
            anyOperationInstance = new UserButtonOperation(mainActivity);
        }else if (anyButtonId==R.id.rememberMeText){
            anyOperationInstance = new RememberMeTextOperation(mainActivity);
        }

        return anyOperationInstance;
    }
}
