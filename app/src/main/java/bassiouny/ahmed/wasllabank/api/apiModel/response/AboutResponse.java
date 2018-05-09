package bassiouny.ahmed.wasllabank.api.apiModel.response;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.wasllabank.api.apiModel.ApiKey;
import bassiouny.ahmed.wasllabank.model.About;

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
