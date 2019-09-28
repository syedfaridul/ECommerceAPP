package com.flover.rifaecom.operation.mainactivityoperation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.EditText;
import android.widget.Toast;

import com.flover.rifaecom.R;
import com.flover.rifaecom.data.UserData;
import com.flover.rifaecom.repository.FirebaseDataRepository;
import com.flover.rifaecom.repository.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class MainActivitySignInButtonOperation implements MainActivityOperation, Observer {
    private Activity mainActivity;
    private String email;
    private String password;

    public final String userDataRootReference = "USERS";
    public final String userEmailReference = "Email";
    public final String userPasswordReference = "Password";


    private ProgressDialog loadingBar;
    Repository<UserData> firebaseDataRepository;

    public MainActivitySignInButtonOperation(Activity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public void perform() {
        EditText emailEditText = mainActivity.findViewById(R.id.userEmail);
        EditText passwordEditTest = mainActivity.findViewById(R.id.userPassword);

        email = emailEditText.getText().toString();
        password = passwordEditTest.getText().toString();

        email = email.replaceAll("\\s+", "");

        String userName = null;

        if(email.isEmpty()){
            Toast.makeText(mainActivity, "Enter email!", Toast.LENGTH_SHORT).show();
        }else if (password.isEmpty()){
            Toast.makeText(mainActivity, "Enter password!", Toast.LENGTH_SHORT).show();
        }else {
            try{
                userName = email.substring(0, email.indexOf('@'));
            }catch (IndexOutOfBoundsException e){
                Toast.makeText(mainActivity, "Enter valid email, \nExample : jstrfaheem065@gmail.com!", Toast.LENGTH_SHORT).show();
            }

            if ((userName!=null)&&(!userName.equals(""))){
                loadingBar = new ProgressDialog(mainActivity);
                loadingBar.setTitle("Please wait!");
                loadingBar.setMessage("Waiting for server response!");


                loadingBar.show();

                Map<String, String> dataSet = new HashMap<>();
                dataSet.put(userEmailReference, email);
                dataSet.put(userPasswordReference, password);
                firebaseDataRepository = new FirebaseDataRepository(userDataRootReference, userName);
                ((FirebaseDataRepository)firebaseDataRepository).addObserver(this);

                firebaseDataRepository.getData();

            }else {
                Toast.makeText(mainActivity, "Can't accept empty email!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        UserData userData = firebaseDataRepository.returnData();
        loadingBar.dismiss();
    }
}
