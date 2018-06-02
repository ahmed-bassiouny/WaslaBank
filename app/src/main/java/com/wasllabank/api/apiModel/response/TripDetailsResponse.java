package com.wasllabank.api.apiModel.response;

import com.wasllabank.model.TripDetails;
import com.google.gson.annotations.SerializedName;

import com.wasllabank.api.apiModel.ApiKey;

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
