package com.flover.rifaecom.operation.mainactivityoperation;

import android.app.Activity;
import android.content.Intent;

import com.flover.rifaecom.SignUpActivity;

public class MainActivitySignUpButtonOperation implements MainActivityOperation{
    private Activity mainActivity;

    public MainActivitySignUpButtonOperation(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void perform() {
        Intent signUpActivity = new Intent(mainActivity, SignUpActivity.class);
        mainActivity.startActivity(signUpActivity);
    }
}
