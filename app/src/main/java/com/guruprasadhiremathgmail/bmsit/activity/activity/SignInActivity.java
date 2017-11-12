package com.guruprasadhiremathgmail.bmsit.activity.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.guruprasadhiremathgmail.bmsit.R;
import com.guruprasadhiremathgmail.bmsit.activity.activity.Models.MemberPojo;
import com.guruprasadhiremathgmail.bmsit.activity.activity.RetroInterfaces.RegisterFcm;
import com.guruprasadhiremathgmail.bmsit.activity.activity.manager.LoginManager;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.guruprasadhiremathgmail.bmsit.activity.activity.DashboardActivity.isNetworkAvailable;

public class SignInActivity extends AppCompatActivity {

    private EditText mUsername,mPassword;
    private ProgressDialog mdialog;
    private LoginManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mUsername = (EditText)findViewById(R.id.number);
        mPassword = (EditText)findViewById(R.id.password);

        lm = new LoginManager(this);



    }

    public void signin(View view){

        if(validate(mUsername) && validate(mPassword)){

            checkuser();
        }


    }

    public void checkuser(){

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

        api.signin(mUsername.getText().toString(), mPassword.getText().toString(), new Callback<MemberPojo>() {
            @Override
            public void success(MemberPojo memberPojo, Response response) {

                lm.setFirstTimeLaunch(false);
                lm.setRole(Integer.parseInt(memberPojo.getId()));
                startActivity(new Intent(SignInActivity.this,DashboardActivity.class));
                finish();
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

    private boolean validate(EditText editText) {
        if (editText.getText().toString().trim().length() < 1) {
            editText.setError(getString(R.string.fill));
            editText.requestFocus();
            return false;
        }
        return true;
    }
}
