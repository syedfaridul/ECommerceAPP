package com.flover.rifaecom.util.initializer;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class OnClickTextInitializer implements ClickInitializer {
    private Activity anyActivity;

    public OnClickTextInitializer(Activity anyActivity) {
        this.anyActivity = anyActivity;
    }

    @Override
    public void initialize(int idOfButton) {
        final TextView anyText = anyActivity.findViewById(idOfButton);
        anyText.setOnClickListener((View.OnClickListener) anyActivity);
    }
}
