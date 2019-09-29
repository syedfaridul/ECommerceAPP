package com.flover.rifaecom.operation.singupactivityoperation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.EditText;
import android.widget.Toast;

import com.flover.rifaecom.R;
import com.flover.rifaecom.repository.Repository;
import com.flover.rifaecom.repository.FirebaseDataRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class SignUpActivityCreateButtonOperation implements SignUpActivityOperation, Observer {
    public final String userDataRootReference = "USERS";
    public final String emailReference = "Email";
    public final String passwordReference = "Password";
    private final String adminDataRootReference = "ADMINS";
    private String dataRootReference;

    private ProgressDialog loadingBar;
    Map<String, Boolean> allFlags;
    // Want to find alternative
    private Activity signUpActivity;
    private boolean isAdmin;

    public SignUpActivityCreateButtonOperation(Activity signUpActivity, boolean isAdmin) {
        this.signUpActivity = signUpActivity;
        this.isAdmin = isAdmin;
    }

    @Override
    public void perform() {
        if(isAdmin){
            dataRootReference = adminDataRootReference;
        }else {
            dataRootReference = userDataRootReference;
        }

        EditText emailEditText = signUpActivity.findViewById(R.id.cUserEmail);
        EditText passwordEditText = signUpActivity.findViewById(R.id.cUserPasswordR);

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        email = email.replaceAll("\\s+", "");
        String userName = null;

        if(email.isEmpty()){
            Toast.makeText(signUpActivity, "Enter valid email!", Toast.LENGTH_SHORT).show();
        }else if(password.isEmpty()){
            Toast.makeText(signUpActivity, "Enter valid password!", Toast.LENGTH_SHORT).show();
        }else {
            try{
                userName = email.substring(0, email.indexOf('@'));
            }catch (IndexOutOfBoundsException e){
                Toast.makeText(signUpActivity, "Enter valid email, \nExample : jstrfaheem065@gmail.com!", Toast.LENGTH_SHORT).show();
            }

            if((userName!=null)&&(!userName.equals(""))){
                loadingBar = new ProgressDialog(signUpActivity);
                loadingBar.setTitle("Please wait!");
                loadingBar.setMessage("Creating new account,\nThis won't take much time!");


                loadingBar.show();

                Map<String, String> dataSet = new HashMap<>();
                dataSet.put(emailReference, email);
                dataSet.put(passwordReference, password);
                Repository firebaseDataRepository = new FirebaseDataRepository(dataRootReference, userName);

                ((FirebaseDataRepository)firebaseDataRepository).addObserver(this);
                firebaseDataRepository.updateData(/*anyActivity,*/ dataSet);


                /*
                Before Observer pattern!
                Map<String, Boolean> allFlags = firebaseDataRepository.getAllFlags();
                */
            }else {
                Toast.makeText(signUpActivity, "Can't accept empty email!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        allFlags = ((Repository)observable).returnAllFlags();

        if(allFlags.get("isUpdateDataTaskComplete")){
            Toast.makeText(signUpActivity, "Your account was created successfully!", Toast.LENGTH_SHORT).show();

            final Thread destroyActivityThread = new Thread(){
                @Override
                public void run(){
                    try {
                        Thread.sleep(2000);
                        signUpActivity.finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            destroyActivityThread.start();

        }else if(!allFlags.get("isUpdateDataTaskComplete")){
            Toast.makeText(signUpActivity, "There was an account already linked with this email!", Toast.LENGTH_SHORT).show();
        }else if (allFlags.get("isUpdateOnCancelled")){
            Toast.makeText(signUpActivity, "Database error occurred!", Toast.LENGTH_SHORT).show();
        }

        final Thread destroyLoadingBarThread = new Thread(){
            @Override
            public void run(){
                try {
                    Thread.sleep(2000);
                    loadingBar.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        destroyLoadingBarThread.start();
    }
}
