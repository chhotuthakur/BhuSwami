package com.nilesh.bhuswami.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nilesh.bhuswami.R;

public class SplashActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

if (currentUser != null){

    startActivity(new Intent(SplashActivity.this, MainActivity.class));

}else {


    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(SplashActivity.this, AccountActivity.class));
        }
    }, 3000);
}
    }
}