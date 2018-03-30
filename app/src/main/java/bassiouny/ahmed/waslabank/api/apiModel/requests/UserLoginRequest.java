package bassiouny.ahmed.waslabank.api.apiModel.requests;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 30/03/18.
 */

@SuppressWarnings({"UnusedDeclaration"})
public class UserLoginRequest {
    @SerializedName(ApiKey.PHONE)
    private String phone;
    @SerializedName(ApiKey.PASSWORD)
    private String password;
    @SerializedName(ApiKey.NOTIFICATION_TOKEN)
    private String notificationToken;

    public UserLoginRequest(String phone, String password, String notificationToken) {
        this.phone = phone;
        this.password = password;
        this.notificationToken = notificationToken;
    }
}
