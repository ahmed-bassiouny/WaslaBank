package bassiouny.ahmed.wasllabank.model;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.wasllabank.api.apiModel.ApiKey;
import bassiouny.ahmed.wasllabank.utils.MyUtils;

/**
 * Created by bassiouny on 23/04/18.
 */

public class About {

    @SerializedName(ApiKey.BODY)
    private String body;

    public String getBody() {
        return MyUtils.getString(body);
    }
}
