package com.guruprasadhiremathgmail.bmsit.activity.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.guruprasadhiremathgmail.bmsit.R;
import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.CandidatesPojo;
import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.DistictPojo;
import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.VotePojo;
import com.guruprasadhiremathgmail.bmsit.activity.activity.RetroInterfaces.RegisterFcm;
import com.guruprasadhiremathgmail.bmsit.activity.activity.adapter.RecyclerAdapterBankAccounts;
import com.guruprasadhiremathgmail.bmsit.activity.activity.manager.LoginManager;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.guruprasadhiremathgmail.bmsit.activity.activity.DashboardActivity.isNetworkAvailable;

public class CandidatesVotingActivity extends AppCompatActivity {

    private String mDistictpk="";
    private ProgressDialog mdialog;
    private RecyclerView mScheduleRecyclerView;
    private RecyclerAdapterBankAccounts madapter;
    private LoginManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidates_voting);

        lm = new LoginManager(this);
        Intent i = getIntent();
        mDistictpk=i.getStringExtra("distict");
        getData();
    }

    public void getData(){

        mdialog = new ProgressDialog(this);
        mdialog.setMessage(getResources().getString(R.string.please_wait));
        mdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mdialog.setCancelable(true);
        mdialog.show();

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

        api.getCandidates(mDistictpk, new Callback<ArrayList<CandidatesPojo>>() {
            @Override
            public void success(ArrayList<CandidatesPojo> distictPojos, Response response) {

                mScheduleRecyclerView = (RecyclerView) findViewById(R.id.recycler);
                madapter = new RecyclerAdapterBankAccounts(getApplicationContext());
                madapter.setSchedule(distictPojos);
                mScheduleRecyclerView.setAdapter(madapter);
                mScheduleRecyclerView.setHasFixedSize(true);
                mScheduleRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                mScheduleRecyclerView.setNestedScrollingEnabled(false);

                mdialog.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("Error","------------------"+error.toString());
                isNetworkAvailable(getApplicationContext());
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                mdialog.dismiss();
            }
        });


    }

    public void submit(View view){


        mdialog = new ProgressDialog(this);
        mdialog.setMessage(getResources().getString(R.string.please_wait));
        mdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mdialog.setCancelable(true);
        mdialog.show();

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

        api.vote(String.valueOf(lm.getRole()),RecyclerAdapterBankAccounts.mBankId, new Callback<VotePojo>() {
            @Override
            public void success(VotePojo votePojo, Response response) {
                Toast.makeText(getApplicationContext(),"Votted Successfully", Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("Error","------------------"+error.toString());
                isNetworkAvailable(getApplicationContext());
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                mdialog.dismiss();
            }
        });

    }

}
