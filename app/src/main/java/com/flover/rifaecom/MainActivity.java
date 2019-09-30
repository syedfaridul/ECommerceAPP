package com.flover.rifaecom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.flover.rifaecom.util.initializer.ClickButtonInitializer;
import com.flover.rifaecom.util.initializer.OnClickButtonInitializer;
import com.flover.rifaecom.util.initializer.OnClickTextInitializer;
import com.flover.rifaecom.operation.mainactivityoperation.MainActivityOperation;
import com.flover.rifaecom.operation.mainactivityoperation.MainActivityOperationFactory;
import com.flover.rifaecom.operation.mainactivityoperation.MainActivitySignInButtonOperation;
import com.flover.rifaecom.repository.PaperDataRepository;
import com.flover.rifaecom.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MainActivityOperationFactory anyOperation;
    // public final String userDataRootReference = "USERS";
    public final String emailReference = "Email";
    public final String passwordReference = "Password";

    private List allKeys;

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

        ClickButtonInitializer initializeOnClickButton = new OnClickButtonInitializer(this);
        initializeOnClickButton.initialize(R.id.signUpButton);
        initializeOnClickButton.initialize(R.id.signInButton);

        ClickButtonInitializer initializerOnClickText = new OnClickTextInitializer(this);
        initializerOnClickText.initialize(R.id.yesIam);
        initializerOnClickText.initialize(R.id.noIamNot);
        initializerOnClickText.initialize(R.id.rememberMeText);

        allKeys = new ArrayList();
        anyOperation = new MainActivityOperationFactory(this);
        allKeys.add(emailReference);
        allKeys.add(passwordReference);
        Repository rememberMe = new PaperDataRepository(this, allKeys);
        rememberMe.getData();
        Map<String, String> userData = (Map) rememberMe.returnData();
        String email = userData.get(emailReference);
        String password = userData.get(passwordReference);

        if ((email!=null)&&(password!=null)){
            EditText emailEditText = findViewById(R.id.userEmail);
            EditText passwordText = findViewById(R.id.userPassword);
            CheckBox rememberMeCheckBox = findViewById(R.id.rememberMeCheckBox);

            emailEditText.setText(email);
            passwordText.setText(password);
            rememberMeCheckBox.setChecked(true);
            MainActivitySignInButtonOperation signIn = new MainActivitySignInButtonOperation(this, email, password);
            signIn.perform();
        }
    }

    /*@Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }*/

    @Override
    public void onClick(View view) {
        MainActivityOperation anyOperationInstance = anyOperation.getInstance(view.getId());
        anyOperationInstance.perform();
    }
}