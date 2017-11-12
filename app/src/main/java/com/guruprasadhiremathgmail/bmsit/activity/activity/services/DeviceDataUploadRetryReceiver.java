package com.guruprasadhiremathgmail.bmsit.activity.activity.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DeviceDataUploadRetryReceiver extends BroadcastReceiver {
    public static final String RETRY_DEVICE_INFO_UPLOAD = "ACTION_RETRY_DEVICE_INFO_UPLOAD";

    @Override
    public void onReceive(Context context, Intent intent) {
        DeviceDataUploadService.start(context, intent != null ? intent.getExtras() : null);
    }
}
