package com.waslla_bank.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by bassiouny on 25/04/18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Intent action1Intent = new Intent().setAction("action_1");
        PendingIntent action1PendingIntent = PendingIntent.getService(this, 0,
                action1Intent, PendingIntent.FLAG_ONE_SHOT);
        if (remoteMessage.getNotification() != null) {
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this, "1")
                            .setSmallIcon(com.waslla_bank.R.mipmap.ic_launcher)
                            .setContentTitle(remoteMessage.getNotification().getTitle())
                            .setContentText(remoteMessage.getNotification().getBody())
                            .setContentIntent(action1PendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mBuilder.setAutoCancel(true);
            android.app.Notification b = mBuilder.build();
            b.flags |= android.app.Notification.FLAG_AUTO_CANCEL;
            if(notificationManager != null)
                notificationManager.notify(950, mBuilder.build());
        }

        //notificationManager.cancel(950);

      /*  MyNotificationManager customNotificationManager = MyNotificationManager.getInstance(this);
        if (remoteMessage.getNotification() != null) {
            customNotificationManager.setTitle(remoteMessage.getNotification().getTitle());
            customNotificationManager.setBody(remoteMessage.getNotification().getBody());
            customNotificationManager.setIcon(com.waslla_bank.R.mipmap.ic_launcher);
            //customNotificationManager.setContentIntent(openHomeScreen());;
            customNotificationManager.removeNotificationAfterClick();
            customNotificationManager.show(1);
        }*/
    }
}
