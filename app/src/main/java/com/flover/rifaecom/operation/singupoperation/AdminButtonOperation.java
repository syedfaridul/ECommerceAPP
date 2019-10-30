package com.flover.rifaecom.operation.singupoperation;

import android.app.Activity;
import android.widget.Button;

import com.flover.rifaecom.R;
import com.flover.rifaecom.operation.Operation;

public class AdminButtonOperation implements Operation {
    private Activity signUpActivity;

    private String adminLabel = "CREATE (ADMIN)";

    public AdminButtonOperation(Activity signUpActivity) {
        this.signUpActivity = signUpActivity;
    }

    @Override
    public void perform() {
        Button createButton = signUpActivity.findViewById(R.id.createAccountButton);
        createButton.setText(adminLabel);
    }
}
