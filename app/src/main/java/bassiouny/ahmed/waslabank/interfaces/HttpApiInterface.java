package bassiouny.ahmed.waslabank.interfaces;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;
import bassiouny.ahmed.waslabank.api.apiModel.response.ParentResponse;
import bassiouny.ahmed.waslabank.api.apiModel.requests.UserLoginRequest;
import bassiouny.ahmed.waslabank.api.apiModel.requests.UserSignUpRequest;
import bassiouny.ahmed.waslabank.api.apiModel.response.UserResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 * Created by bassiouny on 26/03/18.
 */

public interface HttpApiInterface {

    @POST("auth/login")
    Call<UserResponse> login(@Body UserLoginRequest user);

    @POST("registration")
    @Multipart
    Call<UserResponse> signUp(@Part MultipartBody.Part file , @Part(ApiKey.NAME) RequestBody name
    , @Part(ApiKey.PHONE) RequestBody phone, @Part(ApiKey.EMAIL) RequestBody email, @Part(ApiKey.PASSWORD) RequestBody password
    , @Part(ApiKey.CAR_NUMBER) RequestBody carNumber, @Part(ApiKey.LICENSE_NUMBER) RequestBody licenseNumber, @Part(ApiKey.CAR_SIZE) RequestBody carSize
    , @Part(ApiKey.IDENTIFY_NUMBER) RequestBody idetifyNumber, @Part(ApiKey.GENDER) RequestBody gender, @Part(ApiKey.CITY) RequestBody city
    , @Part(ApiKey.NOTIFICATION_TOKEN) RequestBody notificationToken);

    @POST()
    Call<ParentResponse> checkUserData(@Url String url,@Body UserSignUpRequest user);
}
