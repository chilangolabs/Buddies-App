package com.chilangolabs.buddis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_SCREEN_DELAY = 2000;
    SharedPreferences app_preference;
    SharedPreferences.Editor editor;
    boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        FacebookSdk.sdkInitialize(getApplicationContext());

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.chilangolabs.buddis",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }


        app_preference = getSharedPreferences(getString(R.string.preferences), Context.MODE_PRIVATE);
        status = app_preference.getBoolean(getString(R.string.status_enroll), true);
    }

    @Override
    protected void onResume() {
        super.onResume();

        AppEventsLogger.activateApp(this);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                init();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }

    private void init() {

        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

}
