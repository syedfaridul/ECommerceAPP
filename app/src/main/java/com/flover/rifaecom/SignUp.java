package com.flover.rifaecom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {
    private Button createAccountButton;
    private EditText userEmailAddress;
    private EditText userPassword;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        createAccountButton = findViewById(R.id.createAccountButton);
        userEmailAddress = findViewById(R.id.cuserEmailOrPhoneR);
        userPassword = findViewById(R.id.cuserPasswordR);
        loadingBar = new ProgressDialog(this);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });
    }

    void createAccount(){
        String emailId = userEmailAddress.getText().toString();
        String password = userPassword.getText().toString();


        /*
        int toastDurationInMilliSeconds = 1000;
        final Toast mToastToShow = Toast.makeText(this, "Hello world, I am a toast.", Toast.LENGTH_LONG);
        CountDownTimer toastCountDown;
        toastCountDown = new CountDownTimer(100, 10000 //Tick duration) {
            public void onTick(long millisUntilFinished) {
                mToastToShow.show();
            }
            public void onFinish() {
                mToastToShow.cancel();
            }
        };

        // Show the toast and starts the countdown
        mToastToShow.show();
        toastCountDown.start();*/

        if (TextUtils.isEmpty(emailId)){
            Toast.makeText(getBaseContext(), "Enter Email!", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(getBaseContext(), "Enter Password!", Toast.LENGTH_SHORT).show();
        }else {
            loadingBar.setTitle("Creating Account!");
            loadingBar.setMessage("We are creating a new account for you.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatePhoneNumber(emailId, password);
        }
    }

    private void ValidatePhoneNumber(final String emailId, final String password) {
        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("USERS").child(emailId).exists())){
                    HashMap<String, Object> userDataMap = new HashMap<>();
                    userDataMap.put("Email", emailId);
                    userDataMap.put("Password", password);

                    rootRef.child("USERS").child(emailId).updateChildren(userDataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getBaseContext(), "Successfully created account!", Toast.LENGTH_LONG).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(SignUp.this, MainActivity.class);
                                startActivity(intent);
                            }else {
                                loadingBar.dismiss();
                                Toast.makeText(getBaseContext(), "Network error!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }else {
                    Toast.makeText(getBaseContext(), "This " + emailId + " already exist!", Toast.LENGTH_LONG).show();
                    loadingBar.dismiss();
                    Toast.makeText(getBaseContext(), "Try again using another email id!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}