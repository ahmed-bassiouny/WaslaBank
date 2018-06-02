package com.wasllabank.api;

import android.support.annotation.NonNull;

import com.wasllabank.api.apiModel.requests.AcceptRejectUser;
import com.wasllabank.api.apiModel.requests.FeedbackRequest;
import com.wasllabank.api.apiModel.requests.FinishTripRequest;
import com.wasllabank.api.apiModel.requests.TripStatusRequest;
import com.wasllabank.api.apiModel.requests.UserLoginRequest;
import com.wasllabank.api.apiModel.response.AboutResponse;
import com.wasllabank.api.apiModel.response.GenericResponse;
import com.wasllabank.api.apiModel.response.NotificationResponse;
import com.wasllabank.api.apiModel.response.ParentResponse;
import com.wasllabank.api.apiModel.response.TripDetailsListResponse;
import com.wasllabank.model.Notification;
import com.wasllabank.model.TripDetails;
import com.wasllabank.model.User;
import com.wasllabank.model.UserInfo;
import com.wasllabank.utils.MyApplication;
import com.wasllabank.utils.MyUtils;
import com.wasllabank.utils.SharedPrefKey;

import java.io.File;
import java.util.List;

import bassiouny.ahmed.genericmanager.SharedPrefManager;

import com.wasllabank.api.apiModel.requests.ChangePasswordRequest;
import com.wasllabank.api.apiModel.requests.ContactUsRequest;
import com.wasllabank.api.apiModel.requests.CreateTripRequest;
import com.wasllabank.api.apiModel.requests.StartTripRequest;
import com.wasllabank.api.apiModel.requests.TripDetailsRequest;
import com.wasllabank.api.apiModel.requests.TripsByDate;
import com.wasllabank.api.apiModel.requests.UserSignUpRequest;
import com.wasllabank.api.apiModel.response.TripDetailsResponse;
import com.wasllabank.api.apiModel.response.UserInfoResponse;
import com.wasllabank.api.apiModel.response.UserResponse;
import com.wasllabank.interfaces.BaseResponseInterface;
import com.wasllabank.model.About;

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
        Response<ParentResponse> parentResponse = (Response<ParentResponse>) response;
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
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }

    // register user
    // url => registration
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
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
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
            public void onResponse(@NonNull Call<GenericResponse> call, @NonNull Response<GenericResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<GenericResponse> call, @NonNull Throwable t) {
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
            public void onResponse(@NonNull Call<GenericResponse> call, @NonNull Response<GenericResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<GenericResponse> call, @NonNull Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }


    // get all trips created by user
    // url => requests/drivers
    // parameter => date , page getTripsByDate ( 10 , 20 , .. etc )
    public static void getTripsByDate(TripsByDate tripsByDate, final BaseResponseInterface<List<TripDetails>> anInterface) {
        Call<TripDetailsListResponse> response = ApiConfig.httpApiInterface.getTripsByDate("requests/drivers", MyApplication.getUserToken(), tripsByDate);
        response.enqueue(new Callback<TripDetailsListResponse>() {
            @Override
            public void onResponse(@NonNull Call<TripDetailsListResponse> call, @NonNull Response<TripDetailsListResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<TripDetailsListResponse> call, @NonNull Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }

    // get all past trips created by user , order
    // url => api/requests/past ,
    // parameter => user id , page getTripsByDate ( 10 , 20 , .. etc )
    public static void getPastTrips(String url, TripsByDate tripsByDate, final BaseResponseInterface<List<TripDetails>> anInterface) {
        Call<TripDetailsListResponse> response = ApiConfig.httpApiInterface.getTripsByDate(url,MyApplication.getUserToken(), tripsByDate);
        response.enqueue(new Callback<TripDetailsListResponse>() {
            @Override
            public void onResponse(@NonNull Call<TripDetailsListResponse> call, @NonNull Response<TripDetailsListResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<TripDetailsListResponse> call, @NonNull Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }

    // get trip created by user
    // url => requests/one/request
    // parameter => trip id
    public static void getTripRequestById(int tripId, final BaseResponseInterface<TripDetails> anInterface) {
        Call<TripDetailsResponse> response = ApiConfig.httpApiInterface.getTripRequestById(MyApplication.getUserToken(), tripId,SharedPrefManager.getObject(SharedPrefKey.USER, User.class).getId());
        response.enqueue(new Callback<TripDetailsResponse>() {
            @Override
            public void onResponse(@NonNull Call<TripDetailsResponse> call, @NonNull Response<TripDetailsResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<TripDetailsResponse> call, @NonNull Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }

    // contact us
    // url => contact
    // parameter => user id , name, phone,subject , message
    public static void contactUs(ContactUsRequest contactUsRequest, final BaseResponseInterface anInterface) {
        Call<GenericResponse> response = ApiConfig.httpApiInterface.contactUs(MyApplication.getUserToken(), contactUsRequest);
        response.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenericResponse> call, @NonNull Response<GenericResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<GenericResponse> call, @NonNull Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }

    // user information
    // url => home
    // parameter => token , user id
    public static void getUserInfo(final BaseResponseInterface<UserInfo> anInterface) {
        Call<UserInfoResponse> response = ApiConfig.httpApiInterface.getUserInfo("home", MyApplication.getUserToken(), SharedPrefManager.getObject(SharedPrefKey.USER, User.class).getId(),SharedPrefManager.getString(SharedPrefKey.TOKEN));
        response.enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserInfoResponse> call, @NonNull Response<UserInfoResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<UserInfoResponse> call, @NonNull Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }

    // user information with feedback
    // url => /auth/me
    // parameter => token ,user id
    public static void getUserInfoWithFeedback(final BaseResponseInterface<UserInfo> anInterface) {
        Call<UserInfoResponse> response = ApiConfig.httpApiInterface.getUserInfo("auth/me", MyApplication.getUserToken(), SharedPrefManager.getObject(SharedPrefKey.USER, User.class).getId(),"");
        response.enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserInfoResponse> call, @NonNull Response<UserInfoResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<UserInfoResponse> call, @NonNull Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }

    // edit user profile
    // url => auth/edit_profile
    // parameter => user information
    public static void editProfile(UserSignUpRequest userSignUpRequest, String userId, MultipartBody.Part part, final BaseResponseInterface<User> anInterface) {
        Call<UserResponse> response = ApiConfig.httpApiInterface.editProfile(MyApplication.getUserToken(),
                part, MyUtils.createPartFromString(userId)
                , MyUtils.createPartFromString(userSignUpRequest.getName())
                , MyUtils.createPartFromString(userSignUpRequest.getInteresting()));
        response.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }

    // create trip
    // url => requests/create
    // parameter => location start, location end, time, driver id
    public static void createTrip(CreateTripRequest createTripRequest, final BaseResponseInterface anInterface) {
        Call<GenericResponse> response = ApiConfig.httpApiInterface.createTrip(MyApplication.getUserToken(), createTripRequest);
        response.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenericResponse> call, @NonNull Response<GenericResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<GenericResponse> call, @NonNull Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }

    // cancel - finish trip
    // url => requests/cancel
    // parameter => request id, is cancel, is finish
    public static void finishTrip(TripStatusRequest tripStatusRequest, final BaseResponseInterface anInterface) {
        Call<GenericResponse> response = ApiConfig.httpApiInterface.finishTrip(MyApplication.getUserToken(), tripStatusRequest);
        response.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenericResponse> call, @NonNull Response<GenericResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<GenericResponse> call, @NonNull Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }

    // start trip
    // url => requests/start/trip
    // parameter => request id, user id, is running
    public static void startTrip(StartTripRequest startTripRequest, final BaseResponseInterface anInterface) {
        Call<GenericResponse> response = ApiConfig.httpApiInterface.startTrip(MyApplication.getUserToken(), startTripRequest);
        response.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenericResponse> call, @NonNull Response<GenericResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<GenericResponse> call, @NonNull Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }

    // finish trip
    // url =>
    public static void driverFinishTrip(FinishTripRequest finishTripRequest, final BaseResponseInterface anInterface) {
        Call<GenericResponse> response = ApiConfig.httpApiInterface.driverFinishTrip(MyApplication.getUserToken(), finishTripRequest);
        response.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenericResponse> call, @NonNull Response<GenericResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<GenericResponse> call, @NonNull Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }

    // join trip
    // url => requests/user/join/trip
    // parameter => request id, user id, is joined
    public static void joinTrip(StartTripRequest startTripRequest, final BaseResponseInterface anInterface) {
        Call<GenericResponse> response = ApiConfig.httpApiInterface.joinTrip(MyApplication.getUserToken(), startTripRequest);
        response.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenericResponse> call, @NonNull Response<GenericResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<GenericResponse> call, @NonNull Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }

    // driver make user leave trip
    // url => requests/admin/end/user/trip
    public static void setTripDetails(TripDetailsRequest tripDetailsRequest, final BaseResponseInterface anInterface) {
        Call<GenericResponse> response = ApiConfig.httpApiInterface.setTripDetails(MyApplication.getUserToken(), tripDetailsRequest);
        response.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenericResponse> call, @NonNull Response<GenericResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<GenericResponse> call, @NonNull Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }

    // get about
    // url => about
    // parameter => page name
    public static void getAbout(final BaseResponseInterface<About> anInterface) {
        Call<AboutResponse> response = ApiConfig.httpApiInterface.getAbout(MyApplication.getUserToken(), "about");
        response.enqueue(new Callback<AboutResponse>() {
            @Override
            public void onResponse(@NonNull Call<AboutResponse> call, @NonNull Response<AboutResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<AboutResponse> call, @NonNull Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }

    // get notification
    // url => notifications
    // parameter => user id
    public static void getNotification(final BaseResponseInterface<List<Notification>> anInterface) {
        Call<NotificationResponse> response = ApiConfig.httpApiInterface.getNotification(MyApplication.getUserToken(), SharedPrefManager.getObject(SharedPrefKey.USER, User.class).getId());
        response.enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(@NonNull Call<NotificationResponse> call, @NonNull Response<NotificationResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<NotificationResponse> call, @NonNull Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }

    // change password
    // url => requests/admin/end/user/trip
    public static void changePassword(ChangePasswordRequest request, final BaseResponseInterface anInterface) {
        Call<GenericResponse> response = ApiConfig.httpApiInterface.changePassword(MyApplication.getUserToken(), request);
        response.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenericResponse> call, @NonNull Response<GenericResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<GenericResponse> call, @NonNull Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }

    // make comment and rate
    // url => requests/rating
    public static void sendFeedback(FeedbackRequest feedbackRequest, final BaseResponseInterface anInterface) {
        Call<GenericResponse> response = ApiConfig.httpApiInterface.sendFeedback(MyApplication.getUserToken(), feedbackRequest);
        response.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenericResponse> call, @NonNull Response<GenericResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<GenericResponse> call, @NonNull Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }

    // get user profile
    public static void getUserProfile(int userId, final BaseResponseInterface<User> anInterface) {
        Call<UserResponse> response = ApiConfig.httpApiInterface.getUserProfile(MyApplication.getUserToken(),userId);
        response.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }

    // make driver accept user in trip or reject it
    public static void acceptRejectUser(AcceptRejectUser acceptRejectUser, final BaseResponseInterface anInterface) {
        Call<GenericResponse> response = ApiConfig.httpApiInterface.acceptRejectUser(MyApplication.getUserToken(), acceptRejectUser);
        response.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenericResponse> call, @NonNull Response<GenericResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<GenericResponse> call, @NonNull Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }
    private static void uploadLicense(String type, MultipartBody.Part part, final BaseResponseInterface<User> anInterface) {
        //SharedPrefManager.getObject(SharedPrefKey.USER, User.class).getId()
        Call<GenericResponse> response = ApiConfig.httpApiInterface.uploadLicense(part
                , MyUtils.createPartFromString(String.valueOf(2))
                ,MyUtils.createPartFromString(type)
        );
        response.enqueue(new Callback<GenericResponse>() {
            @Override
            public void onResponse(@NonNull Call<GenericResponse> call, @NonNull Response<GenericResponse> response) {
                // check on response and get data
                checkValidResult(response, anInterface);
            }

            @Override
            public void onFailure(@NonNull Call<GenericResponse> call, @NonNull Throwable t) {
                // get error message
                anInterface.onFailed(t.getLocalizedMessage());
            }
        });
    }
    public static void uploadCarLicense(MultipartBody.Part part, final BaseResponseInterface anInterface) {
        uploadLicense("car_license_image",part,anInterface);
    }
    public static void uploadDrivingLicense(MultipartBody.Part part, final BaseResponseInterface anInterface) {
        uploadLicense("driving_license_image",part,anInterface);
    }
    public static void uploadNationalId(MultipartBody.Part part, final BaseResponseInterface anInterface) {
        uploadLicense("national_id_image",part,anInterface);
    }
}
