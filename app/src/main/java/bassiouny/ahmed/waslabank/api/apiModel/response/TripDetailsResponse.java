package bassiouny.ahmed.waslabank.api.apiModel.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;
import bassiouny.ahmed.waslabank.model.TripDetails;

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
