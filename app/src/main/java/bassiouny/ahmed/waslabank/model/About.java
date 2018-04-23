package bassiouny.ahmed.waslabank.model;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 23/04/18.
 */

public class About {

    @SerializedName(ApiKey.BODY)
    private String body;
}
