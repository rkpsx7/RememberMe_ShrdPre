package com.example.rememberme_shrdpre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
private TextView mTvUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mTvUserName = findViewById(R.id.tvUseName);

        Intent intent = getIntent();
        String username = intent.getStringExtra("userName");
        mTvUserName.setText(username);
    }
}