package com.example.agencyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class DashboardActivity extends AppCompatActivity {
LinearLayout btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btn = findViewById(R.id.artbtn);

        btn.setOnClickListener(v->{
            startActivity(new Intent(this,artactivity.class));
        });
    }
}