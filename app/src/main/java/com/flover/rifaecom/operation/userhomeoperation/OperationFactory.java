package com.flover.rifaecom.operation.userhomeoperation;

import android.app.Activity;
import com.flover.rifaecom.R;
import com.flover.rifaecom.operation.Operation;

public class OperationFactory {
    private Activity homeActivity;

    public OperationFactory(Activity homeActivity) {
        this.homeActivity = homeActivity;
    }

    public Operation getInstance(int anyButtonId){
        Operation anyOperationInstance = null;
        if (anyButtonId== R.id.userHomeLogOut){
          anyOperationInstance = new SignOutButtonOperation(homeActivity);
        }

        return anyOperationInstance;
    }
}
