package com.example.rememberme_shrdpre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mEtUserName, mEtPassword;
    private CheckBox mChkBoxRememberMe;
    private Button mBtnLogin;
    private String emailVerificationCode = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        SharedPreferences sp = getSharedPreferences("credentialsZ",MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();

        if (sp.contains("userName") && sp.contains("Password")) {
            Intent PreIntent = new Intent(MainActivity.this, HomeActivity.class);
            PreIntent.putExtra("userName",sp.getString("userName",""));
            startActivity(PreIntent);
        }

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (emailVerification() && passwordVerification()) {
                        if (mChkBoxRememberMe.isChecked()) {
                            ed.putString("userName", mEtUserName.getText().toString());
                            ed.putString("Password", mEtPassword.getText().toString().trim());
                            ed.apply();
                        }
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        intent.putExtra("userName",mEtUserName.getText().toString());
                        startActivity(intent);
                    }



            }
        });
    }

    private void initViews() {
        mEtUserName = findViewById(R.id.etUserName);
        mEtPassword = findViewById(R.id.etPassword);
        mChkBoxRememberMe = findViewById(R.id.chkBoxRememberMe);
        mBtnLogin = findViewById(R.id.btnLogin);
    }
    private boolean emailVerification() {
        if (mEtUserName.getText().toString().matches(emailVerificationCode))
            return true;
        else {
            mEtUserName.setError("Invalid Email");
            return false;
        }
    }

    private boolean passwordVerification() {
        if (mEtPassword.getText().toString().trim().length() >= 6)
            return true;
        else {
            mEtPassword.setError("Password is very short");
            return false;
        }
    }
}