package com.guruprasadhiremathgmail.bmsit.activity.activity.Firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.guruprasadhiremathgmail.bmsit.R;


/**
 * Created by allam on 31/1/17.
 */

public class MyFCMService extends FirebaseMessagingService {

    Notifications mNotification;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        mNotification=new Notifications(this);
//        Log.i("Notification type-",remoteMessage.getData().get("notification_type"));

            Notifications.NotificationConstants.mNotificationCount++;
            Log.i("On Message Recieved=", "-------------------------------------------------------"+remoteMessage.getData().toString());
           // mNotification.showNewSheduleNotification(remoteMessage);


        Log.i("Notification type-----",remoteMessage.getData().get("message"));




        if(remoteMessage.getData().get("message").equals("status"))
        {

        }












    }
    private int getNotificationIcon() {
        boolean useWhiteIcon = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.mipmap.ic_launcher: R.mipmap.ic_launcher;
    }
}
