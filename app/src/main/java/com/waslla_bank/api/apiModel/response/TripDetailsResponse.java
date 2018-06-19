package com.waslla_bank.api.apiModel.response;

import com.waslla_bank.model.TripDetails;
import com.google.gson.annotations.SerializedName;

import com.waslla_bank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 11/04/18.
 */

public class TripDetailsResponse extends ParentResponse<TripDetails> {

    @SerializedName(ApiKey.DATA)
    private TripDetails tripDetails;

    @Override
    public TripDetails getObject() {
        return tripDetails;
    }
}
