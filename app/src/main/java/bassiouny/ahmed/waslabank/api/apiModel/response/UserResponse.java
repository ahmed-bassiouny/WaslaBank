package bassiouny.ahmed.waslabank.api.apiModel.response;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;
import bassiouny.ahmed.waslabank.model.User;

/**
 * Created by bassiouny on 31/03/18.
 */

public class UserResponse extends ParentResponse<User>{

    @SerializedName(ApiKey.DATA)
    private User user;

    @Override
    public User getObject() {
        if(user == null)
            user = new User();
        return user;
    }
}
