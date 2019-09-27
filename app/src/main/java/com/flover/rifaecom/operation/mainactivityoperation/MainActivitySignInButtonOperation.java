package com.flover.rifaecom.operation.mainactivityoperation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.EditText;
import android.widget.Toast;

import com.flover.rifaecom.R;
import com.flover.rifaecom.repository.FirebaseDataRepository;
import com.flover.rifaecom.repository.Repository;

import java.util.HashMap;
import java.util.Map;

public class MainActivitySignInButtonOperation implements MainActivityOperation{
    private Activity mainActivity;
    private String email;
    private String password;

    public final String userDataRootReference = "USERS";
    public final String userEmailReference = "Email";
    public final String userPasswordReference = "Password";


    private ProgressDialog loadingBar;

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
                loadingBar.setMessage("Creating new account,\nThis won't take much time!");


                loadingBar.show();

                Map<String, String> dataSet = new HashMap<>();
                dataSet.put(userEmailReference, email);
                dataSet.put(userPasswordReference, password);
                Repository firebaseDataRepository = new FirebaseDataRepository(userDataRootReference, userName);
            }else {
                Toast.makeText(mainActivity, "Can't accept empty email!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
