package bassiouny.ahmed.waslabank.activities.controller;

import bassiouny.ahmed.waslabank.api.ApiRequests;
import bassiouny.ahmed.waslabank.api.apiModel.requests.CreateTripRequest;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;

/**
 * Created by bassiouny on 15/04/18.
 */

public class CreateRequestController {

    public void createTrip(CreateTripRequest.Builder builder, BaseResponseInterface anInterface) {
        ApiRequests.createTrip(builder.build(), anInterface);
    }
}
