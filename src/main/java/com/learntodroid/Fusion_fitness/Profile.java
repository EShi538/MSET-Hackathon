package com.learntodroid.Fusion_fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    Button statsButton;

    Button leaderboardButton;
    String recentActivity;
    Button activitiesButton;
    TextView activity;
    TextView stress;
    TextView username;
    TextView zip;

    TextView age;

    String username1;
    String password1;
    String ZIP1;
    String age1;
    String stress1;
    String activity1;
    int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        Intent intent = getIntent();
        username1 = intent.getStringExtra("username");
        password1 = intent.getStringExtra("password");
        ZIP1 = intent.getStringExtra("ZIP");
        age1 = intent.getStringExtra("age");
        stress1 = intent.getStringExtra("stress");
        activity1 = intent.getStringExtra("activity");
        points = Integer.parseInt(intent.getStringExtra("points"));
        recentActivity = intent.getStringExtra("recent");
        username = findViewById(R.id.usernameField);
        username.setText(username1);

        activity = findViewById(R.id.activityField);
        activity.setText(activity1);

        stress = findViewById(R.id.stressField);
        stress.setText(stress1);

        zip = findViewById(R.id.zipField);
        zip.setText(ZIP1);

        age = findViewById(R.id.ageField);
        age.setText(age1);

        statsButton = findViewById(R.id.statsButton);
        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toStats();
            }
        });

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

    }

    private void toLeaderboard(){
        Intent intent = new Intent(this, Leaderboard.class);
        intent.putExtra("username", username1);
        intent.putExtra("password", password1);
        intent.putExtra("ZIP", ZIP1);
        intent.putExtra("activity", activity1);
        intent.putExtra("stress", stress1);
        intent.putExtra("age", age1);
        intent.putExtra("points", Integer.toString(points));
        intent.putExtra("recent", recentActivity);
        startActivity(intent);
    }

    private void toStats(){
        Intent intent = new Intent(this, Community.class);
        intent.putExtra("username", username1);
        intent.putExtra("password", password1);
        intent.putExtra("ZIP", ZIP1);
        intent.putExtra("activity", activity1);
        intent.putExtra("stress", stress1);
        intent.putExtra("age", age1);
        intent.putExtra("points", Integer.toString(points));
        intent.putExtra("recent", recentActivity);
        startActivity(intent);
    }

    private void toActivities(){
        Intent intent = new Intent(this, Activities.class);
        intent.putExtra("username", username1);
        intent.putExtra("password", password1);
        intent.putExtra("ZIP", ZIP1);
        intent.putExtra("activity", activity1);
        intent.putExtra("stress", stress1);
        intent.putExtra("age", age1);
        intent.putExtra("points", Integer.toString(points));
        intent.putExtra("recent", recentActivity);
        startActivity(intent);
    }
}

//⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀       ⣠⣴⣶⣿⣿⣷⣶⣄⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀
//        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣾⣿⣿⡿⢿⣿⣿⣿⣿⣿⣿⣿⣷⣦⡀⠀⠀⠀⠀⠀
//        ⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⣿⡟⠁⣰⣿⣿⣿⡿⠿⠻⠿⣿⣿⣿⣿⣧⠀⠀⠀⠀
//        ⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⠏⠀⣴⣿⣿⣿⠉⠀⠀⠀⠀⠀⠈⢻⣿⣿⣇⠀⠀⠀
//        ⠀⠀⠀⠀⢀⣠⣼⣿⣿⡏⠀⢠⣿⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⡀⠀⠀
//        ⠀⠀⠀⣰⣿⣿⣿⣿⣿⡇⠀⢸⣿⣿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⡇⠀⠀
//        ⠀⠀⢰⣿⣿⡿⣿⣿⣿⡇⠀⠘⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⢀⣸⣿⣿⣿⠁⠀⠀
//        ⠀⠀⣿⣿⣿⠁⣿⣿⣿⡇⠀⠀⠻⣿⣿⣿⣷⣶⣶⣶⣶⣶⣿⣿⣿⣿⠃⠀⠀⠀
//        ⠀⢰⣿⣿⡇⠀⣿⣿⣿⠀⠀⠀⠀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠁⠀⠀⠀⠀
//        ⠀⢸⣿⣿⡇⠀⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠉⠛⠛⠛⠉⢉⣿⣿⠀⠀⠀⠀⠀⠀
//        ⠀⢸⣿⣿⣇⠀⣿⣿⣿⠀⠀⠀⠀⠀⢀⣤⣤⣤⡀⠀⠀⢸⣿⣿⣿⣷⣦⠀⠀⠀
//        ⠀⠀⢻⣿⣿⣶⣿⣿⣿⠀⠀⠀⠀⠀⠈⠻⣿⣿⣿⣦⡀⠀⠉⠉⠻⣿⣿⡇⠀⠀
//        ⠀⠀⠀⠛⠿⣿⣿⣿⣿⣷⣤⡀⠀⠀⠀⠀⠈⠹⣿⣿⣇⣀⠀⣠⣾⣿⣿⡇⠀⠀
//        ⠀⠀⠀⠀⠀⠀⠀⠹⣿⣿⣿⣿⣦⣤⣤⣤⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠀
//        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⢿⣿⣿⣿⣿⣿⣿⠿⠋⠉⠛⠋⠉⠉⠁⠀⠀⠀⠀
//        ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠁



