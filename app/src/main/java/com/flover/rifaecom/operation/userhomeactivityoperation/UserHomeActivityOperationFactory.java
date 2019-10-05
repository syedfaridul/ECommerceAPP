package com.flover.rifaecom.operation.userhomeactivityoperation;

import android.app.Activity;

import com.flover.rifaecom.R;

public class UserHomeActivityOperationFactory {
    private Activity homeActivity;

    public UserHomeActivityOperationFactory(Activity homeActivity) {
        this.homeActivity = homeActivity;
    }

    public UserHomeActivityOperation getInstance(int anyButtonId){
        UserHomeActivityOperation anyOperationInstance = null;
        //if (anyButtonId== R.id.signOutButton){
          //  anyOperationInstance = new UserUserHomeActivitySignOutButtonOperation(homeActivity);
        //}

        return anyOperationInstance;
    }
}
