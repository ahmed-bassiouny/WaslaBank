package bassiouny.ahmed.waslabank.api.apiModel.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import bassiouny.ahmed.waslabank.api.apiModel.ApiKey;
import bassiouny.ahmed.waslabank.model.About;
import bassiouny.ahmed.waslabank.model.Notification;

/**
 * Created by bassiouny on 11/04/18.
 */

public class NotificationResponse extends ParentResponse<List<Notification>> {

    @SerializedName(ApiKey.DATA)
    private List<Notification> notifications;

    @Override
    public List<Notification> getObject() {
        return notifications;
    }
}
