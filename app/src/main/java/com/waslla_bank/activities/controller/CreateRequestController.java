package com.waslla_bank.activities.controller;

import com.waslla_bank.api.ApiRequests;
import com.waslla_bank.api.apiModel.requests.CreateTripRequest;
import com.waslla_bank.interfaces.BaseResponseInterface;

/**
 * Created by bassiouny on 15/04/18.
 */

public class CreateRequestController {

    public void createTrip(CreateTripRequest.Builder builder, BaseResponseInterface anInterface) {
        ApiRequests.createTrip(builder.build(), anInterface);
    }
}
