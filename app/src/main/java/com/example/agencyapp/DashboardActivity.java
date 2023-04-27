package com.example.agencyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {
LinearLayout btn1,btn2,btn3;
TextView btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btn1= findViewById(R.id.artbtn);
        btn2= findViewById(R.id.cuisbtn);
        btn3= findViewById(R.id.festbtn);
        btn= findViewById(R.id.calbtn);

        btn.setOnClickListener(v-> startActivity(new Intent(this, art.class)));
        btn1.setOnClickListener(v-> startActivity(new Intent(this,cuisines.class)));
        btn2.setOnClickListener(v-> startActivity(new Intent(this,festival.class)));
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://calendar.google.com/calendar/u/0/embed?height=600&wkst=1&bgcolor=%23ffffff&ctz=Asia%2FKolkata&title=EVENT%20CALENDAR&src=b21rYXJkb2dyYTk1QGdtYWlsLmNvbQ&color=%23039BE5&authuser=2");
            }
        });

    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(new Intent().ACTION_VIEW,uri));
    }
}
