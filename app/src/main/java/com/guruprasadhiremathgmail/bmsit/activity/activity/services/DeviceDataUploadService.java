package com.guruprasadhiremathgmail.bmsit.activity.activity.services;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.DateUtils;

import java.util.UUID;


public class DeviceDataUploadService extends IntentService {

    private static final String EXTRA_RETRIES = "extra_retries";

    public DeviceDataUploadService() {
        super("DeviceDataUploadService");
    }

    private static final String INSTANCE_PREFS = "instance_prefs";
    private static final String INSTANCE_ID_KEY = "instance_id";
    private static final String APP_REMOTE_ID_KEY = "app_remote_id";
    private static final String DEVICE_REMOTE_ID_KEY = "device_remote_id";

    public static void start(Context context) {
        start(context, null);
    }

    public static void start(Context context, Bundle extras) {
        Intent intent = new Intent(context, DeviceDataUploadService.class);
        if(extras != null) {
            intent.putExtras(extras);
        }
        context.startService(intent);
    }

    @SuppressLint("HardwareIds")
    @Override
    protected void onHandleIntent(Intent intent) {

        SharedPreferences prefs = getSharedPreferences(INSTANCE_PREFS, MODE_PRIVATE);
        String instanceId = prefs.getString(INSTANCE_ID_KEY, null);
        if(instanceId == null) {
            instanceId = UUID.randomUUID().toString();
            prefs.edit().putString(INSTANCE_ID_KEY, instanceId).apply();
        }
        String appRemoteIdentifier = prefs.getString(APP_REMOTE_ID_KEY, null);
        String deviceRemoteIdentifier = prefs.getString(DEVICE_REMOTE_ID_KEY, null);

//        DeviceInfo deviceInfo = new DeviceInfo();
//        deviceInfo.setDeviceId(Settings.Secure.getString(getContentResolver(),
//                Settings.Secure.ANDROID_ID));
//        deviceInfo.setDeviceIdentifier(deviceRemoteIdentifier);
//
//        ApplicationInfo applicationInfo = new ApplicationInfo();
//        FirebaseInstanceId firebaseInstanceId = FirebaseInstanceId.getInstance();
//        applicationInfo.setInstallId(instanceId);
//        applicationInfo.setFcmToken(firebaseInstanceId.getToken());
//        applicationInfo.setApplicationKey(getPackageName());
//        applicationInfo.setApplicationName(getString(R.string.app_name));
//        applicationInfo.setApplicationVersionName(BuildConfig.VERSION_NAME);
//        applicationInfo.setApplicationVersionNumber(BuildConfig.VERSION_CODE);
//        applicationInfo.setApplicationIdentifier(appRemoteIdentifier);
//
//        DeviceAndAppInfo deviceAndAppInfo = new DeviceAndAppInfo();
//        deviceAndAppInfo.setApplicationInfo(applicationInfo);
//        deviceAndAppInfo.setDeviceInfo(deviceInfo);
//
//
//        DeviceService.DeviceApi deviceServiceApi = DeviceService.getInstance().newTransaction();
//        Call<DeviceAndAppInfo> updateDeviceInfoCall = deviceServiceApi.createDeviceAndAppInfo(deviceAndAppInfo);
//        Intent retryIntentReceiver = new Intent(this, DeviceDataUploadRetryReceiver.class);
//        int retries = retryValue(intent);
//        retryIntentReceiver.putExtra(EXTRA_RETRIES, ++retries);
//        Log.d("DeviceDataUploadService", "Retries: " + retries);
//        retryIntentReceiver.setAction(DeviceDataUploadRetryReceiver.RETRY_DEVICE_INFO_UPLOAD);
//        PendingIntent retryPendingIntent = PendingIntent.getBroadcast(this, 0, retryIntentReceiver,
//                PendingIntent.FLAG_CANCEL_CURRENT);
//        if(!NetworkUtils.hasDataConnection(this)){
//            scheduleNextRetry(intent, retryPendingIntent);
//            return;
//        }
//        try {
//            retrofit2.Response<DeviceAndAppInfo> resp = updateDeviceInfoCall.execute();
//            if (resp.isSuccessful()) {
//                deviceAndAppInfo = resp.body();
//                if (deviceAndAppInfo != null && deviceAndAppInfo.getApplicationInfo() != null
//                        && deviceAndAppInfo.getDeviceInfo() != null) {
//                    appRemoteIdentifier = deviceAndAppInfo
//                            .getApplicationInfo().getApplicationIdentifier();
//                    deviceRemoteIdentifier = deviceAndAppInfo
//                            .getDeviceInfo().getDeviceIdentifier();
//                    prefs.edit().putString(APP_REMOTE_ID_KEY, appRemoteIdentifier)
//                            .putString(DEVICE_REMOTE_ID_KEY, deviceRemoteIdentifier).apply();
//                    AA3Config.getInstance().setApplicationIdentifier(appRemoteIdentifier);
//                    AA3Config.getInstance().setDeviceIdentifier(deviceRemoteIdentifier);
//                    TeleconsultationConfig.getInstance().setApplicationIdentifier(appRemoteIdentifier);
//                    TeleconsultationConfig.getInstance().setDeviceIdentifier(deviceRemoteIdentifier);
//                }
//                AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//                alarmMgr.cancel(retryPendingIntent);
//                return;
//            }
//        } catch (IOException e) {
//            //Ignore
//        }

       // scheduleNextRetry(intent, retryPendingIntent);
    }


    private void scheduleNextRetry(Intent startIntent, PendingIntent retryPendingIntent) {
        int retries = retryValue(startIntent);
        long nextRetryPeriod = (long) Math.pow(2, retries) * 5 * DateUtils.SECOND_IN_MILLIS;
        AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmMgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + nextRetryPeriod, retryPendingIntent);
    }

    private int retryValue(Intent startIntent) {
        int retries = 0;
        if(startIntent != null) {
            retries = startIntent.getIntExtra(EXTRA_RETRIES, 0);
        }
        return retries;
    }
}
