package bassiouny.ahmed.waslabank.interfaces;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;
import bassiouny.ahmed.waslabank.api.apiModel.requests.ContactUsRequest;
import bassiouny.ahmed.waslabank.api.apiModel.requests.CreateTripRequest;
import bassiouny.ahmed.waslabank.api.apiModel.requests.TripsByDate;
import bassiouny.ahmed.waslabank.api.apiModel.response.GenericResponse;
import bassiouny.ahmed.waslabank.api.apiModel.requests.UserLoginRequest;
import bassiouny.ahmed.waslabank.api.apiModel.requests.UserSignUpRequest;
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
    Call<UserResponse> signUp(@Part MultipartBody.Part file , @Part(ApiKey.NAME) RequestBody name
    , @Part(ApiKey.PHONE) RequestBody phone, @Part(ApiKey.EMAIL) RequestBody email, @Part(ApiKey.PASSWORD) RequestBody password
    , @Part(ApiKey.CAR_NUMBER) RequestBody carNumber, @Part(ApiKey.LICENSE_NUMBER) RequestBody licenseNumber, @Part(ApiKey.CAR_SIZE) RequestBody carSize
    , @Part(ApiKey.IDENTIFY_NUMBER) RequestBody identifyNumber, @Part(ApiKey.GENDER) RequestBody gender, @Part(ApiKey.CITY) RequestBody city
    , @Part(ApiKey.NOTIFICATION_TOKEN) RequestBody notificationToken);

    @POST()
    Call<GenericResponse> checkUserData(@Url String url, @Body UserSignUpRequest user);

    @POST("requests/drivers")
    @Headers(HEADER_KEY)
    Call<TripDetailsListResponse> getTripsByDate(@Header(AUTHORIZATION) String token, @Body TripsByDate tripsByDate);

    @FormUrlEncoded
    @POST("requests/one/request")
    @Headers(HEADER_KEY)
    Call<TripDetailsResponse> getTripRequestById(@Header(AUTHORIZATION) String token, @Field(ApiKey.REQUEST_ID) int requestId);


    @POST("contact")
    @Headers(HEADER_KEY)
    Call<GenericResponse> contactUs(@Header(AUTHORIZATION) String token, @Body ContactUsRequest contactUsRequest);


    @FormUrlEncoded
    @POST()
    @Headers(HEADER_KEY)
    Call<UserInfoResponse> getUserInfo(@Url String url,@Header(AUTHORIZATION) String token,@Field(ApiKey.ID) int userId);


    @POST("auth/edit_profile")
    @Multipart
    @Headers(HEADER_KEY)
    Call<UserResponse> editProfile(@Header(AUTHORIZATION) String token,@Part MultipartBody.Part file,@Part(ApiKey.ID) RequestBody id
            ,@Part(ApiKey.NAME) RequestBody name
            , @Part(ApiKey.INTERESTING) RequestBody interesting);


    @POST("requests/create")
    @Headers(HEADER_KEY)
    Call<GenericResponse> createTrip(@Header(AUTHORIZATION) String token, @Body CreateTripRequest createTripRequest);

}
