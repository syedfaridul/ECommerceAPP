package com.flover.rifaecom.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.flover.rifaecom.R;
import com.flover.rifaecom.operation.adminaddnewproductactivityoperation.AdminAddNewCategoryActivityOperationFactory;
import com.flover.rifaecom.operation.adminaddnewproductactivityoperation.AdminAddNewProductActivityOperation;
import com.flover.rifaecom.util.initializer.ClickButtonInitializer;
import com.flover.rifaecom.util.initializer.OnClickImageInitializer;

public class AdminAddNewProductActivity extends AppCompatActivity implements View.OnClickListener{

    AdminAddNewCategoryActivityOperationFactory anyOperation;

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

        anyOperation = new AdminAddNewCategoryActivityOperationFactory(this);

    }

    @Override
    public void onClick(View view) {
        AdminAddNewProductActivityOperation anyOperationInstance = anyOperation.getInstance(view.getId());
        anyOperationInstance.perform();
    }
}