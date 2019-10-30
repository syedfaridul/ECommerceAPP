package com.flover.rifaecom.operation.userhomeactivityoperation;

import android.app.Activity;

public class OperationFactory {
    private Activity homeActivity;

    public OperationFactory(Activity homeActivity) {
        this.homeActivity = homeActivity;
    }

    public Operation getInstance(int anyButtonId){
        Operation anyOperationInstance = null;
        //if (anyButtonId== R.id.signOutButton){
          //  anyOperationInstance = new UserUserHomeActivitySignOutButtonOperation(homeActivity);
        //}

        return anyOperationInstance;
    }
}
