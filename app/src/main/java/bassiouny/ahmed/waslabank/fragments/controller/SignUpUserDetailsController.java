package bassiouny.ahmed.waslabank.fragments.controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;

import java.io.File;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import bassiouny.ahmed.waslabank.R;
import bassiouny.ahmed.waslabank.api.ApiRequests;
import bassiouny.ahmed.waslabank.api.apiModel.requests.UserSignUpRequest;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.waslabank.utils.Constant;
import bassiouny.ahmed.waslabank.utils.MyUtils;
import bassiouny.ahmed.waslabank.utils.SharedPrefKey;

/**
 * Created by bassiouny on 30/03/18.
 */

public class SignUpUserDetailsController {

    private Context context;
    public final int PICK_IMAGE = 1;

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
        builder.notificationToken(Constant.NOTIFICATION_TOKEN);
        // save user data in shared pref
        if(image == null)
            ApiRequests.signUp(builder.build(),null, anInterface);
        else
            ApiRequests.signUp(builder.build(),MyUtils.convertFileToPart(image), anInterface);
    }
    public void selectImage(Fragment fragment){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        fragment.startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }
}
