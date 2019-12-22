package com.example.onlinetraining;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    Handler handler;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textView=findViewById(R.id.text);
//        getSupportActionBar().hide();
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.mytransition);
        // To perform animation in android , we will  call a static function loadAnimation()
        // (this method is used to load animation) of the class AnimationUtils.
        // We are going to receive the result in an instance of Animation Object. Its syntax is as follows −

        textView.startAnimation(animation);
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Splash.this,MainActivity.class);
                startActivity(intent);
                finish();


            }
        },2000);
    }
}
