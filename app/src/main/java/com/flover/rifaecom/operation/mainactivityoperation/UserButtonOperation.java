package com.flover.rifaecom.operation.mainactivityoperation;

import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.flover.rifaecom.R;
import com.flover.rifaecom.operation.Operation;

public class UserButtonOperation implements Operation {
    private Activity mainActivity;
    private String userLabel = "SIGNIN (USER)";

    public UserButtonOperation(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void perform() {
        TextView noAdmin = mainActivity.findViewById(R.id.signInButton);
        noAdmin.setText(userLabel);

        CheckBox rememberMeCheckBox = mainActivity.findViewById(R.id.rememberMeCheckBox);
        TextView rememberMeText = mainActivity.findViewById(R.id.rememberMeText);

        rememberMeCheckBox.setVisibility(View.VISIBLE);
        rememberMeText.setVisibility(View.VISIBLE);
    }
}
