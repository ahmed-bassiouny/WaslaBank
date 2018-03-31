package bassiouny.ahmed.waslabank.api;

import bassiouny.ahmed.waslabank.api.apiModel.response.GenericResponse;
import bassiouny.ahmed.waslabank.api.apiModel.response.ParentResponse;
import bassiouny.ahmed.waslabank.api.apiModel.requests.UserLoginRequest;
import bassiouny.ahmed.waslabank.api.apiModel.requests.UserSignUpRequest;
import bassiouny.ahmed.waslabank.api.apiModel.response.UserResponse;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;
import bassiouny.ahmed.waslabank.model.User;
import bassiouny.ahmed.waslabank.utils.MyUtils;
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
    private static <T> void checkValidResult(T response, BaseResponseInterface anInterface) {
        // get response body
        Response<ParentResponse> parentResponse = (Response<ParentResponse>)response;
        ParentResponse body = parentResponse.body();
        if (body == null) {
            // if body == null this mean respond from server total bad
            anInterface.onFailed(apiError);
            return;
        }
        // check response code from server
        if (parentResponse.code() == 200) {
            // check status key from server
            if (body.getStatus()) {
                // i make sure this object which i need
                anInterface.onSuccess(body.getObject());
            } else {
                // something happened and server tell me what i should do
                anInterface.onFailed(body.getMessage());
            }
        } else {
            // this case mean response code not equal 200
            anInterface.onFailed(parentResponse.message());
        }
    }

    // login user
    // url => login
    // parameter => phone , password , notification token
    public static void login(UserLoginRequest userLoginRequest, final BaseResponseInterface<User> anInterface) {
        Call<UserResponse> response = ApiConfig.httpApiInterface.login(userLoginRequest);
        response.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }

    // register user
    // url =>
    // parameter => user information
    public static void signUp(UserSignUpRequest userSignUpRequest, MultipartBody.Part part, final BaseResponseInterface<User> anInterface) {
        Call<UserResponse> response = ApiConfig.httpApiInterface.signUp(
                part, MyUtils.createPartFromString(userSignUpRequest.getName())
                , MyUtils.createPartFromString(userSignUpRequest.getPhone())
                , MyUtils.createPartFromString(userSignUpRequest.getEmail())
                , MyUtils.createPartFromString(userSignUpRequest.getPassword())
                , MyUtils.createPartFromString(userSignUpRequest.getCarNumber())
                , MyUtils.createPartFromString(userSignUpRequest.getLicenseNumber())
                , MyUtils.createPartFromString(userSignUpRequest.getCarSize())
                , MyUtils.createPartFromString(userSignUpRequest.getIdentifyNumber())
                , MyUtils.createPartFromString(userSignUpRequest.getGender())
                , MyUtils.createPartFromString(userSignUpRequest.getCity())
                , MyUtils.createPartFromString(userSignUpRequest.getNotificationToken()));
        response.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }

    // check user data
    // url => registration/check_email_phone
    // parameter => phone , email
    public static void checkEmailAndPhone(UserSignUpRequest userSignUpRequest, final BaseResponseInterface anInterface) {
        Call<GenericResponse> response = ApiConfig.httpApiInterface.checkUserData("registration/check_email_phone", userSignUpRequest);
        response.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }

    // check user data
    // url => registration/check_license_car_number
    // parameter => license number , car number
    public static void checkLicenseCarNumber(UserSignUpRequest userSignUpRequest, final BaseResponseInterface anInterface) {
        Call<GenericResponse> response = ApiConfig.httpApiInterface.checkUserData("registration/check_car_number_license_number", userSignUpRequest);
        response.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(Call<GenericResponse> call, Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }
}
