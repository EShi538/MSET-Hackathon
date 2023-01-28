package com.learntodroid.switchingbetweenactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.*;

public class Registration extends AppCompatActivity {
    Button backButton;

    Button signUpButton;
    EditText usernameInput;
    EditText passwordInput;
    EditText zipInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        usernameInput = (EditText) findViewById(R.id.username);
        passwordInput = (EditText) findViewById(R.id.password);
        zipInput = (EditText) findViewById(R.id.zip);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToLogin();
            }
        });

        signUpButton = findViewById(R.id.SignUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToLoginSignedUp();
            }
        });
    }

    private void backToLogin() {
        Intent switchActivityIntent = new Intent(this, loginScreen.class);
        switchActivityIntent.putExtra("message", "From: " + Registration.class.getSimpleName());
        startActivity(switchActivityIntent);
    }

    private void backToLoginSignedUp(){
        Intent registrationIntent = new Intent(this, loginScreen.class);
        registrationIntent.putExtra("Username", usernameInput.getText().toString());
        registrationIntent.putExtra("Password", passwordInput.getText().toString());
        registrationIntent.putExtra("ZIP", zipInput.getText().toString());

        setResult(RESULT_OK, registrationIntent);
        finish();
    }
}
