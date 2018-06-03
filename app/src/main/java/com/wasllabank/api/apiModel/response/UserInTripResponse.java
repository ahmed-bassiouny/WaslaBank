package com.wasllabank.api.apiModel.response;

import com.google.gson.annotations.SerializedName;
import com.wasllabank.api.apiModel.ApiKey;
import com.wasllabank.model.UserInTrip;

import java.util.List;

public class UserInTripResponse extends ParentResponse<List<UserInTrip>> {

    @SerializedName(ApiKey.DATA)
    private List<UserInTrip> userInTrip;

    @Override
    public List<UserInTrip> getObject() {
        return userInTrip;
    }
}
