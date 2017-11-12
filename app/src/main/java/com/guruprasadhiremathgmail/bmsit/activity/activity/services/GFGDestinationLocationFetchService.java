package com.guruprasadhiremathgmail.bmsit.activity.activity.services;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.util.SparseArray;

import com.google.firebase.iid.FirebaseInstanceId;
import com.guruprasadhiremathgmail.bmsit.R;
import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.StatePojo;
import com.guruprasadhiremathgmail.bmsit.activity.activity.RetroInterfaces.RegisterFcm;
import com.guruprasadhiremathgmail.bmsit.activity.activity.SplashActivity;

import java.util.ArrayList;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GFGDestinationLocationFetchService extends IntentService {


    public GFGDestinationLocationFetchService() {
        super("GFGDestinationLocationFetchService");
    }

    public static void start(Context context) {
        start(context, null);
    }

    public static void start(Context context, Bundle extras) {
        Intent intent = new Intent(context, GFGDestinationLocationFetchService.class);
        if(extras != null) {
            intent.putExtras(extras);
        }
        context.startService(intent);
    }

    @SuppressLint("HardwareIds")
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("alllam","allam");
        getAllPlants();

    }

    private void getAllPlants() {

        Log.i("getAllPlants","kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
    }

}
