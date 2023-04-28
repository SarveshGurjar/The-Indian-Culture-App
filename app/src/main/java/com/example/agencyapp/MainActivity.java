package com.example.agencyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
LinearLayout btn;
Button auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.getstartedbtn);
        auth = findViewById(R.id.auth);

        btn.setOnClickListener(v->{
            startActivity(new Intent(this,DashboardActivity.class));
        });
        auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Authentication.class));
            }
        });

    }
}