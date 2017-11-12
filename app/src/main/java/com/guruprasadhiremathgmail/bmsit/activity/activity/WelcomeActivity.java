package com.guruprasadhiremathgmail.bmsit.activity.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.guruprasadhiremathgmail.bmsit.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void signin(View view){

        startActivity(new Intent(this,SignInActivity.class));
        finish();
    }
    public void register(View view){

        startActivity(new Intent(this,SignUpActivity.class));
    }
}
