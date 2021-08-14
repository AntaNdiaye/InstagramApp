package com.example.instagramapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LonginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private  EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button signUp;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_login);

        if( ParseUser.getCurrentUser() != null){
            goMainActivity();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        signUp = findViewById(R.id.signup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"onClick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username,password);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = new ParseUser();
                user.setUsername(etUsername.getText().toString());
                user.setPassword(etPassword.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null){
                            Log.e(TAG,"Issue with sign in button", e);
                            Toast.makeText(LonginActivity.this,"Sign Up Successful",Toast.LENGTH_LONG).show();
                            //setContentView(R.layout.activity_login);
                        }else {
                            Toast.makeText(LonginActivity.this,"Already exist",Toast.LENGTH_SHORT).show();


                        }
                    }
                });
            }
        });

    }

    private void loginUser(String username, String password) {
        Log.i(TAG,"Attempting to login user" + username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null){
                    //TODO: better error handling
                    Log.e(TAG,"Issue with login", e);
                    Log.e(TAG,e.toString());
                    Toast.makeText(LonginActivity.this,"Issue with login!",Toast.LENGTH_LONG).show();
                    return;
                }
                goMainActivity();
                Toast.makeText(LonginActivity.this,"Success!",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
