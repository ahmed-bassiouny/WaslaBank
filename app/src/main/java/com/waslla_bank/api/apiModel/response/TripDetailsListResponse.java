package com.waslla_bank.api.apiModel.response;

import com.waslla_bank.model.TripDetails;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import com.waslla_bank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 11/04/18.
 */

public class TripDetailsListResponse extends ParentResponse<List<TripDetails>> {

    @SerializedName(ApiKey.DATA)
    private List<TripDetails> tripDetails;

    @Override
    public List<TripDetails> getObject() {
        return tripDetails;
    }
}
