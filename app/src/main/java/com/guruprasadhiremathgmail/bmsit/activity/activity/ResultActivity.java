package com.guruprasadhiremathgmail.bmsit.activity.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.guruprasadhiremathgmail.bmsit.R;
import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.DistictPojo;
import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.StatePojo;
import com.guruprasadhiremathgmail.bmsit.activity.activity.manager.LoginManager;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {


    public AutoCompleteTextView mstates,mDisticts;
    public ArrayList<StatePojo> statedata;
    public ArrayList<DistictPojo> distictdata;
    private ArrayAdapter<String> mAdapterVehicleNumber;

    public ArrayList<String> mdistics;
    public String mstatepk="",mDistictpk="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);



        mdistics = new ArrayList<String>();

        mstates = (AutoCompleteTextView) findViewById(R.id.state);
        statedata = LoginManager.states;
        distictdata = LoginManager.disticts;
        ArrayList<String> arlst=new ArrayList<>();
        int k = 0;

        String[] mOrdersNames = new String[statedata.size()];
        for(int m=0;m<statedata.size();m++){
            mOrdersNames[m]=statedata.get(m).getName();
        }
        mAdapterVehicleNumber = new ArrayAdapter<String>(ResultActivity.this,android.R.layout.simple_dropdown_item_1line, mOrdersNames);
        mstates.setThreshold(1);

        mstates.setAdapter(mAdapterVehicleNumber);
        mstates.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                mstates.showDropDown();
                return false;
            }
        });

        mstates.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                for(int j=0;j<statedata.size();j++){
                    if(mstates.getText().toString().equals(statedata.get(j).getName())){
                        mstatepk = statedata.get(j).getId();
                    }
                }

                for(int m=0;m<distictdata.size();m++){
                    if(mstatepk.equals(distictdata.get(m).getState().getId())){
                        mdistics.add(distictdata.get(m).getName());
                    }
                }
                String[] mOrdersNames = new String[mdistics.size()];
                for(int b=0;b<mdistics.size();b++){
                    mOrdersNames[b]=mdistics.get(b);
                }


                mDisticts = (AutoCompleteTextView) findViewById(R.id.distict);
                distictdata = LoginManager.disticts;
                int k = 0;


                mAdapterVehicleNumber = new ArrayAdapter<String>(ResultActivity.this,android.R.layout.simple_dropdown_item_1line, mOrdersNames);
                mDisticts.setThreshold(1);

                mDisticts.setAdapter(mAdapterVehicleNumber);
                mDisticts.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        mDisticts.showDropDown();
                        return false;
                    }
                });






                mDisticts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Log.i("selected","ssssssssssssssssssssssssssssssssss");
                        for(int j=0;j<distictdata.size();j++){
                            Log.i("selected","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+distictdata.get(j).getName());
                            if(mDisticts.getText().toString().equals(distictdata.get(j).getName())){
                                mDistictpk = distictdata.get(j).getId();
                            }
                        }

                    }
                });


            }
        });
    }

    public void submit(View view){

        Log.i("jjjjjjjj","jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj"+mstatepk+"             "+mDistictpk);

        Intent i = new Intent(this,Graph.class);
        i.putExtra("distict",mDistictpk);
        startActivity(i);

    }
}
