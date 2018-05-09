package bassiouny.ahmed.wasllabank.model;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.wasllabank.api.apiModel.ApiKey;
import bassiouny.ahmed.wasllabank.utils.MyUtils;

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
    private boolean loading;

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

    public void setAccepted() {
        isAccepted = true;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }
}
