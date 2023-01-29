package com.learntodroid.switchingbetweenactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.io.*;
import java.util.*;

public class getInfo extends AppCompatActivity {
    Spinner ageDropdown;
    Spinner stressDropdown;
    Spinner physicalDropdown;
    String username;
    String password;
    String ZIP;
    String age;
    String stress;
    String physicality;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_info);

        Intent intent = getIntent();
        username = intent.getStringExtra("Username");
        password = intent.getStringExtra("Password");
        ZIP = intent.getStringExtra("ZIP");

        ageDropdown = findViewById(R.id.AgeSpinner);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.Ages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ageDropdown.setAdapter(adapter);
        age = ageDropdown.getSelectedItem().toString();

        stressDropdown = findViewById(R.id.StressSpinner);
        ArrayAdapter<CharSequence>adapter1=ArrayAdapter.createFromResource(this, R.array.Stress, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        stressDropdown.setAdapter(adapter1);
        stress = stressDropdown.getSelectedItem().toString();

        physicalDropdown = findViewById(R.id.physicalSpinner);
        ArrayAdapter<CharSequence>adapter2=ArrayAdapter.createFromResource(this, R.array.Activities, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        physicalDropdown.setAdapter(adapter2);
        physicality = physicalDropdown.getSelectedItem().toString();

        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createFile();
                submit();
            }
        });
    }

    private void createFile(){
        try {
            File root = new File("/storage/emulated/0/Download/MSET19286" + username + "---" + password + "---" + ZIP + "---" + age + "---" + stress + "---" + physicality + "---.txt");
            boolean result = root.createNewFile();
        }
        catch(IOException e) {
            e.printStackTrace();    //prints exception if any
        }
    }
    private void submit(){
        // start the SecondActivity
        Intent intent = new Intent(this, loginScreen.class);
        startActivity(intent);
    }
}