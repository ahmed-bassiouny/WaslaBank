package bassiouny.ahmed.wasllabank.model;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.wasllabank.api.apiModel.ApiKey;
import bassiouny.ahmed.wasllabank.utils.MyUtils;

/**
 * Created by bassiouny on 11/04/18.
 */

public class Feedback {

    @SerializedName(ApiKey.RATE)
    private String rate;
    @SerializedName(ApiKey.COMMENT)
    private String comment;
    @SerializedName(ApiKey.NAME)
    private String userName;
    @SerializedName(ApiKey.IMAGE)
    private String userImage;

    public float getRate() {
        try {
            return Float.parseFloat(MyUtils.getString(rate));
        }catch (Exception e){
            return 0;
        }
    }

    public String getComment() {
        return comment;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserImage() {
        return userImage;
    }
}
