package bassiouny.ahmed.wasllabank.api.apiModel.response;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.wasllabank.api.apiModel.ApiKey;
import bassiouny.ahmed.wasllabank.model.TripDetails;

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
