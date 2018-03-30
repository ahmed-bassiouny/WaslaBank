package bassiouny.ahmed.waslabank.activities.controller;

import android.content.Context;

import bassiouny.ahmed.waslabank.api.ApiRequests;
import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;
import bassiouny.ahmed.waslabank.api.apiModel.ParentResponse;
import bassiouny.ahmed.waslabank.api.apiModel.requests.UserLoginRequest;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;

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
        UserLoginRequest object = new UserLoginRequest(phone,password,"gdfkljgndl");
        ApiRequests.login(object,anInterface);
    }
}
