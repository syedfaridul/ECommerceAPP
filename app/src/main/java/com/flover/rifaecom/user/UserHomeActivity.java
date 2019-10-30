package com.flover.rifaecom.user;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import com.flover.rifaecom.R;
import com.flover.rifaecom.adapter.ProductViewAdapter;
import com.flover.rifaecom.operation.Operation;
import com.flover.rifaecom.operation.userhomeoperation.OperationFactory;
import com.flover.rifaecom.repository.FirebaseDataBaseRepository;
import com.flover.rifaecom.repository.Repository;
import com.flover.rifaecom.util.initializer.ClickInitializer;
import com.flover.rifaecom.util.initializer.OnClickButtonInitializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import io.paperdb.Paper;

public class UserHomeActivity extends AppCompatActivity implements View.OnClickListener, Observer {
    private String proudctRootRef = "PRODUCTS";
    private Repository productRepository;

    private OperationFactory anyOperation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        anyOperation = new OperationFactory(this);

        productRepository = new FirebaseDataBaseRepository(proudctRootRef);
        ((FirebaseDataBaseRepository)productRepository).addObserver(this);

        ClickInitializer onClickButtonInitializer = new OnClickButtonInitializer(this);
        onClickButtonInitializer.initialize(R.id.userHomeLogOut);

        productRepository.getData();
    }

    @Override
    public void onClick(View view) {
        Operation anyOperationInstance = anyOperation.getInstance(view.getId());
        anyOperationInstance.perform();
        Paper.init(this);
        Paper.book().destroy();
    }

    @Override
    public void update(Observable observable, Object o) {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Map allData = (Map) productRepository.returnData();
        try{
            List keys = new ArrayList(allData.keySet());
            List allUrls = new ArrayList();


            for (Object oneKey : keys){
                Map oneValues = (Map) allData.get(oneKey);
                allUrls.add(oneValues.get("productImageDownloadUrl"));
            }

            ProductViewAdapter adapter = new ProductViewAdapter((ArrayList<String>) allUrls,UserHomeActivity.this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(UserHomeActivity.this));
        }catch (NullPointerException e){
            Toast.makeText(this, "Products are empty!", Toast.LENGTH_SHORT).show();
        }
    }
}
