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
                try {
                    if(accountExists(usernameInput.getText().toString(), passwordInput.getText().toString())){
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
        if(directoryListing != null){
            for(File file: directoryListing){
                if(!file.getPath().contains("MSET19286")){
                    continue;
                }
                try {
                    String pathname = file.getPath();
                    int index = pathname.indexOf("MSET19286") + 9;
                    String accountDetails = pathname.substring(index);
                    String[] details = accountDetails.split("-");
                    String username1 = details[0];
                    String password1 = details[1];
                    if (username1.equals(username) && password1.equals(password)) {
                        return true;
                    }
                }
                catch(Exception e){
                    continue;
                }
            }
        }
        return false;
    }

    private void toHomePage() {
        Intent switchActivityIntent = new Intent(this, homeScreen.class);
        switchActivityIntent.putExtra("message", "From: " + loginScreen.class.getSimpleName());
        startActivity(switchActivityIntent);
    }
    //
    private void toRegistration() {
        Intent intent = new Intent(this, Registration.class);
        startActivityForResult(intent, REGISTRATION_REQUEST_CODE);
    }

//    @Override
//    protected void onA(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        // check that it is the SecondActivity with an OK result
//        if (requestCode == REGISTRATION_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
//                // get String data from Intent
//                String registrationUsername = data.getStringExtra("Username");
//                String registrationPassword = data.getStringExtra("Password");
//                String registrationZIP = data.getStringExtra("ZIP");
//                accounts.add(new Account(registrationUsername, registrationPassword, Integer.parseInt(registrationZIP)));
//            }
//        }
//    }
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