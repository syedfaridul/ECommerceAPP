package com.flover.rifaecom;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.flover.rifaecom.operation.singupactivityoperation.SignUpActivityOperation;
import com.flover.rifaecom.operation.singupactivityoperation.SignUpActivityOperationFactory;
import com.flover.rifaecom.operation.initializer.ClickButtonInitializer;
import com.flover.rifaecom.operation.initializer.OnClickButtonInitializer;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    SignUpActivityOperationFactory anyOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        anyOperation = new SignUpActivityOperationFactory();
        ClickButtonInitializer initializeOnClickButton = new OnClickButtonInitializer();
        initializeOnClickButton.initialize(R.id.createAccountButton, this);
    }

    @Override
    public void onClick(View view) {
        SignUpActivityOperation anyOperationInstance = anyOperation.getInstance(view.getId(), this);
        anyOperationInstance.perform();
    }
}
