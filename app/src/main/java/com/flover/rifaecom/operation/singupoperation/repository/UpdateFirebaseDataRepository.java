package com.flover.rifaecom.operation.singupoperation.repository;

import android.app.Activity;
import android.widget.Toast;

public class UpdateFirebaseDataRepository implements Repository{
    private String userDataRootReference;
    public UpdateFirebaseDataRepository(String userDataRootReference) {
        this.userDataRootReference = userDataRootReference;
    }

    @Override
    public void updateData(Activity anyActivity) {
        Toast.makeText(anyActivity.getBaseContext(), "You Fucked up!", Toast.LENGTH_SHORT).show();
    }
}
