package com.learntodroid.switchingbetweenactivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.IOException;
import java.util.*;

public class Registration extends AppCompatActivity {
    Button backButton;
    Button signUpButton;
    EditText usernameInput;
    EditText passwordInput;
    String zipInput;

    int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        if (ContextCompat.checkSelfPermission(Registration.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(Registration.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(Registration.this, "This app needs access to your location to provide community interaction features such as leaderboards.", Toast.LENGTH_LONG).show();

                ActivityCompat.requestPermissions(Registration.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(Registration.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        } else {
            FusedLocationProviderClient fusedLocationClient;
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(Registration.this);

            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(Registration.this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            Geocoder geocoder = new Geocoder(Registration.this, Locale.getDefault());
                            List<Address> addresses = null;
                            try {
                                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            if (addresses != null && addresses.size() > 0) {
                                zipInput = addresses.get(0).getPostalCode();
                            }
                        }
                    });

        }


        usernameInput = (EditText) findViewById(R.id.username);
        passwordInput = (EditText) findViewById(R.id.password);
        //zipInput = (EditText) findViewById(R.id.zip);

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
                next();
            }
        });
    }

    private void backToLogin() {
        Intent switchActivityIntent = new Intent(this, loginScreen.class);
        startActivity(switchActivityIntent);
    }

//    private void next(){
//        Intent registrationIntent = new Intent(this, getInfo.class);
//        registrationIntent.putExtra("Username", usernameInput.getText().toString());
//        registrationIntent.putExtra("Password", passwordInput.getText().toString());
//        registrationIntent.putExtra("ZIP", zipInput.getText().toString());
//        setResult(RESULT_OK, registrationIntent);
//        finish();
//    }

    private void next(){
        // start the SecondActivity
        Intent intent = new Intent(this, getInfo.class);
        intent.putExtra("Username", usernameInput.getText().toString());
        intent.putExtra("Password", passwordInput.getText().toString());
        intent.putExtra("ZIP", zipInput);
        startActivity(intent);
    }
}