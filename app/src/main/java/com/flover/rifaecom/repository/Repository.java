package com.flover.rifaecom.repository;

// import android.app.Activity;

import java.util.Map;

public interface Repository{
    void updateData(/*Activity anyActivity,*/ Map dataSet);
    Map returnAllFlags();
    void getData();
    Object returnData();
}
