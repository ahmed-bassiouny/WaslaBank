package bassiouny.ahmed.wasllabank.fragments.controller;

import android.content.Context;
import android.widget.ArrayAdapter;

import bassiouny.ahmed.genericmanager.SharedPrefManager;
import bassiouny.ahmed.wasllabank.R;
import bassiouny.ahmed.wasllabank.api.ApiRequests;
import bassiouny.ahmed.wasllabank.api.apiModel.requests.UserSignUpRequest;
import bassiouny.ahmed.wasllabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.wasllabank.utils.SharedPrefKey;

/**
 * Created by bassiouny on 30/03/18.
 */

public class SignUpCarController {
    private Context context;

    public SignUpCarController(Context context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    public ArrayAdapter getCarSizeAdapter(){
        // get array adapter contain car size
        return new ArrayAdapter(context, R.layout.spinner_row,context.getResources().getStringArray(R.array.car_size));
    }
    public void saveUserObject(String carNumber,String licenseNumber,String carSize) {
        // get user data from shared pref
        UserSignUpRequest.Builder builder = SharedPrefManager.getObject(SharedPrefKey.USER_REGISTER,UserSignUpRequest.Builder.class);
        builder.carNumber(carNumber);
        builder.licenseNumber(licenseNumber);
        builder.carSize(carSize);
        // save user data in shared pref
        SharedPrefManager.setObject(SharedPrefKey.USER_REGISTER,builder);
    }
    public void checkLicenseCarNumber(String carNumber , String licenseNumber , BaseResponseInterface anInterface){
        UserSignUpRequest.Builder builder = new UserSignUpRequest.Builder();
        builder.carNumber(carNumber);
        builder.licenseNumber(licenseNumber);
        ApiRequests.checkLicenseCarNumber(builder.build(),anInterface);
    }
}
