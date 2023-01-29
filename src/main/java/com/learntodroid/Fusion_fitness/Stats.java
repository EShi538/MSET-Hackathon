package com.learntodroid.Fusion_fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//import com.google.android.gms.fitness.Scopes;
//import com.google.android.gms.fitness.data.Scope;


public class Stats extends AppCompatActivity {
    Button leaderboardButton;

    Button activitiesButton;

    Button profileButton;
    String recentActivity;
    String username;
    String password;
    String ZIP;
    String age;
    String stress;
    String activity;
    int points;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);

//        float heartRate;
//        float sleepTime;
//        Fitness.getSensorsClient(this, GoogleSignIn.getLastSignedInAccount(this))
//                .findDataSources(new DataSourcesRequest.Builder()
//                        .setDataTypes(DataType.TYPE_HEART_RATE_BPM)
//                        .setDataSourceTypes(DataSource.TYPE_RAW)
//                        .build())
//                .addOnSuccessListener(new OnSuccessListener<List<DataSource>>() {
//                    @Override
//                    public void onSuccess(List<DataSource> dataSources) {
//                        for (DataSource dataSource : dataSources) {
//                            DataType dataType = dataSource.getDataType();
//                            // Extract the type of data (in this case, heart rate)
//                            if (dataType.equals(DataType.TYPE_HEART_RATE_BPM)) {
//                                // Read the data from the data source
//                                Fitness.getHistoryClient(Stats.this, Objects.requireNonNull(GoogleSignIn.getLastSignedInAccount(Stats.this)))
//                                        .readData(new DataReadRequest.Builder()
//                                                .read(dataSource)
//                                                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
//                                                .build())
//                                        .addOnSuccessListener(new OnSuccessListener<DataReadResponse>() {
//                                            @Override
//                                            public void onSuccess(DataReadResponse dataReadResponse) {
//                                                // Extract the data points
//                                                List<DataPoint> dataPoints = dataReadResponse.getDataSet(dataType).getDataPoints();
//                                                for (DataPoint dataPoint : dataPoints) {
//                                                    // Extract the heart rate value
//                                                    heartRate = dataPoint.getValue(Field.FIELD_BPM).asFloat();
//
//                                                }
//                                            }
//                                        });
//                            }
//                        }
//                    }
//                });
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        ZIP = intent.getStringExtra("ZIP");
        activity = intent.getStringExtra("activity");
        stress = intent.getStringExtra("stress");
        age = intent.getStringExtra("age");
        points = Integer.parseInt(intent.getStringExtra("points"));
        recentActivity = intent.getStringExtra("recent");

        leaderboardButton = findViewById(R.id.leaderboardButton);
        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLeaderboard();
            }
        });

        activitiesButton = findViewById(R.id.activitiesButton);
        activitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toActivities();
            }
        });

        profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toProfile();
            }
        });
    }
    private void toLeaderboard(){
        Intent intent = new Intent(this, Leaderboard.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        intent.putExtra("ZIP", ZIP);
        intent.putExtra("activity", activity);
        intent.putExtra("stress", stress);
        intent.putExtra("age", age);
        intent.putExtra("points", Integer.toString(points));
        intent.putExtra("recent", recentActivity);
        startActivity(intent);
    }

    private void toActivities(){
        Intent intent = new Intent(this, Activities.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        intent.putExtra("ZIP", ZIP);
        intent.putExtra("activity", activity);
        intent.putExtra("stress", stress);
        intent.putExtra("age", age);
        intent.putExtra("points", Integer.toString(points));
        intent.putExtra("recent", recentActivity);
        startActivity(intent);
    }

    private void toProfile(){
        // start the SecondActivity
        Intent intent = new Intent(this, Profile.class);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        intent.putExtra("ZIP", ZIP);
        intent.putExtra("activity", activity);
        intent.putExtra("stress", stress);
        intent.putExtra("age", age);
        intent.putExtra("points", Integer.toString(points));
        intent.putExtra("recent", recentActivity);
        startActivity(intent);
    }
}
