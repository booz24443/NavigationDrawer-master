package com.google.navigationdrawer.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.navigationdrawer.R;

public class SplashActivity extends AppCompatActivity {

    private TextView tv;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tv = findViewById(R.id.splash_tv);
        iv = findViewById(R.id.splash_iv);

        Animation myAnim = AnimationUtils.loadAnimation(this , R.anim.fade_in);
        tv.startAnimation(myAnim);
        iv.startAnimation(myAnim);

        final Intent intent = new Intent(this , MainActivity.class);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}
