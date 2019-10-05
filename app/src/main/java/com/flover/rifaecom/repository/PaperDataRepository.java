package com.flover.rifaecom.repository;

import android.app.Activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.paperdb.Paper;

public class PaperDataRepository implements Repository{
    List<String> allKeys;

    /*
    private boolean isUpdateDataTaskComplete = false;
    private String updateDataTaskCompleteFlag = "isUpdateDataTaskComplete";
    private boolean isUpdateOnCancelled = false;
    private String updateOnCancelledFlag = "isUpdateOnCancelled";
    private String gettingDataErrorFlag = "isGettingDataErrorOccurred";
    private boolean isGettingDataErrorOccurred = false;
    private String  dataFetchedFlag = "isDataFetched";
    private boolean isDataFatched = false;
    */

    Map dataFromPaper;

    public PaperDataRepository(Activity anyActivity, List allKeys){
        Paper.init(anyActivity);
        this.allKeys = allKeys;
        dataFromPaper = new HashMap();
    }

    @Override
    public void updateData(Map dataSet) {
        for (String oneKey : allKeys){
            Paper.book().write(oneKey, dataSet.get(oneKey));
        }
    }

    @Override
    public Map returnAllFlags() {
        return null;
    }

    @Override
    public void getData() {
        for (String oneKey : allKeys){
            dataFromPaper.put(oneKey, Paper.book().read(oneKey));
        }
    }

    @Override
    public void getDataByID() {

    }

    @Override
    public Object returnData() {
        return dataFromPaper;
    }

    @Override
    public void deleteData() {
        // Will modify soon
        Paper.book().destroy();
    }
}