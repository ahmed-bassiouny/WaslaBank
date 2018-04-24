package bassiouny.ahmed.waslabank.api.apiModel.requests;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;

/**
 * Created by bassiouny on 24/04/18.
 */

public class ChangePasswordRequest {

    @SerializedName(ApiKey.ID)
    private int userId;
    @SerializedName(ApiKey.OLD_PASSWORD)
    private String oldPassword;
    @SerializedName(ApiKey.PASSWORD)
    private String newPassword;

    public ChangePasswordRequest(int userId, String oldPassword, String newPassword) {
        this.userId = userId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
