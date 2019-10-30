package com.flover.rifaecom.operation.singupoperation;

import android.app.Activity;
import android.widget.Button;

import com.flover.rifaecom.R;
import com.flover.rifaecom.operation.Operation;

public class UserButtonOperation implements Operation {
    private Activity signUpActivity;
    private String userLabel = "CREATE (USER)";

    public UserButtonOperation(Activity signUpActivity) {
        this.signUpActivity = signUpActivity;
    }

    @Override
    public void perform() {
        Button createButton = signUpActivity.findViewById(R.id.createAccountButton);
        createButton.setText(userLabel);
    }
}
