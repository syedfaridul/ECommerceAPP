package com.flover.rifaecom.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.flover.rifaecom.R;
import com.flover.rifaecom.operation.userhomeactivityoperation.UserHomeActivityOperation;
import com.flover.rifaecom.operation.userhomeactivityoperation.UserHomeActivityOperationFactory;
import com.flover.rifaecom.util.initializer.ClickButtonInitializer;
import com.flover.rifaecom.util.initializer.OnClickButtonInitializer;

public class UserHomeActivity extends AppCompatActivity implements View.OnClickListener{
    private UserHomeActivityOperationFactory anyOperation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        ClickButtonInitializer initializerOnClickButton = new OnClickButtonInitializer(this);
        initializerOnClickButton.initialize(R.id.signOutButton);
        anyOperation = new UserHomeActivityOperationFactory(this);
    }

    @Override
    public void onClick(View view) {
        UserHomeActivityOperation anyOperationInstance = anyOperation.getInstance(view.getId());
        anyOperationInstance.perform();
    }
}
