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

public class SignUpCreateButtonOperation implements SignUpOperation {
    public final String userDataRootReference = "USERS";
    public final String userEmailReference = "Email";
    public final String userPasswordReference = "Password";

    private ProgressDialog loadingBar;

    @Override
    public void perform(Activity anyActivity) {
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
                firebaseDataRepository.updateData(anyActivity, dataSet);

                /*
                Map<String, Boolean> allFlags = firebaseDataRepository.getAllFlags();

                if(allFlags.get("isUpdateDataTaskComplete")){
                    Toast.makeText(anyActivity, "Your account was created successfully!", Toast.LENGTH_SHORT).show();
                }else if(allFlags.get("isUserPrivateKeyExist")){
                    Toast.makeText(anyActivity, "There was an account already linked with this email!", Toast.LENGTH_SHORT).show();
                }else if (allFlags.get("isUpdateOnCancelled")){
                    Toast.makeText(anyActivity, "Database error occurred!", Toast.LENGTH_SHORT).show();
                }
                */
                loadingBar.dismiss();
            }
        }
    }
}
