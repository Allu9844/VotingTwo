package com.guruprasadhiremathgmail.bmsit.activity.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.google.firebase.iid.FirebaseInstanceId;
import com.guruprasadhiremathgmail.bmsit.R;
import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.DistictPojo;
import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.StatePojo;
import com.guruprasadhiremathgmail.bmsit.activity.activity.RetroInterfaces.RegisterFcm;
import com.guruprasadhiremathgmail.bmsit.activity.activity.manager.LoginManager;

import java.util.ArrayList;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by govindarao on 11/24/16.
 */
public class SplashActivity extends AppCompatActivity {

    public static String url = "http://172.104.50.103/";




    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    /**
     * Called when the activity is first created.
     */
    Thread splashTread;
    public ImageView logo;
    public LoginManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activity);

        lm=new LoginManager(this);

        getStates();

        getDisticts();


//        logo.animate().rotation(360);

//        startActivity(new Intent(this,DashboardActivity.class));
//        finish();

        StartAnimations();




    }

    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l = (LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splash);
        iv.clearAnimation();
        iv.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }

                    if(lm.getFistTime()){
                        Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        SplashActivity.this.finish();
                        finish();
                    }else {
                        Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        SplashActivity.this.finish();
                        finish();
                    }
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    SplashActivity.this.finish();
                }

            }
        };
        splashTread.start();


    }

    public void getStates(){

        Log.i("aaaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaa");
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(SplashActivity.url);
        String credentials = String.format("%s:%s","allam","allam123");
        final String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        builder.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Authorization", basic);
                request.addHeader("Accept", "application/json");
            }
        });
        RestAdapter restAdapter = builder.build();
        RegisterFcm api = restAdapter.create(RegisterFcm.class);

        api.register(new retrofit.Callback<ArrayList<StatePojo>>() {
            @Override
            public void success(ArrayList<StatePojo> statePojos, retrofit.client.Response response) {

                LoginManager.states = statePojos;
            }

            @Override
            public void failure(RetrofitError error) {

                Log.i("error","rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr"+error.toString());
            }
        });
    }

    public void getDisticts(){
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(SplashActivity.url);
        String credentials = String.format("%s:%s","allam","allam123");
        final String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        builder.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Authorization", basic);
                request.addHeader("Accept", "application/json");
            }
        });
        RestAdapter restAdapter = builder.build();
        RegisterFcm api = restAdapter.create(RegisterFcm.class);

        api.getDisticts(new Callback<ArrayList<DistictPojo>>() {
            @Override
            public void success(ArrayList<DistictPojo> distictPojos, Response response) {

                LoginManager.disticts=distictPojos;
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }


}