package com.example.agencyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;

public class Authentication extends AppCompatActivity {
    LottieAnimationView LAV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
    LAV = findViewById(R.id.google_lottie);
    LAV.setAnimation(R.raw.google_logo);
    LAV.playAnimation();
    LAV.loop(true);

    }
}