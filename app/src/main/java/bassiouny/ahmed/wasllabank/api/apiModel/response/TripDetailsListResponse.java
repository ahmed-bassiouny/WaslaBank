package bassiouny.ahmed.wasllabank.api.apiModel.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import bassiouny.ahmed.wasllabank.api.apiModel.ApiKey;
import bassiouny.ahmed.wasllabank.model.TripDetails;

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
