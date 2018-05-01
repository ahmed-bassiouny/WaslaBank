package bassiouny.ahmed.waslabank.interfaces;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;
import bassiouny.ahmed.waslabank.api.apiModel.requests.AcceptRejectUser;
import bassiouny.ahmed.waslabank.api.apiModel.requests.ChangePasswordRequest;
import bassiouny.ahmed.waslabank.api.apiModel.requests.ContactUsRequest;
import bassiouny.ahmed.waslabank.api.apiModel.requests.CreateTripRequest;
import bassiouny.ahmed.waslabank.api.apiModel.requests.FeedbackRequest;
import bassiouny.ahmed.waslabank.api.apiModel.requests.FinishTripRequest;
import bassiouny.ahmed.waslabank.api.apiModel.requests.StartTripRequest;
import bassiouny.ahmed.waslabank.api.apiModel.requests.TripDetailsRequest;
import bassiouny.ahmed.waslabank.api.apiModel.requests.TripStatusRequest;
import bassiouny.ahmed.waslabank.api.apiModel.requests.TripsByDate;
import bassiouny.ahmed.waslabank.api.apiModel.response.AboutResponse;
import bassiouny.ahmed.waslabank.api.apiModel.response.GenericResponse;
import bassiouny.ahmed.waslabank.api.apiModel.requests.UserLoginRequest;
import bassiouny.ahmed.waslabank.api.apiModel.requests.UserSignUpRequest;
import bassiouny.ahmed.waslabank.api.apiModel.response.NotificationResponse;
import bassiouny.ahmed.waslabank.api.apiModel.response.TripDetailsListResponse;
import bassiouny.ahmed.waslabank.api.apiModel.response.TripDetailsResponse;
import bassiouny.ahmed.waslabank.api.apiModel.response.UserInfoResponse;
import bassiouny.ahmed.waslabank.api.apiModel.response.UserResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 * Created by bassiouny on 26/03/18.
 */

public interface HttpApiInterface {
    String HEADER_KEY = "Accept:application/json";
    String AUTHORIZATION = "Authorization";

    @POST("auth/login")
    Call<UserResponse> login(@Body UserLoginRequest user);

    @POST("registration")
    @Multipart
    Call<UserResponse> signUp(@Part MultipartBody.Part file, @Part(ApiKey.NAME) RequestBody name
            , @Part(ApiKey.PHONE) RequestBody phone, @Part(ApiKey.EMAIL) RequestBody email, @Part(ApiKey.PASSWORD) RequestBody password
            , @Part(ApiKey.CAR_NUMBER) RequestBody carNumber, @Part(ApiKey.LICENSE_NUMBER) RequestBody licenseNumber, @Part(ApiKey.CAR_SIZE) RequestBody carSize
            , @Part(ApiKey.IDENTIFY_NUMBER) RequestBody identifyNumber, @Part(ApiKey.GENDER) RequestBody gender, @Part(ApiKey.CITY) RequestBody city
            , @Part(ApiKey.NOTIFICATION_TOKEN) RequestBody notificationToken);

    @POST()
    Call<GenericResponse> checkUserData(@Url String url, @Body UserSignUpRequest user);

    @POST()
    @Headers(HEADER_KEY)
    Call<TripDetailsListResponse> getTripsByDate(@Url String url,@Header(AUTHORIZATION) String token, @Body TripsByDate tripsByDate);

    @FormUrlEncoded
    @POST("requests/one/request")
    @Headers(HEADER_KEY)
    Call<TripDetailsResponse> getTripRequestById(@Header(AUTHORIZATION) String token, @Field(ApiKey.REQUEST_ID) int requestId,@Field(ApiKey.USER_ID) int userId);


    @POST("contact")
    @Headers(HEADER_KEY)
    Call<GenericResponse> contactUs(@Header(AUTHORIZATION) String token, @Body ContactUsRequest contactUsRequest);


    @FormUrlEncoded
    @POST()
    @Headers(HEADER_KEY)
    Call<UserInfoResponse> getUserInfo(@Url String url, @Header(AUTHORIZATION) String token, @Field(ApiKey.ID) int userId,@Field(ApiKey.NOTIFICATION_TOKEN) String notificationToken);


    @POST("auth/edit_profile")
    @Multipart
    @Headers(HEADER_KEY)
    Call<UserResponse> editProfile(@Header(AUTHORIZATION) String token, @Part MultipartBody.Part file, @Part(ApiKey.ID) RequestBody id
            , @Part(ApiKey.NAME) RequestBody name
            , @Part(ApiKey.INTERESTING) RequestBody interesting);


    @POST("requests/create")
    @Headers(HEADER_KEY)
    Call<GenericResponse> createTrip(@Header(AUTHORIZATION) String token, @Body CreateTripRequest createTripRequest);

    @POST("requests/cancel")
    @Headers(HEADER_KEY)
    Call<GenericResponse> finishTrip(@Header(AUTHORIZATION) String token, @Body TripStatusRequest tripStatusRequest);


    @POST("requests/start/trip")
    @Headers(HEADER_KEY)
    Call<GenericResponse> startTrip(@Header(AUTHORIZATION) String token, @Body StartTripRequest startTripRequest);


    @POST("requests/driver/end/trip")
    @Headers(HEADER_KEY)
    Call<GenericResponse> driverFinishTrip(@Header(AUTHORIZATION) String token, @Body FinishTripRequest finishTripRequest);


    @POST("requests/user/join/trip")
    @Headers(HEADER_KEY)
    Call<GenericResponse> joinTrip(@Header(AUTHORIZATION) String token, @Body StartTripRequest startTripRequest);


    @POST("requests/driver/end/user/trip")
    @Headers(HEADER_KEY)
    Call<GenericResponse> setTripDetails(@Header(AUTHORIZATION) String token, @Body TripDetailsRequest tripDetailsRequest);


    @FormUrlEncoded
    @POST("about")
    @Headers(HEADER_KEY)
    Call<AboutResponse> getAbout(@Header(AUTHORIZATION) String token, @Field(ApiKey.PAGE_NAME) String pageName);


    @FormUrlEncoded
    @POST("notifications")
    @Headers(HEADER_KEY)
    Call<NotificationResponse> getNotification(@Header(AUTHORIZATION) String token, @Field(ApiKey.USER_ID) int userId);



    @POST("auth/change_password")
    @Headers(HEADER_KEY)
    Call<GenericResponse> changePassword(@Header(AUTHORIZATION) String token, @Body ChangePasswordRequest request);


    @POST("requests/rating")
    @Headers(HEADER_KEY)
    Call<GenericResponse> sendFeedback(@Header(AUTHORIZATION) String token, @Body FeedbackRequest feedbackRequest);


    @FormUrlEncoded()
    @POST("auth/me")
    @Headers(HEADER_KEY)
    Call<UserResponse> getUserProfile(@Header(AUTHORIZATION) String token, @Field(ApiKey.ID) int userId);


    @POST("requests/one/request/accept/user")
    @Headers(HEADER_KEY)
    Call<GenericResponse> acceptRejectUser(@Header(AUTHORIZATION) String token, @Body AcceptRejectUser acceptRejectUser);

}
