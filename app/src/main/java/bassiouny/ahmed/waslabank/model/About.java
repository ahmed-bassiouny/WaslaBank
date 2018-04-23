package bassiouny.ahmed.waslabank.model;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;
import bassiouny.ahmed.waslabank.utils.MyUtils;

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
