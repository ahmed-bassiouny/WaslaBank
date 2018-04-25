package bassiouny.ahmed.waslabank.firebase;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import bassiouny.ahmed.genericmanager.CustomNotificationManager;
import bassiouny.ahmed.waslabank.R;

/**
 * Created by bassiouny on 25/04/18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        CustomNotificationManager customNotificationManager = CustomNotificationManager.getInstance(this);
        if (remoteMessage.getNotification() != null) {
            customNotificationManager.setTitle(getString(R.string.app_name));
            customNotificationManager.setBody(remoteMessage.getNotification().getBody());
            customNotificationManager.setIcon(R.mipmap.ic_launcher_round);
            customNotificationManager.show(1);
        }
    }
}
