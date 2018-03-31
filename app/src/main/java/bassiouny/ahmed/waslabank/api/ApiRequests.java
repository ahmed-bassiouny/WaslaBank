package bassiouny.ahmed.waslabank.api;

import java.io.File;

import bassiouny.ahmed.waslabank.api.apiModel.ParentResponse;
import bassiouny.ahmed.waslabank.api.apiModel.requests.UserLoginRequest;
import bassiouny.ahmed.waslabank.api.apiModel.requests.UserSignUpRequest;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bassiouny on 26/03/18.
 */

public class ApiRequests {
    private static final String apiError = "Server not responding";

    @SuppressWarnings("unchecked")
    private static <T> void checkValidResult(Response<ParentResponse> response, BaseResponseInterface anInterface, T t) {
        // get response body
        ParentResponse parentResponse = response.body();
        if (parentResponse == null) {
            // if body == null this mean respond from server total bad
            anInterface.onFailed(apiError);
            return;
        }
        // check response code from server
        if (response.code() == 200) {
            // check status key from server
            if (parentResponse.getStatus()) {
                // i make sure this object which i need
                anInterface.onSuccess(t);
            } else {
                // something happened and server tell me what i should do
                anInterface.onFailed(parentResponse.getMessage());
            }
        } else {
            // this case mean response code not equal 200
            anInterface.onFailed(response.message());
        }
    }

    // login user
    // url => login
    // parameter => phone , password , notification token
    public static void login(UserLoginRequest userLoginRequest, final BaseResponseInterface anInterface) {
        Call<ParentResponse> response = ApiConfig.httpApiInterface.login(userLoginRequest);
        response.enqueue(new Callback<ParentResponse>() {
            @Override
            public void onResponse(Call<ParentResponse> call, Response<ParentResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface, null);
            }

            @Override
            public void onFailure(Call<ParentResponse> call, Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }
    // register user
    // url =>
    // parameter => user information
    public static void signUp(UserSignUpRequest userSignUpRequest, MultipartBody.Part image, final BaseResponseInterface anInterface) {
        Call<ParentResponse> response = ApiConfig.httpApiInterface.signUp(userSignUpRequest,image);
        response.enqueue(new Callback<ParentResponse>() {
            @Override
            public void onResponse(Call<ParentResponse> call, Response<ParentResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface, null);
            }

            @Override
            public void onFailure(Call<ParentResponse> call, Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }
    // check user data
    // url => registration/check_email_phone
    // parameter => phone , email
    public static void checkEmailAndPhone(UserSignUpRequest userSignUpRequest,final BaseResponseInterface anInterface) {
        Call<ParentResponse> response = ApiConfig.httpApiInterface.checkUserData("registration/check_email_phone",userSignUpRequest);
        response.enqueue(new Callback<ParentResponse>() {
            @Override
            public void onResponse(Call<ParentResponse> call, Response<ParentResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface, null);
            }

            @Override
            public void onFailure(Call<ParentResponse> call, Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }

    // check user data
    // url => registration/check_license_car_number
    // parameter => license number , car number
    public static void checkLicenseCarNumber(UserSignUpRequest userSignUpRequest,final BaseResponseInterface anInterface) {
        Call<ParentResponse> response = ApiConfig.httpApiInterface.checkUserData("registration/check_car_number_license_number",userSignUpRequest);
        response.enqueue(new Callback<ParentResponse>() {
            @Override
            public void onResponse(Call<ParentResponse> call, Response<ParentResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface, null);
            }

            @Override
            public void onFailure(Call<ParentResponse> call, Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }
}
