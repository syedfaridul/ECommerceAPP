package com.flover.rifaecom.operation.mainoperation;

import android.app.Activity;
import android.content.Intent;

import com.flover.rifaecom.SignUpActivity;
import com.flover.rifaecom.operation.Operation;

public class SignUpButtonOperation implements Operation {
    private Activity mainActivity;

    public SignUpButtonOperation(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void perform() {
        Intent signUpActivity = new Intent(mainActivity, SignUpActivity.class);
        mainActivity.startActivity(signUpActivity);
    }
}
