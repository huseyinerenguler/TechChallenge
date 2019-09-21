package com.huseyinerenguler.techchallenge.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.huseyinerenguler.techchallenge.R;
import com.huseyinerenguler.techchallenge.utils.StaticParameters;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        StaticParameters.init(getWindowManager());

        if (StaticParameters.screenWidth > 0)
            startLoginActivity();
    }

    private void startLoginActivity() {

        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }

}