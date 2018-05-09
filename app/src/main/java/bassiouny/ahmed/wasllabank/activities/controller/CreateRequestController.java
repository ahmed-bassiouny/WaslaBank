package bassiouny.ahmed.wasllabank.activities.controller;

import bassiouny.ahmed.wasllabank.api.ApiRequests;
import bassiouny.ahmed.wasllabank.api.apiModel.requests.CreateTripRequest;
import bassiouny.ahmed.wasllabank.interfaces.BaseResponseInterface;

/**
 * Created by bassiouny on 15/04/18.
 */

public class CreateRequestController {

    public void createTrip(CreateTripRequest.Builder builder, BaseResponseInterface anInterface) {
        ApiRequests.createTrip(builder.build(), anInterface);
    }
}
