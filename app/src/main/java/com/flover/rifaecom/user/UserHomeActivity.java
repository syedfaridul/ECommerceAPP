package com.flover.rifaecom.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;

import com.flover.rifaecom.R;
// import com.flover.rifaecom.operation.userhomeactivityoperation.UserHomeActivityOperation;
import com.flover.rifaecom.adapter.ProductViewAdapter;
import com.flover.rifaecom.operation.userhomeactivityoperation.UserHomeActivityOperationFactory;
import com.flover.rifaecom.repository.FirebaseDataBaseRepository;
import com.flover.rifaecom.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
// import com.flover.rifaecom.util.initializer.ClickInitializer;
// import com.flover.rifaecom.util.initializer.OnClickButtonInitializer;

public class UserHomeActivity extends AppCompatActivity implements View.OnClickListener, Observer {
    private String proudctRootRef = "PRODUCTS";
    private Repository productRepository;

    private UserHomeActivityOperationFactory anyOperation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        // ClickInitializer initializerOnClickButton = new OnClickButtonInitializer(this);
        // initializerOnClickButton.initialize(R.id.signOutButton);
        // anyOperation = new UserHomeActivityOperationFactory(this);

        productRepository = new FirebaseDataBaseRepository(proudctRootRef);
        ((FirebaseDataBaseRepository)productRepository).addObserver(this);

        productRepository.getData();
    }

    @Override
    public void onClick(View view) {
        // UserHomeActivityOperation anyOperationInstance = anyOperation.getInstance(view.getId());
        // anyOperationInstance.perform();
    }

    @Override
    public void update(Observable observable, Object o) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Map allData = (Map) productRepository.returnData();
        List keys = new ArrayList(allData.keySet());
        List allUrls = new ArrayList();


        for (Object oneKey : keys){
            Map oneValues = (Map) allData.get(oneKey);
            allUrls.add(oneValues.get("productImageDownloadUrl"));
        }



        ProductViewAdapter adapter = new ProductViewAdapter((ArrayList<String>) allUrls,UserHomeActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(UserHomeActivity.this));
    }
}
