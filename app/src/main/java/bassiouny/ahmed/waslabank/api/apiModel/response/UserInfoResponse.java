package bassiouny.ahmed.waslabank.api.apiModel.response;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;
import bassiouny.ahmed.waslabank.model.UserInfo;

/**
 * Created by bassiouny on 12/04/18.
 */

public class UserInfoResponse extends ParentResponse<UserInfo> {

    @SerializedName(ApiKey.DATA)
    private UserInfo userInfo;

    @Override
    public UserInfo getObject() {
        if (userInfo == null)
            userInfo = new UserInfo();
        return userInfo;
    }
}
