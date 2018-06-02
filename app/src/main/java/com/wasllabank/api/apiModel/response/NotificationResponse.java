package com.wasllabank.api.apiModel.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import com.wasllabank.api.apiModel.ApiKey;
import com.wasllabank.model.Notification;

/**
 * Created by bassiouny on 11/04/18.
 */

public class NotificationResponse extends ParentResponse<List<Notification>> {

    @SerializedName(ApiKey.DATA)
    private List<Notification> notifications;

    @Override
    public List<Notification> getObject() {
        return notifications;
    }
}
