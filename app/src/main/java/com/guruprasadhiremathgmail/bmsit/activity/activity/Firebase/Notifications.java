package com.guruprasadhiremathgmail.bmsit.activity.activity.Firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.google.firebase.messaging.RemoteMessage;
import com.guruprasadhiremathgmail.bmsit.R;


/**
 * Created by allam on 10/2/17.
 */

public class Notifications extends ContextWrapper {


    public static Bitmap[] bitmaps;



    public Notifications(Context base) {
        super(base);
    }
    public static class NotificationConstants {
        public static int mNotificationCount;
    }
    public  void showNewSheduleNotification(RemoteMessage message) {

    }




    private int getNotificationIcon() {
        boolean useWhiteIcon = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.mipmap.ic_launcher: R.mipmap.ic_launcher;
    }
}
