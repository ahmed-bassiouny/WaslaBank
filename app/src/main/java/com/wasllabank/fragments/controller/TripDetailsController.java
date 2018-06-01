package com.wasllabank.fragments.controller;

import android.support.v4.app.Fragment;

import com.wasllabank.api.ApiRequests;
import com.wasllabank.api.apiModel.requests.TripStatusRequest;
import com.wasllabank.interfaces.BaseResponseInterface;
import com.wasllabank.api.apiModel.requests.StartTripRequest;

/**
 * Created by bassiouny on 18/04/18.
 */

public class TripDetailsController {

    private Fragment fragment;


    public void finishTrip(int requestId, boolean isFinish, boolean isCancel,String comment, BaseResponseInterface anInterface) {
        TripStatusRequest.Builder builder = new TripStatusRequest.Builder();
        builder.requestId(requestId);
        builder.isCanceled(isCancel);
        builder.isFinished(isFinish);
        builder.comment(comment);
        ApiRequests.finishTrip(builder.build(), anInterface);
    }

    public void startTrip(int requestId, int userId, boolean isRunning,double startLat,double startLng, BaseResponseInterface anInterface) {
        StartTripRequest.Builder builder = new StartTripRequest.Builder();
        builder.requestId(requestId);
        builder.userId(userId);
        builder.isRunning(isRunning);
        builder.startPointLat(startLat);
        builder.startPointLng(startLng);
        ApiRequests.startTrip(builder.build(), anInterface);
    }

    public void joinTrip(int requestId, int userId, boolean isJoined,String comment, BaseResponseInterface anInterface) {
        StartTripRequest.Builder builder = new StartTripRequest.Builder();
        builder.requestId(requestId);
        builder.userId(userId);
        builder.isJoined(isJoined);
        builder.comment(comment);
        ApiRequests.joinTrip(builder.build(), anInterface);
    }
}
