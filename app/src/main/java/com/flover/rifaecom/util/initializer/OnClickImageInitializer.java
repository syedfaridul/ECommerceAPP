package com.flover.rifaecom.util.initializer;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

public class OnClickImageInitializer implements ClickInitializer {
    private Activity anyActivity;

    public OnClickImageInitializer(Activity anyActivity) {
        this.anyActivity = anyActivity;
    }

    @Override
    public void initialize(int idOfButton) {
        ImageView anyImageView = anyActivity.findViewById(idOfButton);
        anyImageView.setOnClickListener((View.OnClickListener) anyActivity);
    }
}
