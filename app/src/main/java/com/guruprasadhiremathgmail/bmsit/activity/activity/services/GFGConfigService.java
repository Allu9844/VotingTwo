package com.guruprasadhiremathgmail.bmsit.activity.activity.services;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.DateUtils;

public class GFGConfigService extends IntentService {

    private static final String EXTRA_RETRIES = "extra_retries";

    public GFGConfigService() {
        super("GFGConfigService");
    }

    public static void start(Context context) {
        start(context, null);
    }

    public static void start(Context context, Bundle extras) {
        Intent intent = new Intent(context, GFGConfigService.class);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startService(intent);
    }

    @SuppressLint("HardwareIds")
    @Override
    protected void onHandleIntent(Intent intent) {

//        HaloDocService haloDocService = new HaloDocService(this);
//
//        Call<ConfigResponse> call = haloDocService.getConfig();
//
//        Intent retryIntentReceiver = new Intent(this, GFGConfigRetryReceiver.class);
//        int retries = retryValue(intent);
//        retryIntentReceiver.putExtra(EXTRA_RETRIES, ++retries);
//        Log.d("GFGConfigService", "Retries: " + retries);
//        retryIntentReceiver.setAction(GFGConfigRetryReceiver.RETRY_GET_CONFIG);
//        PendingIntent retryPendingIntent = PendingIntent.getBroadcast(this, 0, retryIntentReceiver,
//                PendingIntent.FLAG_CANCEL_CURRENT);
//        if (!Utils.hasDataConnection(this)) {
//            scheduleNextRetry(intent, retryPendingIntent);
//            return;
//        }
//
//        try {
//            retrofit2.Response<ConfigResponse> response = call.execute();
//            if (response.isSuccessful() && response.body() != null) {
//                ConfigResponse config = response.body();
//                AA3Config.getInstance().setMaxOrderAmount(config.getAaConfiguration().getMaximumOrderAmount());
//                AA3Config.getInstance().setMaxPrescriptionFileSize(config.getAaConfiguration().getMaxPrescriptionFileSize());
//                AA3Config.getInstance().setMinOrderAmount(config.getAaConfiguration().getMinimumOrderAmount());
//                AA3Config.getInstance().setMinPrescriptionFileSize(config.getAaConfiguration().getMinPrescriptionFileSize());
//                AA3Config.getInstance().setOrderCompletedStatuses(config.getAaConfiguration().getOrderStatusMap().getCompleted().get(0));
//                AA3Config.getInstance().setOrderPickedUpStatuses(config.getAaConfiguration().getOrderStatusMap().getPicked().get(0));
//                AA3Config.getInstance().setOrderPlacedStatuses(config.getAaConfiguration().getOrderStatusMap().getPlaced().get(0));
//                AA3Config.getInstance().setMaxItemIncrement(config.getAaConfiguration().getMaxItemIncrement());
//                AA3Config.getInstance().setPromoEnabled(config.getAaConfiguration().isPromoEnabled());
//                AA3Config.getInstance().setPaymentOptionsEnabled(config.getAaConfiguration().isPaymentOptionsEnabled());
//                AA3Config.getInstance().setPrescriptionRequired(config.getAaConfiguration().isPrescriptionRequired());
//                TeleconsultationConfig.getInstance().setConsultationConfiguration(config.getConsultationConfiguration());
//            }
//
//            AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//            alarmMgr.cancel(retryPendingIntent);
//            return;
//
//        } catch (IOException e) {
//            if (BuildConfig.DEBUG) {
//                Crashlytics.logException(e);
//            }
//        }
//
//        scheduleNextRetry(intent, retryPendingIntent);
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
        if (startIntent != null) {
            retries = startIntent.getIntExtra(EXTRA_RETRIES, 0);
        }
        return retries;
    }
}
