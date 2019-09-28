package com.flover.rifaecom.repository;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class FirebaseDataRepository extends Observable implements Repository{
    // private boolean isUserPrivateKeyExist = false;
    // private String userPrivateKeyExistFlag = "isUserPrivateKeyExist";
    private boolean isUpdateDataTaskComplete = false;
    private String updateDataTaskCompleteFlag = "isUpdateDataTaskComplete";
    private boolean isUpdateOnCancelled = false;
    private String updateOnCancelledFlag = "isUpdateOnCancelled";
    private String gettingDataError = "isGettingDataErrorOccurred";
    private boolean isGettingDataErrorOccurred = false;

    private Object dataFromFirebase;


    private Map<String, Boolean> allFlags;

    private String userDataRootReference;
    private String userPrivateKey;

    public FirebaseDataRepository(String userDataRootReference, String userPrivateKey) {
        this.userDataRootReference = userDataRootReference;
        this.userPrivateKey = userPrivateKey;
        allFlags = new HashMap<>();
        // allFlags.put(userPrivateKeyExistFlag, isUserPrivateKeyExist);
        allFlags.put(updateDataTaskCompleteFlag, isUpdateDataTaskComplete);
        allFlags.put(updateOnCancelledFlag, isUpdateOnCancelled);
        allFlags.put(gettingDataError, isGettingDataErrorOccurred);
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
                }
                /*
                else {
                    isUserPrivateKeyExist = true;
                    // Toast.makeText(anyActivity, "Email exist!", Toast.LENGTH_SHORT).show();
                }
                */
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
    public Map returnAllFlags() {
        allFlags.put(updateDataTaskCompleteFlag, isUpdateDataTaskComplete);
        // allFlags.put(userPrivateKeyExistFlag, isUserPrivateKeyExist);
        allFlags.put(updateOnCancelledFlag, isUpdateOnCancelled);
        allFlags.put(gettingDataError, isGettingDataErrorOccurred);
        return allFlags;
    }

    @Override
    public void getData() {
        final DatabaseReference rootReference = FirebaseDatabase.getInstance().getReference();
        rootReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if((dataSnapshot.child(userDataRootReference).child(userPrivateKey).exists())){
                    dataFromFirebase = dataSnapshot.child(userDataRootReference).child(userPrivateKey).getValue(Object.class);
                    setChanged();
                    notifyObservers();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                isGettingDataErrorOccurred = true;
            }
        });
    }

    @Override
    public Object returnData() {
        return dataFromFirebase;
    }


}
