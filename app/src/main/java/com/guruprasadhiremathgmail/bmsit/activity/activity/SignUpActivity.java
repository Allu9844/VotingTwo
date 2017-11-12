package com.guruprasadhiremathgmail.bmsit.activity.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.guruprasadhiremathgmail.bmsit.R;
import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.MemberPojo;
import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.RegisterPojo;
import com.guruprasadhiremathgmail.bmsit.activity.activity.RetroInterfaces.RegisterFcm;
import com.guruprasadhiremathgmail.bmsit.activity.activity.RetroInterfaces.SignupInterface;
import com.guruprasadhiremathgmail.bmsit.activity.activity.RetroInterfaces.TodayScheduleInterface;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.guruprasadhiremathgmail.bmsit.activity.activity.DashboardActivity.isNetworkAvailable;
import static com.guruprasadhiremathgmail.bmsit.activity.activity.HideKeypad.hideSoftKeyboard;

public class SignUpActivity extends AppCompatActivity {


    public EditText mName,mAge,mCity,mState,mAdhar,mPhone,mPass;
    public ProgressDialog mdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        setupUI(findViewById(R.id.mainrel));

        mName = (EditText)findViewById(R.id.username);
        mAge = (EditText)findViewById(R.id.age);
        mCity = (EditText)findViewById(R.id.city);
        mState = (EditText)findViewById(R.id.state);
        mAdhar = (EditText)findViewById(R.id.adhar);
        mPhone = (EditText)findViewById(R.id.number);
        mPass = (EditText)findViewById(R.id.password);





//
    }


    public void registerButton(View view)
    {
//
        if(validate(mName) && validate(mAge) && validate(mCity)&& validate(mState)&& validate(mAdhar)&& validate(mPhone)&& validate(mPass)){

            postData();
//            startActivity(new Intent(this,DashboardActivity.class));
        }


    }

    public void postData(){

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

        api.signup(mName.getText().toString(), mAge.getText().toString(), mCity.getText().toString(), mState.getText().toString(), mAdhar.getText().toString(), mPhone.getText().toString(), mPass.getText().toString(), new Callback<MemberPojo>() {
            @Override
            public void success(MemberPojo memberPojo, Response response) {
                mdialog.dismiss();
                Toast.makeText(getApplicationContext(),"Registered succcessfully Sign in",Toast.LENGTH_LONG).show();
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
    private boolean validate(EditText editText) {
        if (editText.getText().toString().trim().length() < 1) {
            editText.setError(getString(R.string.fill));
            editText.requestFocus();
            return false;
        }
        return true;
    }


    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(SignUpActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }




}
