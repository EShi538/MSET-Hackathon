package com.learntodroid.Fusion_fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.Comparator;

public class Leaderboard extends AppCompatActivity {
    ArrayList<User> users = new ArrayList<>();
    Button statsButton;
    Button activitiesButton;
    Button profileButton;
    String username;
    String password;
    String ZIP;
    String activity;
    String stress;
    String recentActivity;
    String age;
    TextView firstUser, secondUser, thirdUser, fourthUser, fifthUser, sixthUser, seventhUser, eighthUser, ninthUser, tenthUser;
    TextView firstScore, secondScore, thirdScore, fourthScore, fifthScore, sixthScore, seventhScore, eighthScore, ninthScore, tenthScore;

    TextView userRank, currentUser, currentScore;
    int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        ZIP = intent.getStringExtra("ZIP");
        activity = intent.getStringExtra("activity");
        stress = intent.getStringExtra("stress");
        age = intent.getStringExtra("age");
        points = Integer.parseInt(intent.getStringExtra("points"));
        recentActivity = intent.getStringExtra("recent");

        createExampleUsers();
        users.sort(new sortByScore());
        firstUser = findViewById(R.id.firstUser);
        firstUser.setText(users.get(0).getUsername());

        secondUser = findViewById(R.id.secondUser);
        secondUser.setText(users.get(1).getUsername());

        thirdUser = findViewById(R.id.thirdUser);
        thirdUser.setText(users.get(2).getUsername());

        fourthUser = findViewById(R.id.fourthUser);
        fourthUser.setText(users.get(3).getUsername());

        fifthUser = findViewById(R.id.fifthUser);
        fifthUser.setText(users.get(4).getUsername());

        sixthUser = findViewById(R.id.sixthUser);
        sixthUser.setText(users.get(5).getUsername());

        seventhUser = findViewById(R.id.seventhUser);
        seventhUser.setText(users.get(6).getUsername());

        eighthUser = findViewById(R.id.eighthUser);
        eighthUser.setText(users.get(7).getUsername());

        ninthUser = findViewById(R.id.ninthUser);
        ninthUser.setText(users.get(8).getUsername());

        tenthUser = findViewById(R.id.tenthUser);
        tenthUser.setText(users.get(9).getUsername());

        firstScore = findViewById(R.id.firstScore);
        firstScore.setText(Integer.toString(users.get(0).getXp()));

        secondScore = findViewById(R.id.secondScore);
        secondScore.setText(Integer.toString(users.get(1).getXp()));

        thirdScore = findViewById(R.id.thirdScore);
        thirdScore.setText(Integer.toString(users.get(2).getXp()));

        fourthScore = findViewById(R.id.fourthScore);
        fourthScore.setText(Integer.toString(users.get(3).getXp()));

        fifthScore = findViewById(R.id.fifthScore);
        fifthScore.setText(Integer.toString(users.get(4).getXp()));

        sixthScore = findViewById(R.id.sixthScore);
        sixthScore.setText(Integer.toString(users.get(5).getXp()));

        seventhScore = findViewById(R.id.seventhScore);
        seventhScore.setText(Integer.toString(users.get(6).getXp()));

        eighthScore = findViewById(R.id.eighthScore);
        eighthScore.setText(Integer.toString(users.get(7).getXp()));

        ninthScore = findViewById(R.id.ninthScore);
        ninthScore.setText(Integer.toString(users.get(8).getXp()));

        tenthScore = findViewById(R.id.tenthScore);
        tenthScore.setText(Integer.toString(users.get(9).getXp()));

        userRank = findViewById(R.id.userRank);
        int index = 0;
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getUsername().equals(username)){
                index = i;
            }
        }
        userRank.setText(Integer.toString(index + 1));
        currentUser = findViewById(R.id.currentUser);
        currentUser.setText(username);

        currentScore = findViewById(R.id.currentScore);
        currentScore.setText(Integer.toString(points));

        activitiesButton = findViewById(R.id.activitiesButton);
        activitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toActivities();
            }
        });

        statsButton = findViewById(R.id.statsButton);
        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toStats();
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

    private void createExampleUsers(){
        //West is negative longitude, East is post longitude, North is positive latitude, South is negative latitude
        users.add(new User("Jane", "Walk 15 minutes", 37.282293, -122.050874, 100));
        users.add(new User("Quandale", "Aerobic 30 minutes", 37.266072, -122.027485, 400));
        users.add(new User("Eric", "Meditation 15 minutes", 37.281600, -122.009422, 1030 ));
        users.add(new User("Gerald", "Meditation 15 minutes", 37.281600, -122.009421, 120));
        users.add(new User("Mason Jiro Hendley", "Yoga 30 minutes", 37.251733, -122.041970, 540));
        users.add(new User("Joe", "Yoga 10 minutes", 37.266069, -122.029250, 330));
        users.add(new User("Dire", "Yoga 10 minutes", 37.266070, -122.029251, 130));
        users.add(new User("Soooji", "Yoga 10 minutes", 37.265578, -122.014880, 400));
        users.add(new User("Daniel", "Yoga 10 minutes", 37.275145, -122.036507, 190));
        users.add(new User("John Q. Adams", "Yoga 10 minutes", 37.283785, -122.053637, 350));
        users.add(new User("Chistiano Ronaldo", "Yoga 10 minutes", 37.280194, -122.012585, 302));
        users.add(new User("Jesse", "Yoga 10 minutes", 37.269854, -122.031166, 10));
        users.add(new User("Juan", "Yoga 10 minutes", 37.262662, -122.032049, 0));

        users.add(new User(username, "Idle", 37, -122, points));
    }

    private void toStats(){
        Intent intent = new Intent(this, Community.class);
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
}

class User {
    private String username;
    private String currentActivity;
    private double latitude;
    private double longitude;
    private int xp;
    public User(String username, String currentActivity, double latitude, double longitude, int xp) {
        this.username = username;
        this.currentActivity = currentActivity;
        this.latitude = latitude;
        this.longitude = longitude;
        this.xp = xp;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getCurrentActivity() {
        return currentActivity;
    }
    public void setCurrentActivity(String currentActivity) {
        this.currentActivity = currentActivity;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public int getXp() {
        return xp;
    }
    public void setXp(int xp) {
        this.xp = xp;
    }
}

class sortByScore implements Comparator<User> {
    public int compare(User a, User b) {
        return b.getXp() - a.getXp();
    }
}


