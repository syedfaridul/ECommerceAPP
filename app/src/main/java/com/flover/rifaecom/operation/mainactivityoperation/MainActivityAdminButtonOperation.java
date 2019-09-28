package com.flover.rifaecom.operation.mainactivityoperation;

import android.app.Activity;
import android.widget.Button;

import com.flover.rifaecom.R;

public class MainActivityAdminButtonOperation implements MainActivityOperation{
    private Activity mainActivity;
    private String adminLabel = "SIGNIN (ADMIN)";

    public MainActivityAdminButtonOperation(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void perform() {
        Button signInButton = mainActivity.findViewById(R.id.signInButton);
        signInButton.setText(adminLabel);
    }
}
