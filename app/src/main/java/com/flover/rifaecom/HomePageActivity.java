package com.flover.rifaecom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.flover.rifaecom.operation.homeactivityoperation.HomeActivityOperation;
import com.flover.rifaecom.operation.homeactivityoperation.HomeActivityOperationFactory;
import com.flover.rifaecom.operation.initializer.ClickButtonInitializer;
import com.flover.rifaecom.operation.initializer.OnClickButtonInitializer;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener{
    private HomeActivityOperationFactory anyOperation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ClickButtonInitializer initializerOnClickButton = new OnClickButtonInitializer(this);
        initializerOnClickButton.initialize(R.id.signOutButton);
        anyOperation = new HomeActivityOperationFactory();
    }

    @Override
    public void onClick(View view) {
        HomeActivityOperation anyOperationInstance = anyOperation.getInstance(view.getId(), this);
        anyOperationInstance.perform();
    }
}
