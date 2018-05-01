package bassiouny.ahmed.waslabank.model;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;
import bassiouny.ahmed.waslabank.utils.MyUtils;

/**
 * Created by bassiouny on 01/05/18.
 */
@SuppressWarnings({"UnusedDeclaration"})
public class UserInTrip {

    @SerializedName(ApiKey.USER_ID)
    private int userId;
    @SerializedName(ApiKey.NAME)
    private String userName;
    @SerializedName(ApiKey.PHONE)
    private String userPhone;
    @SerializedName(ApiKey.IMAGE)
    private String userImage;
    @SerializedName(ApiKey.IS_ACCEPTED)
    private boolean isAccepted;

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return MyUtils.getString(userName);
    }

    public String getUserPhone() {
        return MyUtils.getString(userPhone);
    }

    public String getUserImage() {
        return MyUtils.getString(userImage);
    }

    public boolean isAccepted() {
        return isAccepted;
    }
}
