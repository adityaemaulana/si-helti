package com.example.sihelti.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {
    Intent intent;
    private Boolean firstTime = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash_screen);

        if (isFirstTime()) {
            intent = new Intent(SplashScreenActivity.this, OnBoardActivity.class);
            startActivity(intent);
            finish();
        } else {
            intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private boolean isFirstTime() {
        if (firstTime == null) {
            SharedPreferences mPreference = getSharedPreferences("first_time", Context.MODE_PRIVATE);
            firstTime = mPreference.getBoolean("firstTime", true);
            if (firstTime) {
                SharedPreferences.Editor editor = mPreference.edit();
                editor.putBoolean("firstTime", false);
                editor.commit();
            }
        }
        return firstTime;
    }
}
