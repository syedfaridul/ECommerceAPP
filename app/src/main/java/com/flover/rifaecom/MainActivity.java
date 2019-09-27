package com.flover.rifaecom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.flover.rifaecom.operation.initializer.ClickButtonInitializer;
import com.flover.rifaecom.operation.initializer.OnClickButtonInitializer;
import com.flover.rifaecom.operation.mainactivityoperation.MainActivityOperation;
import com.flover.rifaecom.operation.mainactivityoperation.MainActivityOperationFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    MainActivityOperationFactory anyOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        final Button signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(this);
        final Button signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(this);
        */

        ClickButtonInitializer initializeOnClickButton = new OnClickButtonInitializer();
        initializeOnClickButton.initialize(R.id.signUpButton, this);
        initializeOnClickButton.initialize(R.id.signInButton, this);

        anyOperation = new MainActivityOperationFactory();
    }

    @Override
    public void onClick(View view) {
        MainActivityOperation anyOperationInstance = anyOperation.getInstance(view.getId(), this);
        anyOperationInstance.perform();
    }
}