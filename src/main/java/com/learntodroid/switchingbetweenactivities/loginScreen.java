package com.learntodroid.switchingbetweenactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.*;
import java.util.*;

public class loginScreen extends AppCompatActivity {
    private static final int REGISTRATION_REQUEST_CODE = 0;
    Button submitButton;
    Button registerButton;
    EditText usernameInput;
    EditText passwordInput;

    String username;
    String password;
    String ZIP;
    String activity;
    String stress;
    String age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        usernameInput = (EditText) findViewById(R.id.usernameInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);

        submitButton = findViewById(R.id.loginButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (accountExists(usernameInput.getText().toString(), passwordInput.getText().toString())) {
                        getDetails();
                        toHomePage();
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
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

    private boolean accountExists(String username, String password) throws FileNotFoundException {
        File dir = new File("/storage/emulated/0/Download/");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File file : directoryListing) {
                if (!file.getPath().contains("MSET19286")) {
                    continue;
                }
                try {
                    String pathname = file.getPath();
                    int index = pathname.indexOf("MSET19286") + 9;
                    String accountDetails = pathname.substring(index);
                    String[] details = accountDetails.split("---");
                    String username1 = details[0];
                    String password1 = details[1];
                    if (username1.equals(username) && password1.equals(password)) {
                        return true;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }
        return false;
    }

    private void getDetails() {
        File dir = new File("/storage/emulated/0/Download/");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File file : directoryListing) {
                if (!file.getPath().contains("MSET19286")) {
                    continue;
                }
                try {
                    String pathname = file.getPath();
                    int index = pathname.indexOf("MSET19286") + 9;
                    String accountDetails = pathname.substring(index);
                    String[] details = accountDetails.split("---");
                    username = details[0];
                    password = details[1];
                    ZIP = details[2];
                    age = details[3];
                    stress = details[4];
                    activity = details[5];
                } catch (Exception e) {
                    continue;
                }
            }
        }
    }

    private void toHomePage() {
        Intent intent = new Intent(this, homeScreen.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        intent.putExtra("ZIP", ZIP);
        intent.putExtra("age", age);
        intent.putExtra("stress", stress);
        intent.putExtra("activity", activity);
        startActivity(intent);
    }

    private void toRegistration() {
        Intent intent = new Intent(this, Registration.class);
        startActivityForResult(intent, REGISTRATION_REQUEST_CODE);
    }
}

