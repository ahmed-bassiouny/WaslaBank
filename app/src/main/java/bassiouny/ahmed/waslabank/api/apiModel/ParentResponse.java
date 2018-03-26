package bassiouny.ahmed.waslabank.api.apiModel;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import bassiouny.ahmed.waslabank.utils.MyUtils;

/**
 * Created by bassiouny on 26/03/18.
 */

public class ParentResponse {

    @SerializedName(ApiKey.STATUS)
    @SuppressWarnings({"UnusedDeclaration"})
    private boolean status;
    @SerializedName(ApiKey.MESSAGE)
    @SuppressWarnings({"UnusedDeclaration"})
    private String message;

    public boolean getStatus() {
        return status;
    }
    @NonNull
    public String getMessage() {
        return MyUtils.getString(message);
    }
}
