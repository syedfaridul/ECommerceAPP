package com.flover.rifaecom.operation.mainactivityoperation;

import android.app.Activity;
import android.widget.CheckBox;

import com.flover.rifaecom.R;

public class MainActivityRememberMeTextClickOperation implements MainActivityOperation{
    private Activity mainActivity;

    public MainActivityRememberMeTextClickOperation(Activity mainActivity) {
        this.mainActivity = mainActivity;
    }


    @Override
    public void perform() {
        CheckBox rememberMeCheckBox = mainActivity.findViewById(R.id.rememberMeCheckBox);
        if (!rememberMeCheckBox.isChecked()){
            rememberMeCheckBox.setChecked(true);
        }else {
            rememberMeCheckBox.setChecked(false);
        }
    }
}
