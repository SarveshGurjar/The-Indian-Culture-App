package com.example.agencyapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Authentication extends AppCompatActivity {
    LottieAnimationView LAV;
    private TextView admin;
    //Firebase google
    private ImageView google_btn;

    GoogleSignInClient client;
    FirebaseAuth auth;
    FirebaseFirestore fStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
    LAV = findViewById(R.id.google_lottie);
    LAV.setAnimation(R.raw.google_logo);
    LAV.playAnimation();
    LAV.loop(true);

//    finding IDS
        google_btn=findViewById(R.id.Glogin);
        admin = findViewById(R.id.admin);

        //Firebase
        auth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        //google Login
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        client = GoogleSignIn.getClient(this, options);
        google_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FirebaseUser user = auth.getCurrentUser();
//                DocumentReference DF = fStore.collection("Users").document(user.getUid());
//                Map<String, Object> userDetails = new HashMap<>();
//                userDetails.put("Role","1");
//                DF.set(userDetails);
                SignIn();
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Authentication.this,add_art.class));
            }
        });



    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                auth.signInWithCredential(credential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Authentication.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }


    //Update UI

    public void updateUI(GoogleSignInAccount account) {
        //If app login is Successful
        if (account != null) {

            startActivity(new Intent(Authentication.this , DashboardActivity .class));
            Toast.makeText(this, "Welcome Back", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "You are not signed in", Toast.LENGTH_LONG).show();
        }
    }

    private void SignIn(){
        Intent i = client.getSignInIntent();
        startActivityForResult(i, 1234);
    }

}