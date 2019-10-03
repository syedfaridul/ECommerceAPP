package com.flover.rifaecom.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.flover.rifaecom.R;
import com.flover.rifaecom.operation.adminaddnewcategoryactivityoperation.AdminAddNewCategoryActivityAddNewCategoryButtonOperation;
import com.flover.rifaecom.operation.adminaddnewcategoryactivityoperation.AdminAddNewCategoryActivityOperation;
import com.flover.rifaecom.operation.adminaddnewcategoryactivityoperation.AdminAddNewCategoryActivityOperationFactory;
import com.flover.rifaecom.util.initializer.ClickInitializer;
import com.flover.rifaecom.util.initializer.OnClickButtonInitializer;
import com.flover.rifaecom.util.initializer.OnClickImageInitializer;

public class AdminAddNewCategoryActivity extends AppCompatActivity implements View.OnClickListener{

    private AdminAddNewCategoryActivityOperationFactory anyOperation;
    private ImageView productImage;
    public Uri imageUri;
    protected static final int galleryPick = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_category);

        ClickInitializer initializerOnClickImage = new OnClickImageInitializer(this);
        initializerOnClickImage.initialize(R.id.selectProductImage);

        ClickInitializer initializeOnClickButton = new OnClickButtonInitializer(this);
        initializeOnClickButton.initialize(R.id.addNewCategoryButton);


        productImage = findViewById(R.id.selectProductImage);
        anyOperation = new AdminAddNewCategoryActivityOperationFactory(this);
    }

    @Override
    public void onClick(View view) {
        AdminAddNewCategoryActivityOperation anyOperationInstance = anyOperation.getInstance(view.getId());
        anyOperationInstance.perform();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==galleryPick  &&  resultCode==RESULT_OK  &&  data!=null) {
            imageUri = data.getData();

            // https://stackoverflow.com/questions/6267733/android-resizing-imageview-in-xml
            productImage.setImageURI(imageUri);
            Toast.makeText(getBaseContext(), "Product image added!", Toast.LENGTH_SHORT).show();

            // Want to find solution
            AdminAddNewCategoryActivityAddNewCategoryButtonOperation.performAfterGotTheUri(imageUri);
        }
    }
}
