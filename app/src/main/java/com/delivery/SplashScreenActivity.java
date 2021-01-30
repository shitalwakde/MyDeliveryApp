package com.delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.delivery.controller.AppConstant;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    //todo change this tym
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if(AppConstant.isLogin(SplashScreenActivity.this)){
                        Intent intent=new Intent(SplashScreenActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Intent intent=new Intent(SplashScreenActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        };
        timerThread.start();
    }
}
