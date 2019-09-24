package com.flover.rifaecom.operation.singupoperation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.EditText;
import android.widget.Toast;

import com.flover.rifaecom.R;
import com.flover.rifaecom.operation.singupoperation.repository.Repository;
import com.flover.rifaecom.operation.singupoperation.repository.UpdateFirebaseDataRepository;

import java.util.HashMap;
import java.util.Map;

public class SignUpCreateButtonOperation implements SignUpOperation {
    private final String userDataRootReference = "USERS";
    private final String userEmailReference = "Email";
    private final String userPasswordReference = "Password";

    @Override
    public void perform(Activity anyActivity) {
        EditText emailEditText = anyActivity.findViewById(R.id.cUserEmail);
        EditText passwordEditText = anyActivity.findViewById(R.id.cUserPasswordR);

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        email = email.replaceAll("\\s+", "");
        String userName = null;

        ProgressDialog loadingBar = new ProgressDialog(anyActivity);

        if(email.isEmpty()){
            Toast.makeText(anyActivity, "Enter valid email!", Toast.LENGTH_SHORT).show();
        }else if(password.isEmpty()){
            Toast.makeText(anyActivity, "Enter valid password!", Toast.LENGTH_SHORT).show();
        }else {
            loadingBar.setTitle("Please wait!");
            loadingBar.setMessage("Creating new account,\nThis won't take much time!");
            loadingBar.show();
            try{
                userName = email.substring(0, email.indexOf('@'));
            }catch (IndexOutOfBoundsException e){
                Toast.makeText(anyActivity, "Enter valid email, \nExample : jstrfaheem065@gmail.com!", Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }

            if(userName!=null){
                Map<String, String> dataSet = new HashMap<>();
                dataSet.put(userEmailReference, email);
                dataSet.put(userPasswordReference, password);
                Repository firebaseDataRepository = new UpdateFirebaseDataRepository(userDataRootReference);
                firebaseDataRepository.updateData(anyActivity);
                loadingBar.dismiss();
            }
        }
    }
}
