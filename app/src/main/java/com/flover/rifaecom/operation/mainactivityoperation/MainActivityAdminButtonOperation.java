package com.flover.rifaecom.operation.mainactivityoperation;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

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
        CheckBox rememberMeCheckBox = mainActivity.findViewById(R.id.rememberMeCheckBox);
        TextView rememberMeText = mainActivity.findViewById(R.id.rememberMeText);

        rememberMeCheckBox.setVisibility(View.GONE);
        rememberMeText.setVisibility(View.GONE);
    }
}
