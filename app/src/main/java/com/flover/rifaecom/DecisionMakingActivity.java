package com.flover.rifaecom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.flover.rifaecom.operation.mainactivityoperation.SignInButtonOperation;
import com.flover.rifaecom.repository.PaperDataRepository;
import com.flover.rifaecom.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DecisionMakingActivity extends AppCompatActivity {

    // public final String userDataRootReference = "USERS";
    public final String emailReference = "Email";
    public final String passwordReference = "Password";

    private List allKeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decision_making);

        allKeys = new ArrayList();
        allKeys.add(emailReference);
        allKeys.add(passwordReference);
        Repository rememberMe = new PaperDataRepository(this, allKeys);
        rememberMe.getData();
        Map<String, String> userData = (Map) rememberMe.returnData();
        String email = userData.get(emailReference);
        String password = userData.get(passwordReference);

        if ((email!=null)&&(password!=null)){
            SignInButtonOperation signIn = new SignInButtonOperation(this, email, password);
            signIn.perform();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
