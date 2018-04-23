package bassiouny.ahmed.waslabank.api.apiModel.response;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;
import bassiouny.ahmed.waslabank.model.About;
import bassiouny.ahmed.waslabank.model.TripDetails;

/**
 * Created by bassiouny on 11/04/18.
 */

public class AboutResponse extends ParentResponse<About> {

    @SerializedName(ApiKey.DATA)
    private About about;

    @Override
    public About getObject() {
        return about;
    }
}
