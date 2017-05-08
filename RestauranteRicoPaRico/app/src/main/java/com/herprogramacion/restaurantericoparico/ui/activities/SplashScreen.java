package com.herprogramacion.restaurantericoparico.ui.activities;

/**
 * Created by Sergio on 10/04/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.herprogramacion.restaurantericoparico.R;
//import android.util.Log;

public class SplashScreen extends AppCompatActivity {
    private ProgressBar mProgress;
    private TextView txtv;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splsah);
        mProgress = (android.widget.ProgressBar) findViewById(R.id.splash_screen_progress_bar);
        txtv=(TextView)findViewById(R.id.textView);
        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                Handler handler = new Handler();
                doWork();
                startApp();
                finish();
            }
        }).start();
    }
    private void doWork() {

        for (int progress=0; progress<100; progress+=1) {
            try {
                Thread.sleep(10);
                mProgress.setProgress(progress);
            } catch (Exception e) {

            }
        }
    }

    private void startApp() {

            Intent intent = new Intent(SplashScreen.this, ActividadPrincipal.class);
            startActivity(intent);

    }


}
