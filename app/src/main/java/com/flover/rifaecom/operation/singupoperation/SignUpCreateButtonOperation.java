package com.flover.rifaecom.operation.singupoperation;

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

public class SignUpCreateButtonOperation implements SignUpOperation, Observer {
    public final String userDataRootReference = "USERS";
    public final String userEmailReference = "Email";
    public final String userPasswordReference = "Password";

    private ProgressDialog loadingBar;
    Map<String, Boolean> allFlags;
    // Want to find alternative
    private Activity anyActivity2;


    @Override
    public void perform(Activity anyActivity) {
        this.anyActivity2 = anyActivity;


        EditText emailEditText = anyActivity.findViewById(R.id.cUserEmail);
        EditText passwordEditText = anyActivity.findViewById(R.id.cUserPasswordR);

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        email = email.replaceAll("\\s+", "");
        String userName = null;

        if(email.isEmpty()){
            Toast.makeText(anyActivity, "Enter valid email!", Toast.LENGTH_SHORT).show();
        }else if(password.isEmpty()){
            Toast.makeText(anyActivity, "Enter valid password!", Toast.LENGTH_SHORT).show();
        }else {
            try{
                userName = email.substring(0, email.indexOf('@'));
            }catch (IndexOutOfBoundsException e){
                Toast.makeText(anyActivity, "Enter valid email, \nExample : jstrfaheem065@gmail.com!", Toast.LENGTH_SHORT).show();
            }

            if(userName!=null){
                loadingBar = new ProgressDialog(anyActivity);
                loadingBar.setTitle("Please wait!");
                loadingBar.setMessage("Creating new account,\nThis won't take much time!");


                loadingBar.show();

                Map<String, String> dataSet = new HashMap<>();
                dataSet.put(userEmailReference, email);
                dataSet.put(userPasswordReference, password);
                Repository firebaseDataRepository = new FirebaseDataRepository(userDataRootReference, userName);

                ((FirebaseDataRepository)firebaseDataRepository).addObserver(this);
                firebaseDataRepository.updateData(/*anyActivity,*/ dataSet);


                /*
                Before Observer pattern!
                Map<String, Boolean> allFlags = firebaseDataRepository.getAllFlags();
                */
            }
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        allFlags = ((Repository)observable).getAllFlags();

        if(allFlags.get("isUpdateDataTaskComplete")){
            Toast.makeText(this.anyActivity2, "Your account was created successfully!", Toast.LENGTH_SHORT).show();

            final Thread destroyActivityThread = new Thread(){
                @Override
                public void run(){
                    try {
                        Thread.sleep(2000);
                        anyActivity2.finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            destroyActivityThread.start();

        }else if(allFlags.get("isUserPrivateKeyExist")){
            Toast.makeText(this.anyActivity2, "There was an account already linked with this email!", Toast.LENGTH_SHORT).show();
        }else if (allFlags.get("isUpdateOnCancelled")){
            Toast.makeText(this.anyActivity2, "Database error occurred!", Toast.LENGTH_SHORT).show();
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
