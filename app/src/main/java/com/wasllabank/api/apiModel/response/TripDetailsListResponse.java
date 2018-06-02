package com.wasllabank.api.apiModel.response;

import com.wasllabank.model.TripDetails;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import com.wasllabank.api.apiModel.ApiKey;

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
