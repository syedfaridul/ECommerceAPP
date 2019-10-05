package com.flover.rifaecom.repository;

import android.net.Uri;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class FirebaseStorageRepository extends Observable implements Repository{
    private Uri productImageUri;
    private String  productImageRef;
    private String productRandomKey;
    private StorageReference firebaseStorage;
    private Map allFlags;

    private String downloadFileUrl;
    private String downloadFileUrlFlag = "downloadFileUrl";

    private String uploadFileSuccessFlag = "isUploadSuccess";
    private Boolean isUploadSuccess = true;

    @Override
    public void updateData(Map dataSet) {
        productRandomKey = dataSet.get("productRandomKey").toString();
        productImageRef = dataSet.get("productRootReference").toString();
        productImageUri = (Uri) dataSet.get("productImageUri");

        allFlags = new HashMap();
        firebaseStorage = FirebaseStorage.getInstance().getReference().child(productImageRef);

        final StorageReference storageReference = firebaseStorage.child(productRandomKey);
        final UploadTask uploadTask = storageReference.putFile(productImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                isUploadSuccess = false;
                setChanged();
                notifyObservers();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()){
                            isUploadSuccess = false;
                            setChanged();
                            notifyObservers();
                            throw task.getException();
                        }
                        return storageReference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        setChanged();
                        if (task.isSuccessful()){
                            downloadFileUrl = task.getResult().toString();
                            isUploadSuccess = true;
                            setChanged();
                            notifyObservers();
                        }
                    }
                });
            }
        });
    }

    @Override
    public Map returnAllFlags() {
        allFlags.put(uploadFileSuccessFlag, isUploadSuccess);
        allFlags.put(downloadFileUrlFlag, downloadFileUrl);

        return allFlags;
    }

    @Override
    public void getData() {

    }

    @Override
    public void getDataByID() {

    }

    @Override
    public Object returnData() {
        return null;
    }

    @Override
    public void deleteData() {

    }
}
