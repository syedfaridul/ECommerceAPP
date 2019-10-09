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

public class FirebaseDataBaseRepository extends Observable implements Repository{
    // private boolean isUserPrivateKeyExist = false;
    // private String userPrivateKeyExistFlag = "isUserPrivateKeyExist";
    private boolean isUpdateDataTaskComplete = false;
    private String updateDataTaskCompleteFlag = "isUpdateDataTaskComplete";
    private boolean isUpdateOnCancelled = false;
    private String updateOnCancelledFlag = "isUpdateOnCancelled";
    private String gettingDataErrorFlag = "isGettingDataErrorOccurred";
    private boolean isGettingDataErrorOccurred = false;
    private String  dataFetchedFlag = "isDataFetched";
    private boolean isDataFetched = false;

    private Object dataFromFirebase;


    private Map<String, Boolean> allFlags;

    private String userDataRootReference;
    private String userPrivateKey;

    public FirebaseDataBaseRepository(String userDataRootReference, String userPrivateKey) {
        this.userDataRootReference = userDataRootReference;
        this.userPrivateKey = userPrivateKey;
        allFlags = new HashMap<>();
        // allFlags.put(userPrivateKeyExistFlag, isUserPrivateKeyExist);
        allFlags.put(updateDataTaskCompleteFlag, isUpdateDataTaskComplete);
        allFlags.put(updateOnCancelledFlag, isUpdateOnCancelled);
        allFlags.put(gettingDataErrorFlag, isGettingDataErrorOccurred);
        allFlags.put(dataFetchedFlag, isDataFetched);
    }

    public FirebaseDataBaseRepository(String userDataRootReference) {
        this.userDataRootReference = userDataRootReference;
        allFlags = new HashMap<>();
        // allFlags.put(userPrivateKeyExistFlag, isUserPrivateKeyExist);
        allFlags.put(updateDataTaskCompleteFlag, isUpdateDataTaskComplete);
        allFlags.put(updateOnCancelledFlag, isUpdateOnCancelled);
        allFlags.put(gettingDataErrorFlag, isGettingDataErrorOccurred);
        allFlags.put(dataFetchedFlag, isDataFetched);
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
        allFlags.put(gettingDataErrorFlag, isGettingDataErrorOccurred);
        allFlags.put(dataFetchedFlag, isDataFetched);
        return allFlags;
    }

    @Override
    public void getData() {
        final DatabaseReference rootReference = FirebaseDatabase.getInstance().getReference();
        rootReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if((dataSnapshot.child(userDataRootReference).exists())){
                    dataFromFirebase = dataSnapshot.child(userDataRootReference).getValue(Object.class);
                    isDataFetched = true;
                }
                setChanged();
                notifyObservers();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                isGettingDataErrorOccurred = true;
            }
        });
    }

    @Override
    public void getDataByID() {
        final DatabaseReference rootReference = FirebaseDatabase.getInstance().getReference();
        rootReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if((dataSnapshot.child(userDataRootReference).child(userPrivateKey).exists())){
                    dataFromFirebase = dataSnapshot.child(userDataRootReference).child(userPrivateKey).getValue(Object.class);
                    isDataFetched = true;
                }
                setChanged();
                notifyObservers();
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

    @Override
    public void deleteData() {
        // https://stackoverflow.com/questions/37390864/how-to-delete-from-firebase-realtime-database
    }


}
