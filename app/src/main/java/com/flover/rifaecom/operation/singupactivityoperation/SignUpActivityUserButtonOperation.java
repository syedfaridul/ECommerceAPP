package com.flover.rifaecom.operation.singupactivityoperation;

import android.app.Activity;
import android.widget.Button;

import com.flover.rifaecom.R;

public class SignUpActivityUserButtonOperation implements SignUpActivityOperation{
    private Activity signUpActivity;
    private String userLabel = "CREATE (USER)";

    public SignUpActivityUserButtonOperation(Activity signUpActivity) {
        this.signUpActivity = signUpActivity;
    }

    @Override
    public void perform() {
        Button createButton = signUpActivity.findViewById(R.id.createAccountButton);
        createButton.setText(userLabel);
    }
}
