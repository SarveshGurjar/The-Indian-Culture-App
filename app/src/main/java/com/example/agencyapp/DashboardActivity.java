package com.example.agencyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity {

private ImageView btn1,btn2,btn3,btn4;
    GoogleSignInClient client;

TextView btn,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btn1= findViewById(R.id.arts);
        btn2= findViewById(R.id.cuisines);
        btn3= findViewById(R.id.festival);
        btn4= findViewById(R.id.tourism);
        btn= findViewById(R.id.calbtn);
//        logout = findViewById(R.id.logout);

        btn1.setOnClickListener(v-> startActivity(new Intent(this, art.class)));
        btn2.setOnClickListener(v-> startActivity(new Intent(this,cuisines.class)));
        btn3.setOnClickListener(v-> startActivity(new Intent(this,festival.class)));
        btn4.setOnClickListener(v-> startActivity(new Intent(this,tourism.class)));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://calendar.google.com/calendar/u/0/embed?height=600&wkst=1&bgcolor=%23ffffff&ctz=Asia%2FKolkata&title=EVENT%20CALENDAR&src=b21rYXJkb2dyYTk1QGdtYWlsLmNvbQ&color=%23039BE5&authuser=2");
            }
        });

//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               signOut();
//            }
//        });

    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(new Intent().ACTION_VIEW,uri));
    }
//
//    private void signOut() {
//        client.signOut()
//                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        // ...
//                        startActivity(new Intent(DashboardActivity.this,MainActivity.class));
//                    }
//                });
//    }
}
