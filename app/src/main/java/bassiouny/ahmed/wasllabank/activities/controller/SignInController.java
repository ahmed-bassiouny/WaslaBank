package bassiouny.ahmed.wasllabank.activities.controller;

import android.content.Context;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import bassiouny.ahmed.wasllabank.api.ApiRequests;
import bassiouny.ahmed.wasllabank.api.apiModel.requests.UserLoginRequest;
import bassiouny.ahmed.wasllabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.wasllabank.utils.SharedPrefKey;

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
