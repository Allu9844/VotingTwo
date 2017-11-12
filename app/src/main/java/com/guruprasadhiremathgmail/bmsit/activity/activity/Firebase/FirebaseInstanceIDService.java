package com.guruprasadhiremathgmail.bmsit.activity.activity.Firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.guruprasadhiremathgmail.bmsit.activity.activity.RetroInterfaces.RegisterFcm;
import com.guruprasadhiremathgmail.bmsit.activity.activity.manager.LoginManager;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by allam on 31/1/17.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {


    public static LoginManager lm;
    @Override
    public void onTokenRefresh() {
        Log.i("On Token Refresh=", "----------------------------------------------------------------------"+FirebaseInstanceId.getInstance().getToken());

        lm=new LoginManager(this);

        Log.i("IsTokenGen", String.valueOf(lm.getIstokenGen()));
        lm.setIsTokenGen(true);
        Log.i("IsTokenGen", String.valueOf(lm.getIstokenGen()));
//            addTokenToDB();

    }




}
