package bassiouny.ahmed.waslabank.model;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 11/04/18.
 */

public class Feedback {

    @SerializedName(ApiKey.ID)
    private int id;
    @SerializedName(ApiKey.RATE)
    private String rate;
    @SerializedName(ApiKey.COMMENT)
    private String comment;
    @SerializedName(ApiKey.NAME)
    private String userName;
    @SerializedName(ApiKey.IMAGE)
    private String userImage;
}
