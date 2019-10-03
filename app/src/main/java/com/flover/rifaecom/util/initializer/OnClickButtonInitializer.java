package com.flover.rifaecom.util.initializer;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class OnClickButtonInitializer implements ClickInitializer {
    private Activity anyActivity;

    public OnClickButtonInitializer(Activity anyActivity) {
        this.anyActivity = anyActivity;
    }

    @Override
    public void initialize(int idOfButton) {
        final Button anyButton = anyActivity.findViewById(idOfButton);
        anyButton.setOnClickListener((View.OnClickListener) anyActivity);
    }
}
