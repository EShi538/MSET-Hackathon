package com.learntodroid.switchingbetweenactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.*;

public class loginScreen extends AppCompatActivity {
    private static final int REGISTRATION_REQUEST_CODE = 0;
    Button submitButton;
    Button registerButton;
    EditText usernameInput;
    EditText passwordInput;

    ArrayList<Account> accounts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        accounts.add(new Account("Joe Balls", "123", 16437));

        usernameInput = (EditText) findViewById(R.id.usernameInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);

        submitButton = findViewById(R.id.loginButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(accountExists(usernameInput.getText().toString(), passwordInput.getText().toString())){
                    switchActivitiesWithData();
                }
            }
        });

        registerButton = findViewById(R.id.registrationButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toRegistration();
            }
        });

    }

    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, SecondActivity.class);
        startActivity(switchActivityIntent);
    }

    private boolean accountExists(String username, String password) {
        for(int i = 0; i < accounts.size(); i++) {
            Account currentAccount = accounts.get(i);
            if(username.equals(currentAccount.username) && password.equals(currentAccount.password)){
                return true;
            }
        }
        return false;
    }

    private void switchActivitiesWithData() {
        Intent switchActivityIntent = new Intent(this, SecondActivity.class);
        switchActivityIntent.putExtra("message", "From: " + loginScreen.class.getSimpleName());
        startActivity(switchActivityIntent);
    }
//
    private void toRegistration() {
        Intent intent = new Intent(this, Registration.class);
        startActivityForResult(intent, REGISTRATION_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == REGISTRATION_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // get String data from Intent
                String registrationUsername = data.getStringExtra("Username");
                String registrationPassword = data.getStringExtra("Password");
                String registrationZIP = data.getStringExtra("ZIP");
                accounts.add(new Account(registrationUsername, registrationPassword, Integer.parseInt(registrationZIP)));
            }
        }
    }
}

class Account{
    String username;
    String password;
    int zipCode;
    public Account(String userName, String password, int zipCode){
        this.username = userName;
        this.password = password;
        this.zipCode = zipCode;
    }
}
