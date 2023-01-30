package com.learntodroid.Fusion_fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

//import com.google.android.gms.fitness.Scopes;
//import com.google.android.gms.fitness.data.Scope;


public class Community extends AppCompatActivity {
    Button leaderboardButton;
    ArrayList<Account> accounts = new ArrayList<>();
    Button activitiesButton;
    TextView firstUser, secondUser, thirdUser, fourthUser, fifthUser, sixthUser, seventhUser, eighthUser, ninthUser, tenthUser;
    TextView firstScore, secondScore, thirdScore, fourthScore, fifthScore, sixthScore, seventhScore, eighthScore, ninthScore, tenthScore;
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
        setContentView(R.layout.community);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        ZIP = intent.getStringExtra("ZIP");
        activity = intent.getStringExtra("activity");
        stress = intent.getStringExtra("stress");
        age = intent.getStringExtra("age");
        points = Integer.parseInt(intent.getStringExtra("points"));
        recentActivity = intent.getStringExtra("recent");

        addExampleUsers();
        ArrayList<Account> sameZIP = new ArrayList<>();
        for(Account account: accounts){
            if(account.ZIP == Integer.parseInt(ZIP)){
                sameZIP.add(account);
            }
        }

        ArrayList<Account> sameInterests = new ArrayList<>();
        for(Account account: sameZIP){
            if(account.interest.equals(activity)){
                sameInterests.add(account);
            }
        }

        if(0 < sameInterests.size()) {
            firstUser = findViewById(R.id.firstUser);
            firstUser.setText(sameInterests.get(0).username);
        }
        if(1 < sameInterests.size()) {
            secondUser = findViewById(R.id.secondUser);
            secondUser.setText(sameInterests.get(1).username);
        }
        if(2 < sameInterests.size()) {
            thirdUser = findViewById(R.id.thirdUser);
            thirdUser.setText(sameInterests.get(2).username);
        }
        if(3 < sameInterests.size()) {
            fourthUser = findViewById(R.id.fourthUser);
            fourthUser.setText(sameInterests.get(3).username);
        }
        if(4 < sameInterests.size()) {
            fifthUser = findViewById(R.id.fifthUser);
            fifthUser.setText(sameInterests.get(4).username);
        }
        if(5 < sameInterests.size()) {
            sixthUser = findViewById(R.id.sixthUser);
            sixthUser.setText(sameInterests.get(5).username);
        }
        if(6 < sameInterests.size()) {
            seventhUser = findViewById(R.id.seventhUser);
            seventhUser.setText(sameInterests.get(6).username);
        }
        if(7 < sameInterests.size()) {
            eighthUser = findViewById(R.id.eighthUser);
            eighthUser.setText(sameInterests.get(7).username);
        }
        if(8 < sameInterests.size()) {
            ninthUser = findViewById(R.id.ninthUser);
            ninthUser.setText(sameInterests.get(8).username);
        }
        if(9 < sameInterests.size()) {
            tenthUser = findViewById(R.id.tenthUser);
            tenthUser.setText(sameInterests.get(9).username);
        }

        if(0 < sameInterests.size()) {
            firstScore = findViewById(R.id.firstScore);
            firstScore.setText(Integer.toString(accounts.get(0).score));
        }
        if(1 < sameInterests.size()) {
            secondScore = findViewById(R.id.secondScore);
            secondScore.setText(Integer.toString(accounts.get(1).score));
        }
        if(2 < sameInterests.size()) {
            thirdScore = findViewById(R.id.thirdScore);
            thirdScore.setText(Integer.toString(accounts.get(2).score));
        }
        if(3 < sameInterests.size()) {
            fourthScore = findViewById(R.id.fourthScore);
            fourthScore.setText(Integer.toString(accounts.get(3).score));
        }
        if(4 < sameInterests.size()) {
            fifthScore = findViewById(R.id.fifthScore);
            fifthScore.setText(Integer.toString(accounts.get(4).score));
        }
        if(5 < sameInterests.size()) {
            sixthScore = findViewById(R.id.sixthScore);
            sixthScore.setText(Integer.toString(accounts.get(5).score));
        }
        if(6 < sameInterests.size()) {
            seventhScore = findViewById(R.id.seventhScore);
            seventhScore.setText(Integer.toString(accounts.get(6).score));
        }
        if(7 < sameInterests.size()) {
            eighthScore = findViewById(R.id.eighthScore);
            eighthScore.setText(Integer.toString(accounts.get(7).score));
        }
        if(8 < sameInterests.size()) {
            ninthScore = findViewById(R.id.ninthScore);
            ninthScore.setText(Integer.toString(accounts.get(8).score));
        }
        if(9 < sameInterests.size()) {
            tenthScore = findViewById(R.id.tenthScore);
            tenthScore.setText(Integer.toString(accounts.get(9).score));
        }

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

    private void addExampleUsers(){
        accounts.add(new Account("Gerald", "Pickleball", 95070, 100));
        accounts.add(new Account("John Q. Adams", "Running", 95070, 500));
        accounts.add(new Account("soooji", "Running", 95070, 3460));
        accounts.add(new Account("Mason J. Hendley", "Swimming", 95070, 240));
        accounts.add(new Account("Yashom", "Tennis", 95070, 565));
        accounts.add(new Account("Emily", "Basketball", 40032, 290));
        accounts.add(new Account("Erick", "Running", 64732, 350));
        accounts.add(new Account("Dire", "Football", 95070, 200));
        accounts.add(new Account("Daniel", "Running", 95070, 200));
        accounts.add(new Account("Ethan", "Running", 134567, 450));
        accounts.add(new Account("Andrew", "Running", 95070, 120));
        accounts.add(new Account("Joon-Joon", "Running", 95070, 190));
        accounts.add(new Account("Amy", "Running", 95070, 340));
        accounts.add(new Account("Olivia", "Tennis",12567, 950));
        accounts.add(new Account("Steve", "Running", 3900, 120));
        accounts.add(new Account("Alex", "Baseball", 95070, 570));
        accounts.add(new Account("DreamNotFound", "Running", 95070, 120));
    }
}

class Account{
    String username;
    String interest;
    int ZIP;
    int score;
    public Account(String username, String interest, int ZIP, int score){
        this.username = username;
        this.interest = interest;
        this.ZIP = ZIP;
        this.score = score;
    }
}