package com.socialnetwork.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.socialnetwork.R;
import com.socialnetwork.Utils.PreferencesManager;

public class SplashActivity extends AppCompatActivity {

    private PreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        preferencesManager = new PreferencesManager(getApplicationContext());
        startApplication();
    }

    private void startApplication() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(preferencesManager.getUserId().isEmpty()){
                    loginActiviy();
                }else {
                    mainActiviy();
                }
            }
        }, 3000);
    }

    private void loginActiviy(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    private void mainActiviy(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}