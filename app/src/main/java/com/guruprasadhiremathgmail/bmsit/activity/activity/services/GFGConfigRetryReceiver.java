package com.guruprasadhiremathgmail.bmsit.activity.activity.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class GFGConfigRetryReceiver extends BroadcastReceiver {
    public static final String RETRY_GET_CONFIG = "ACTION_RETRY_GET_CONFIG";

    @Override
    public void onReceive(Context context, Intent intent) {
        GFGConfigService.start(context, intent != null ? intent.getExtras() : null);
    }
}
