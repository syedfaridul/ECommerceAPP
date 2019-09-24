package com.flover.rifaecom.operation.initializer;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class OnClickButtonInitializer implements ClickButtonInitializer{
    @Override
    public void initialize(int idOfButton, Activity activity) {
        final Button anyButton = activity.findViewById(idOfButton);
        anyButton.setOnClickListener((View.OnClickListener) activity);
    }
}
