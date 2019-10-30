package com.flover.rifaecom.operation.userhomeactivityoperation;

import android.app.Activity;
import android.content.Intent;

import com.flover.rifaecom.MainActivity;

import io.paperdb.Paper;

public class SignOutButtonOperation implements Operation {

    private Activity homeActivity;

    public SignOutButtonOperation(Activity homeActivity) {
        this.homeActivity = homeActivity;
        Paper.init(homeActivity);
    }

    @Override
    public void perform() {
        Intent mainActivity = new Intent(homeActivity, MainActivity.class);
        homeActivity.startActivity(mainActivity);
        homeActivity.finish();
        Paper.book().destroy();
    }
}
