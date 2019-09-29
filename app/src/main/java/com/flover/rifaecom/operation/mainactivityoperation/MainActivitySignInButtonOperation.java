package com.flover.rifaecom.operation.mainactivityoperation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.flover.rifaecom.AdminPageActivity;
import com.flover.rifaecom.HomePageActivity;
import com.flover.rifaecom.R;
import com.flover.rifaecom.repository.FirebaseDataRepository;
import com.flover.rifaecom.repository.PaperDataRepository;
import com.flover.rifaecom.repository.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class MainActivitySignInButtonOperation implements MainActivityOperation, Observer {
    private Activity mainActivity;
    private String email;
    private String password;

    public final String userDataRootReference = "USERS";
    public final String emailReference = "Email";
    public final String passwordReference = "Password";
    private final String adminDataRootReference = "ADMINS";
    private String dataRootReference;

    private ProgressDialog loadingBar;
    Repository firebaseDataRepository;
    private boolean isAdmin;

    public MainActivitySignInButtonOperation(Activity mainActivity, boolean isAdmin){
        this.mainActivity = mainActivity;
        this.isAdmin = isAdmin;

        EditText emailEditText = mainActivity.findViewById(R.id.userEmail);
        EditText passwordEditTest = mainActivity.findViewById(R.id.userPassword);

        this.email = emailEditText.getText().toString();
        this.password = passwordEditTest.getText().toString();
    }

    public MainActivitySignInButtonOperation(Activity mainActivity, String email, String password){
        this.mainActivity = mainActivity;

        this.email = email;
        this.password = password;
        this.isAdmin = false;
    }

    @Override
    public void perform() {

        if(isAdmin){
            dataRootReference = adminDataRootReference;
        }else {
            dataRootReference = userDataRootReference;
        }

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
                dataSet.put(emailReference, email);
                dataSet.put(passwordReference, password);
                firebaseDataRepository = new FirebaseDataRepository(dataRootReference, userName);
                ((FirebaseDataRepository)firebaseDataRepository).addObserver(this);

                firebaseDataRepository.getData();

            }else {
                Toast.makeText(mainActivity, "Can't accept empty email!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        Map allData = (HashMap) firebaseDataRepository.returnData();
        Map<String, Boolean> allFlags = firebaseDataRepository.returnAllFlags();
        try {
            if(email.equals(allData.get(emailReference))){
                CheckBox rememberMeCheckBox = mainActivity.findViewById(R.id.rememberMeCheckBox);
                if(password.equals(allData.get(passwordReference))&&(!isAdmin)){
                    if (rememberMeCheckBox.isChecked()){

                        // https://stackoverflow.com/questions/8892360/convert-set-to-list-without-creating-new-list
                        List allKeys = new ArrayList(allData.keySet());
                        Repository androidPaper = new PaperDataRepository(mainActivity, allKeys);
                        androidPaper.updateData(allData);
                    }
                    Intent homePageIntent = new Intent(mainActivity, HomePageActivity.class);
                    mainActivity.startActivity(homePageIntent);
                }else if (password.equals(allData.get(passwordReference))&&(isAdmin)){
                    Intent adminPageIntent = new Intent(mainActivity, AdminPageActivity.class);
                    mainActivity.startActivity(adminPageIntent);
                }else {
                    Toast.makeText(mainActivity, "Incorrect Username or Password!", Toast.LENGTH_SHORT).show();
                }
            }else if (allFlags.get("isGettingDataErrorOccurred")){
                Toast.makeText(mainActivity, "There was a server error occurred!", Toast.LENGTH_SHORT).show();
            }
        }catch (NullPointerException e){
            Toast.makeText(mainActivity, "No account found, Please create a new account!", Toast.LENGTH_SHORT).show();
        }
        final Thread destroyDialogBoxThread = new Thread(){
            @Override
            public void run(){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                loadingBar.dismiss();
            }
        };

        destroyDialogBoxThread.start();
        mainActivity.finish();
    }
}
