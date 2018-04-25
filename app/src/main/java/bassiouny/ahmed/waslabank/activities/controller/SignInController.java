package bassiouny.ahmed.waslabank.activities.controller;

import android.content.Context;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import bassiouny.ahmed.waslabank.api.ApiRequests;
import bassiouny.ahmed.waslabank.api.apiModel.requests.UserLoginRequest;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.waslabank.utils.Constant;
import bassiouny.ahmed.waslabank.utils.SharedPrefKey;

/**
 * Created by bassiouny on 30/03/18.
 */

public class SignInController {
    private Context context;

    public SignInController(Context context) {
        this.context = context;
    }

    public void login(String phone , String password,BaseResponseInterface anInterface){
        // create UserLoginRequest Object
        UserLoginRequest.Builder builder = new UserLoginRequest.Builder();
        builder.phone(phone);
        builder.password(password);
        builder.notificationToken(SharedPrefManager.getString(SharedPrefKey.TOKEN));
        ApiRequests.login(builder.build(),anInterface);
    }
}
