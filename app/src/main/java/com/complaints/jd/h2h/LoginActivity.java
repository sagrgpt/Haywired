package com.complaints.jd.h2h;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("Login");
//        setSupportActionBar(toolbar);
    }

    public void onLogin(View view) {
    }

    public void onRegister(View view) {
    }

    public void onGuest(View view) {
        startActivity(new Intent(LoginActivity.this,MainTabActivity.class));
    }
}
