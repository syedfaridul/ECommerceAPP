package com.flover.rifaecom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.flover.rifaecom.util.initializer.ClickInitializer;
import com.flover.rifaecom.util.initializer.OnClickButtonInitializer;
import com.flover.rifaecom.util.initializer.OnClickTextInitializer;
import com.flover.rifaecom.operation.mainactivityoperation.Operation;
import com.flover.rifaecom.operation.mainactivityoperation.OperationFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private OperationFactory anyOperation;

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

        anyOperation = new OperationFactory(this);

        ClickInitializer initializeOnClickButton = new OnClickButtonInitializer(this);
        initializeOnClickButton.initialize(R.id.signUpButton);
        initializeOnClickButton.initialize(R.id.signInButton);

        ClickInitializer initializerOnClickText = new OnClickTextInitializer(this);
        initializerOnClickText.initialize(R.id.yesIam);
        initializerOnClickText.initialize(R.id.noIamNot);
        initializerOnClickText.initialize(R.id.rememberMeText);
    }

    /*@Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }*/

    @Override
    public void onClick(View view) {
        Operation anyOperationInstance = anyOperation.getInstance(view.getId());
        anyOperationInstance.perform();
    }
}