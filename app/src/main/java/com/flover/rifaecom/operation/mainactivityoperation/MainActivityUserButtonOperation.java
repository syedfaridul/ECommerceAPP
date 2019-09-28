package com.flover.rifaecom.operation.mainactivityoperation;

import android.app.Activity;
import android.widget.TextView;

import com.flover.rifaecom.R;

public class MainActivityUserButtonOperation implements MainActivityOperation{
    private Activity mainActivity;
    private String userLabel = "SIGNIN (USER)";

    public MainActivityUserButtonOperation(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void perform() {
        TextView noAdmin = mainActivity.findViewById(R.id.signInButton);
        noAdmin.setText(userLabel);
    }
}
