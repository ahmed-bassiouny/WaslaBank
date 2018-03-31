package bassiouny.ahmed.waslabank.interfaces;

import bassiouny.ahmed.waslabank.api.apiModel.ParentResponse;
import bassiouny.ahmed.waslabank.api.apiModel.requests.UserLoginRequest;
import bassiouny.ahmed.waslabank.api.apiModel.requests.UserSignUpRequest;
import okhttp3.MultipartBody;
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
    Call<ParentResponse> login(@Body UserLoginRequest user);

    @Multipart
    @POST("signup")
    Call<ParentResponse> signUp(@Part UserSignUpRequest user, @Part MultipartBody.Part file);

    @POST()
    Call<ParentResponse> checkUserData(@Url String url,@Body UserSignUpRequest user);
}
