package bassiouny.ahmed.waslabank.interfaces;

import bassiouny.ahmed.waslabank.api.apiModel.ParentResponse;
import bassiouny.ahmed.waslabank.api.apiModel.requests.UserLoginRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by bassiouny on 26/03/18.
 */

public interface HttpApiInterface {

    @POST()
    Call<ParentResponse> login(@Url String url, @Body UserLoginRequest user);
}
