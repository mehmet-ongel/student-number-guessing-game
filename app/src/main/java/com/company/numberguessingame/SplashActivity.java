package com.company.numberguessingame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;

    Animation animationImage, animationtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textView = findViewById(R.id.textView3);
        imageView = findViewById(R.id.imageView3);

        animationImage = AnimationUtils.loadAnimation(this,R.anim.image_animation);
        animationtext = AnimationUtils.loadAnimation(this,R.anim.textanimation);
        imageView.setAnimation(animationImage);
        textView.setAnimation(animationtext);

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();

            }
        }.start();
    }
}