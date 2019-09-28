package com.flover.rifaecom.operation.singupactivityoperation;

import android.app.Activity;
import android.widget.Button;

import com.flover.rifaecom.R;

public class SignUpActivityAdminButtonOperation implements SignUpActivityOperation{
    private Activity signUpActivity;

    private String adminLabel = "CREATE (ADMIN)";

    public SignUpActivityAdminButtonOperation(Activity signUpActivity) {
        this.signUpActivity = signUpActivity;
    }

    @Override
    public void perform() {
        Button createButton = signUpActivity.findViewById(R.id.createAccountButton);
        createButton.setText(adminLabel);
    }
}
