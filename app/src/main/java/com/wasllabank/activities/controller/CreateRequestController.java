package com.wasllabank.activities.controller;

import com.wasllabank.api.ApiRequests;
import com.wasllabank.api.apiModel.requests.CreateTripRequest;
import com.wasllabank.interfaces.BaseResponseInterface;

/**
 * Created by bassiouny on 15/04/18.
 */

public class CreateRequestController {

    public void createTrip(CreateTripRequest.Builder builder, BaseResponseInterface anInterface) {
        ApiRequests.createTrip(builder.build(), anInterface);
    }
}
