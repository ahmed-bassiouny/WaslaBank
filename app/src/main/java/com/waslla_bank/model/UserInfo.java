package com.waslla_bank.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import com.waslla_bank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 12/04/18.
 */

// this class represent user data view in home screen , and profile
public class UserInfo {

    @SerializedName(ApiKey.TOTAL_POINT)
    private int point;
    @SerializedName(ApiKey.TOTAL_REQUESTS)
    private int requests;
    @SerializedName(ApiKey.TOTAL_USERS)
    private int orders;
    @SerializedName(ApiKey.TOTAL_RATE)
    private float rate;
    @SerializedName(ApiKey.FEEDBACK)
    private List<Feedback> feedbacks;
    @SerializedName(ApiKey.CURRENT_REQUEST_ID)
    private int currentRequest;

    public int getPoint() {
        return point;
    }

    public int getRequests() {
        return requests;
    }

    public int getOrders() {
        return orders;
    }

    public float getRate() {
        return rate;
    }

    public List<Feedback> getFeedbacks() {
        if(feedbacks == null)
            feedbacks = new ArrayList<>();
        return feedbacks;
    }

    public int getCurrentRequest() {
        return currentRequest;
    }

    public void clearFeedback(){
        feedbacks.clear();
    }

    public UserInfo() {
    }

    public UserInfo(UserInfo userInfo) {
        this.point = userInfo.point;
        this.requests = userInfo.requests;
        this.orders = userInfo.orders;
        this.rate = userInfo.rate;
    }
}
