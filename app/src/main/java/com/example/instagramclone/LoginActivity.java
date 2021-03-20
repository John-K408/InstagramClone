package com.example.instagramclone;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";
    EditText etUsername;
    EditText etPassword;
    Button btnLogin;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etuserName);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        toolbar = (Toolbar)(findViewById(R.id.toolbar));
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if(ParseUser.getCurrentUser() != null){
            //goMainActivity();
            goFeedActivity();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG,"log in button clicked");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if(username.length() < 3){
                    Snackbar.make(etUsername,"Sorry, your Username must be at least 3 characters long ",Snackbar.LENGTH_LONG).show();

                }
                else if(password.length() < 3){
                Snackbar.make(etPassword,"Sorry, your Password must be at least 3 characters long ",Snackbar.LENGTH_LONG).show();
                }
                else{
                    logInUser(username,password);
                }
            }
        });

    }
    public void logInUser(String username, String password){
        Log.i(TAG,"Logging In");
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e != null){
                    Log.i(TAG,"Issue with logIn",e);
                    Snackbar.make(etPassword,"Issue with logIn",Snackbar.LENGTH_LONG).show();
                    return;
                }
                else{
                    goFeedActivity();
                    Toast.makeText(LoginActivity.this,"Successfully logged in!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void goMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void goFeedActivity(){
        Intent intent = new Intent(this,feedActivity.class);
        startActivity(intent);
        finish();
    }
}