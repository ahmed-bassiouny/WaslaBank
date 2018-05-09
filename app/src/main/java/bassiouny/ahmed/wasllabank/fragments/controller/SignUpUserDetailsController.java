package bassiouny.ahmed.wasllabank.fragments.controller;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.io.File;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import bassiouny.ahmed.wasllabank.R;
import bassiouny.ahmed.wasllabank.api.ApiRequests;
import bassiouny.ahmed.wasllabank.api.apiModel.requests.UserSignUpRequest;
import bassiouny.ahmed.wasllabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.wasllabank.utils.MyUtils;
import bassiouny.ahmed.wasllabank.utils.SharedPrefKey;

/**
 * Created by bassiouny on 30/03/18.
 */

public class SignUpUserDetailsController {

    private Context context;
    public final int MY_PERMISSIONS_REQUEST_IMAGE = 2;

    public SignUpUserDetailsController(Context context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    public ArrayAdapter getCitiesAdapter() {
        // get array adapter contain cities
        return new ArrayAdapter(context, R.layout.spinner_row, context.getResources().getStringArray(R.array.cities));
    }

    public void registerUser(String identifyNumber, String gender, String city, File image, BaseResponseInterface anInterface) {
        // get user data from shared pref
        UserSignUpRequest.Builder builder = SharedPrefManager.getObject(SharedPrefKey.USER_REGISTER,UserSignUpRequest.Builder.class);
        builder.identifyNumber(identifyNumber);
        builder.gender(gender);
        builder.city(city);
        builder.notificationToken(SharedPrefManager.getString(SharedPrefKey.TOKEN));
        // save user data in shared pref
        if(image == null)
            ApiRequests.signUp(builder.build(),null, anInterface);
        else
            ApiRequests.signUp(builder.build(),MyUtils.convertFileToPart(image), anInterface);
    }
}
