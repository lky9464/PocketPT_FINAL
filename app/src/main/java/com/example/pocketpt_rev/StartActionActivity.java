package com.example.pocketpt_rev;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class StartActionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_action);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartActionActivity.this, MainActivity.class);

                startActivity(intent);
                finish();
            }
        }, 2000);


    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}
