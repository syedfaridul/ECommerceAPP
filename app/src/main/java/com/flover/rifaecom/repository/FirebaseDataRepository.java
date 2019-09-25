package com.flover.rifaecom.repository;


import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class FirebaseDataRepository implements Repository{
    private boolean isUserPrivateKeyExist = false;
    private String userPrivateKeyExistFlag = "isUserPrivateKeyExist";
    private boolean isUpdateDataTaskComplete = false;
    private String updateDataTaskCompleteFlag = "isUpdateDataTaskComplete";
    private boolean isUpdateOnCancelled = false;
    private String updateOnCancelledFlag = "isUpdateOnCancelled";


    private Map<String, Boolean> allFlags;

    private String userDataRootReference;
    private String userPrivateKey;
    public FirebaseDataRepository(String userDataRootReference, String userPrivateKey) {
        this.userDataRootReference = userDataRootReference;
        this.userPrivateKey = userPrivateKey;
        allFlags = new HashMap<>();
        allFlags.put(userPrivateKeyExistFlag, isUserPrivateKeyExist);
        allFlags.put(updateDataTaskCompleteFlag, isUpdateDataTaskComplete);
        allFlags.put(updateOnCancelledFlag, isUpdateOnCancelled);
    }

    @Override
    public void updateData(final Map dataSet) {
        final DatabaseReference rootReference = FirebaseDatabase.getInstance().getReference();
        rootReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!(dataSnapshot.child(userDataRootReference).child(userPrivateKey).exists())){
                    rootReference.child(userDataRootReference).child(userPrivateKey).updateChildren(dataSet);
                    allFlags.put(updateDataTaskCompleteFlag, true);
                }else {
                    allFlags.put(userPrivateKeyExistFlag, true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                allFlags.put(updateOnCancelledFlag, true);
            }
        });
    }

    @Override
    public Map getAllFlags() {
        return allFlags;
    }


}
