package com.flover.rifaecom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.flover.rifaecom.util.initializer.ClickButtonInitializer;
import com.flover.rifaecom.util.initializer.OnClickImageInitializer;

public class AdminAddNewProductActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);

        ClickButtonInitializer initializerOnClickImage = new OnClickImageInitializer(this);

        initializerOnClickImage.initialize(R.id.tShirt);
        initializerOnClickImage.initialize(R.id.sweather);
        initializerOnClickImage.initialize(R.id.sportTShirt);
        initializerOnClickImage.initialize(R.id.femaleDress);
        initializerOnClickImage.initialize(R.id.glass);
        initializerOnClickImage.initialize(R.id.purseAndBag);
        initializerOnClickImage.initialize(R.id.hat);
        initializerOnClickImage.initialize(R.id.shoe);
        initializerOnClickImage.initialize(R.id.lapTop);
        initializerOnClickImage.initialize(R.id.headPhone);
        initializerOnClickImage.initialize(R.id.watch);
        initializerOnClickImage.initialize(R.id.mobile);

    }

    @Override
    public void onClick(View view) {
        Intent addNewCategoryIntent = new Intent(this, AdminAddNewCategoryActivity.class);
        startActivity(addNewCategoryIntent);
    }
}
