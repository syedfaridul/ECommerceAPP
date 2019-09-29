package com.flover.rifaecom.operation.homeactivityoperation;

import android.app.Activity;

import com.flover.rifaecom.R;

public class HomeActivityOperationFactory {

    public HomeActivityOperation getInstance(int anyButtonId, Activity homeActivity){
        HomeActivityOperation anyOperationInstance = null;
        if (anyButtonId== R.id.signOutButton){
            anyOperationInstance = new HomeActivitySignOutButtonOperation(homeActivity);
        }

        return anyOperationInstance;
    }
}
