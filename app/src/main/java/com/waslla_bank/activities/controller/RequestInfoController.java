package com.waslla_bank.activities.controller;

import android.app.Activity;

import com.waslla_bank.api.ApiRequests;
import com.waslla_bank.interfaces.BaseResponseInterface;

/**
 * Created by bassiouny on 12/04/18.
 */

public class RequestInfoController {

    private Activity activity;

    public RequestInfoController(Activity activity) {
        this.activity = activity;
    }

    public void getTripRequestById(int tripId,final BaseResponseInterface anInterface){
        ApiRequests.getTripRequestById(tripId,anInterface);
    }
}
