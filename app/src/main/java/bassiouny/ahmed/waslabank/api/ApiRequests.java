package bassiouny.ahmed.waslabank.api;

import bassiouny.ahmed.waslabank.api.apiModel.ParentResponse;
import bassiouny.ahmed.waslabank.interfaces.BaseResponseInterface;
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
}
