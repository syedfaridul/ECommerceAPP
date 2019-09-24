package com.flover.rifaecom;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.flover.rifaecom.operation.singupoperation.SignUpOperation;
import com.flover.rifaecom.operation.singupoperation.SignUpOperationFactory;
import com.flover.rifaecom.operation.initializer.ClickButtonInitializer;
import com.flover.rifaecom.operation.initializer.OnClickButtonInitializer;

public class SignUp2 extends AppCompatActivity implements View.OnClickListener{
    SignUpOperationFactory anyOperation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        anyOperation = new SignUpOperationFactory();
        ClickButtonInitializer initializerOnClickButton = new OnClickButtonInitializer();
        initializerOnClickButton.initialize(R.id.createAccountButton, this);
    }

    @Override
    public void onClick(View view) {
        SignUpOperation anyOperationInstance = anyOperation.getInstance(view.getId());
        anyOperationInstance.perform(this);
    }
}
