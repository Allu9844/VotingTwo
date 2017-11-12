package com.guruprasadhiremathgmail.bmsit.activity.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.guruprasadhiremathgmail.bmsit.R;
import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.ResultPojo;
import com.guruprasadhiremathgmail.bmsit.activity.activity.RetroInterfaces.RegisterFcm;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.guruprasadhiremathgmail.bmsit.activity.activity.DashboardActivity.isNetworkAvailable;

public class Graph extends AppCompatActivity {

    BarChart chart ;
    ArrayList<BarEntry> BARENTRY ;
    ArrayList<String> BarEntryLabels ;
    BarDataSet Bardataset ;
    BarData BARDATA ;
    public ProgressDialog mdialog;
    public String distickpk="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        chart = (BarChart) findViewById(R.id.chart1);


        Intent i =getIntent();
        distickpk = i.getStringExtra("distict");


        BARENTRY = new ArrayList<>();

        BarEntryLabels = new ArrayList<String>();

        getData();

//        AddValuesToBARENTRY();
//
//        AddValuesToBarEntryLabels();


    }

    public void AddValuesToBARENTRY(){

        BARENTRY.add(new BarEntry(2f, 0));
        BARENTRY.add(new BarEntry(4f, 1));
        BARENTRY.add(new BarEntry(6f, 2));
        BARENTRY.add(new BarEntry(8f, 3));
        BARENTRY.add(new BarEntry(4f, 4));

//        BARENTRY.add(new BarEntry(7f, 4));
//        BARENTRY.add(new BarEntry(3f, 5));

    }

    public void AddValuesToBarEntryLabels(){

        BarEntryLabels.add("M B APtil");
        BarEntryLabels.add("Reddy");
        BarEntryLabels.add("Krishna");
        BarEntryLabels.add("FFFFFF");
        BarEntryLabels.add("DDDDDDDDDDDDD");
//        BarEntryLabels.add("May");
//        BarEntryLabels.add("June");

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

        api.result(distickpk, new Callback<ArrayList<ResultPojo>>() {
            @Override
            public void success(ArrayList<ResultPojo> resultPojos, Response response) {

                mdialog.dismiss();

                for(int i=0;i<resultPojos.size();i++){
                    BARENTRY.add(new BarEntry(Float.parseFloat(resultPojos.get(i).getVotes()), i));

                }
                for(int i=0;i<resultPojos.size();i++){
                    BarEntryLabels.add(resultPojos.get(i).getCandidate());

                }

                Bardataset = new BarDataSet(BARENTRY, "Voting Candidates");

                BARDATA = new BarData(BarEntryLabels, Bardataset);

                Bardataset.setColors(ColorTemplate.COLORFUL_COLORS);

                chart.setData(BARDATA);

                chart.animateY(3000);
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
