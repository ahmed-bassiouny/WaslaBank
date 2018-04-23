package bassiouny.ahmed.waslabank.fragments.controller;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import bassiouny.ahmed.waslabank.api.ApiRequests;
import bassiouny.ahmed.waslabank.api.apiModel.requests.StartTripRequest;
import bassiouny.ahmed.waslabank.api.apiModel.requests.TripStatusRequest;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;

/**
 * Created by bassiouny on 18/04/18.
 */

public class TripDetailsController {

    private Fragment fragment;


    public void finishTrip(int requestId, boolean isFinish, boolean isCancel, BaseResponseInterface anInterface) {
        TripStatusRequest.Builder builder = new TripStatusRequest.Builder();
        builder.requestId(requestId);
        builder.isCanceled(isCancel);
        builder.isFinished(isFinish);
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

    public void joinTrip(int requestId, int userId, boolean isJoined, BaseResponseInterface anInterface) {
        StartTripRequest.Builder builder = new StartTripRequest.Builder();
        builder.requestId(requestId);
        builder.userId(userId);
        builder.isJoined(isJoined);
        ApiRequests.joinTrip(builder.build(), anInterface);
    }
}
