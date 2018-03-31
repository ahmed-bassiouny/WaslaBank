package bassiouny.ahmed.waslabank.fragments.controller;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.io.File;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.api.ApiRequests;
import bassiouny.ahmed.waslabank.api.apiModel.requests.UserSignUpRequest;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.waslabank.utils.MyUtils;
import bassiouny.ahmed.waslabank.utils.SharedPrefKey;

/**
 * Created by bassiouny on 30/03/18.
 */

public class SignUpUserDetailsController {

    private Context context;

    public SignUpUserDetailsController(Context context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    public ArrayAdapter getCitiesAdapter() {
        // get array adapter contain cities
        return new ArrayAdapter(context, R.layout.spinner_row, context.getResources().getStringArray(R.array.cities));
    }

    public void registerUser(String identifyNumber, String gender, String city, File image, BaseResponseInterface anInterface) {
        // save user data in shared pref
        UserSignUpRequest.Builder builder = new UserSignUpRequest.Builder();
        builder.identifyNumber(identifyNumber);
        builder.gender(gender);
        builder.city(city);
        // send object to data base
        ApiRequests.signUp(builder.build(), MyUtils.convertFileToPart(image), anInterface);
    }
}
