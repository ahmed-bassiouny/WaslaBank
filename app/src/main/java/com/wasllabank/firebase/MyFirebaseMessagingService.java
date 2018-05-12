package com.wasllabank.firebase;

import android.app.PendingIntent;
import android.content.Intent;

import com.wasllabank.activities.view.SplashActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import bassiouny.ahmed.genericmanager.CustomNotificationManager;

/**
 * Created by bassiouny on 25/04/18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        CustomNotificationManager customNotificationManager = CustomNotificationManager.getInstance(this);
        if (remoteMessage.getNotification() != null) {
            customNotificationManager.setTitle(remoteMessage.getNotification().getTitle());
            customNotificationManager.setBody(remoteMessage.getNotification().getBody());
            customNotificationManager.setIcon(com.wasllabank.R.mipmap.ic_launcher);
            //customNotificationManager.setContentIntent(openHomeScreen());
            customNotificationManager.show(1);
        }
    }

    private PendingIntent openHomeScreen() {
        Intent myIntent = new Intent(this, SplashActivity.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        return PendingIntent.getActivity(this, 0, myIntent, 0);
    }
}
