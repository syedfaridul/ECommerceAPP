package com.flover.rifaecom;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.flover.rifaecom.operation.initializer.OnClickTextInitializer;
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
        ClickButtonInitializer initializeOnClickButton = new OnClickButtonInitializer(this);
        initializeOnClickButton.initialize(R.id.createAccountButton);

        ClickButtonInitializer initializerOnClickText = new OnClickTextInitializer(this);
        initializerOnClickText.initialize(R.id.yesIam);
        initializerOnClickText.initialize(R.id.noIamNot);

    }

    @Override
    public void onClick(View view) {
        SignUpActivityOperation anyOperationInstance = anyOperation.getInstance(view.getId(), this);
        anyOperationInstance.perform();
    }
}
