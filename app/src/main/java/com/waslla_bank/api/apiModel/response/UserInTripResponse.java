package com.waslla_bank.api.apiModel.response;

import com.google.gson.annotations.SerializedName;
import com.waslla_bank.api.apiModel.ApiKey;
import com.waslla_bank.model.UserInTrip;

import java.util.List;

public class UserInTripResponse extends ParentResponse<List<UserInTrip>> {

    @SerializedName(ApiKey.DATA)
    private List<UserInTrip> userInTrip;

    @Override
    public List<UserInTrip> getObject() {
        return userInTrip;
    }
}
