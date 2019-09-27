package com.flover.rifaecom.repository;


import android.provider.ContactsContract;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

public class FirebaseDataRepository extends Observable implements Repository{
    private static boolean isUserPrivateKeyExist = false;
    private String userPrivateKeyExistFlag = "isUserPrivateKeyExist";
    private static boolean isUpdateDataTaskComplete = false;
    private String updateDataTaskCompleteFlag = "isUpdateDataTaskComplete";
    private static boolean isUpdateOnCancelled = false;
    private static String updateOnCancelledFlag = "isUpdateOnCancelled";


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
    public void updateData(/*final Activity anyActivity,*/ final Map dataSet) {
        final DatabaseReference rootReference = FirebaseDatabase.getInstance().getReference();
        rootReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!(dataSnapshot.child(userDataRootReference).child(userPrivateKey).exists())){
                    rootReference.child(userDataRootReference).child(userPrivateKey).updateChildren(dataSet);
                    isUpdateDataTaskComplete = true;
                    // Toast.makeText(anyActivity, "Account created!", Toast.LENGTH_SHORT).show();
                }else {
                    isUserPrivateKeyExist = true;
                    // Toast.makeText(anyActivity, "Email exist!", Toast.LENGTH_SHORT).show();
                }
                setChanged();
                notifyObservers();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                isUpdateOnCancelled = true;
                // Toast.makeText(anyActivity, "Database Error!", Toast.LENGTH_SHORT).show();
                setChanged();
                notifyObservers();
            }
        });
    }

    @Override
    public Map getAllFlags() {
        allFlags.put(updateDataTaskCompleteFlag, isUpdateDataTaskComplete);
        allFlags.put(userPrivateKeyExistFlag, isUserPrivateKeyExist);
        allFlags.put(updateOnCancelledFlag, isUpdateOnCancelled);
        return allFlags;
    }


}
