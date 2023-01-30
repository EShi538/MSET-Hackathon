package com.learntodroid.Fusion_fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


import java.io.File;

public class Activities extends AppCompatActivity {
    Button statsButton;
    Button leaderboardButton;
    Button finishButton;
    Button profileButton;
    String username;
    Button updateButton;
    Activity recAc1, recAc2, recAc3;
    Button activity1, activity2, activity3;
    String password;
    String ZIP;
    String activity;
    String stress;
    String age;
    String recentActivity;
    Spinner excersizeSpinner, socialSpinner, stressSpinner;

    ArrayList<Activity> activities = new ArrayList<>();
    TextView recentText;
    int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activities);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        ZIP = intent.getStringExtra("ZIP");
        activity = intent.getStringExtra("activity");
        stress = intent.getStringExtra("stress");
        age = intent.getStringExtra("age");
        points = Integer.parseInt(intent.getStringExtra("points"));
        recentActivity = intent.getStringExtra("recent");

        addActivities();

        excersizeSpinner = findViewById(R.id.excersizeSpinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.Stress, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        excersizeSpinner.setAdapter(adapter);
        int physicalQuotient = Integer.parseInt(excersizeSpinner.getSelectedItem().toString());

        socialSpinner = findViewById(R.id.socialSpinner);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this, R.array.Stress, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        socialSpinner.setAdapter(adapter1);
        int socialQuotient = Integer.parseInt(excersizeSpinner.getSelectedItem().toString());

        stressSpinner = findViewById(R.id.stressSpinner);
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this, R.array.Stress, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        stressSpinner.setAdapter(adapter2);
        int stressQuotient = Integer.parseInt(excersizeSpinner.getSelectedItem().toString());

        profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toProfile();
            }
        });

        leaderboardButton = findViewById(R.id.leaderboardButton);
        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLeaderboard();
            }
        });

        recentText = findViewById(R.id.recentActivity);
        recentText.setText(recentActivity);

        activity1 = findViewById(R.id.activity1);
        activity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                points += recAc1.points;
                File file = new File(getFile());
                File newFile = new File(newPathname(file.getPath()));
                boolean result = file.renameTo(newFile);

                String oldActivity = recentActivity;
                recentActivity = activity1.getText().toString();
                file = new File(getFile());
                newFile = new File(updateActivity(file.getPath(), oldActivity));
                result = file.renameTo(newFile);

                activity1.setVisibility(view.GONE);
            }
        });

        activity2 = findViewById(R.id.activity2);
        activity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                points += recAc2.points;
                File file = new File(getFile());
                File newFile = new File(newPathname(file.getPath()));
                boolean result = file.renameTo(newFile);

                String oldActivity = recentActivity;
                recentActivity = activity2.getText().toString();
                file = new File(getFile());
                newFile = new File(updateActivity(file.getPath(), oldActivity));
                result = file.renameTo(newFile);
                activity2.setVisibility(view.GONE);
            }
        });

        activity3 = findViewById(R.id.activity3);
        activity3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                points += recAc3.points;
                File file = new File(getFile());
                File newFile = new File(newPathname(file.getPath()));
                boolean result = file.renameTo(newFile);

                String oldActivity = recentActivity;
                recentActivity = activity3.getText().toString();
                file = new File(getFile());
                newFile = new File(updateActivity(file.getPath(), oldActivity));
                result = file.renameTo(newFile);
                activity3.setVisibility(view.GONE);
            }
        });

        recommendActivities(physicalQuotient, stressQuotient, 10 - socialQuotient);
        activity1.setText(recAc1.name + " for " + recAc1.duration + " minutes (" + recAc1.points + "SP)");
        activity2.setText(recAc2.name + " for " + recAc2.duration + " minutes (" + recAc2.points + "SP)");
        activity3.setText(recAc3.name + " for " + recAc3.duration + " minutes (" + recAc3.points + "SP)");

        updateButton = findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recommendActivities(physicalQuotient, stressQuotient, 10 - socialQuotient);
                activity1.setText(recAc1.name + " for " + recAc1.duration + " minutes (" + recAc1.points + "SP)");
                activity2.setText(recAc2.name + " for " + recAc2.duration + " minutes (" + recAc2.points + "SP)");
                activity3.setText(recAc3.name + " for " + recAc3.duration + " minutes (" + recAc3.points + "SP)");
            }
        });

        statsButton = findViewById(R.id.statsButton);
        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toStats();
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

    private void addActivities(){
        //physical
        activities.add(new Activity("Walk", 0, 200, 5));
        activities.add(new Activity("Walk", 0, 400, 30));
        activities.add(new Activity("Jog", 0, 600, 10 ));
        activities.add(new Activity("Jog", 0, 800, 20));
        activities.add(new Activity("Aerobic Exercise", 0, 1000, 30));

        //mental
        activities.add(new Activity("Meditate", 1, 100, 5)); //5
        activities.add(new Activity("Meditate", 1, 200, 10));  //6
        activities.add(new Activity("Meditate", 1, 300, 15)); //7
        activities.add(new Activity("Yoga", 1, 600, 15)); //8
        activities.add(new Activity("Yoga", 1, 800, 30)); //9
    }

    private void recommendActivities(int physical, int mental, int social){
        //physical
        if(physical < 2){
            recAc1 = new Activity("None for today", 0, 0, 0);
        }
        else if (physical > 2 && physical <= 4){
            recAc1 = activities.get(0);
        }
        else if (physical > 4 && physical <= 6){
            recAc1 = activities.get((int) Math.floor(Math.random() * 2));
        }
        else if (physical > 6 && physical <= 8){
            recAc1 = activities.get((int) Math.floor(Math.random() * 3));
        }
        else if (physical == 9){
            recAc1 = activities.get((int) Math.floor(Math.random() * (4 - 2) + 2));
        }
        else{
            recAc1 = activities.get((int) Math.floor(Math.random() * (5 - 3) + 3));
        }

        //mental
        int mentalQuotient = mental + social;
        if(mentalQuotient <= 2){
            recAc2 = new Activity("Take a breather", 0, 0, 0);
        }
        else if (mentalQuotient > 2 && mentalQuotient <= 6){
            recAc2 = activities.get(5);
        }
        else if (mentalQuotient > 6 && mentalQuotient <= 10){
            recAc2 = activities.get(6);
        }
        else if (mentalQuotient > 10 && mentalQuotient <= 14){
            recAc2 = activities.get((int) Math.floor(Math.random() * (8 - 5) + 5));
        }
        else if (mentalQuotient > 14 && mentalQuotient <= 18){
            recAc2 = activities.get((int) Math.floor(Math.random() * (9 - 5) + 5));
        }
        else{
            recAc2 = activities.get((int) Math.floor(Math.random() * (10 - 6) + 6));
        }

        recAc3 = activities.get((int) Math.floor(Math.random() * 10));
    }
    private String getFile() {
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
                    if(username1.equals(username) && password1.equals(password)){
                        return pathname;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }
        return null;
    }

    private String newPathname(String pathname){
        pathname = pathname.substring(0, pathname.length() - 7 - recentActivity.length() - 3);
        int index = 0;
        for(int i = pathname.length() - 1; i >= 0; i--){
            if(pathname.charAt(i) == '-'){
                index = i;
                break;
            }
        }
        pathname = pathname.substring(0, index + 1);
        return pathname + points + "---" + recentActivity + "---.txt";
    }

    private String updateActivity(String pathname, String oldActivity){
        pathname = pathname.substring(0, pathname.length() - oldActivity.length() - 7);
        return pathname + recentActivity + "---.txt";
    }
}

class Activity{
    String name;
    int type; //0 = physical, 1 = mental
    int points;
    int duration; //(in minutes)
    public Activity(String name, int type, int points, int duration){
        this.name = name;
        this.type = type;
        this.points = points;
        this.duration = duration;
    }
}

//class ActivityRecommender {
//    private double[] coefficients;
//    OLSMultipleLinearRegression model = new OLSMultipleLinearRegression();
//    public void train(int[][] data, int[] target) {
//        int n = data.length;
//        int m = data[0].length;
//
//        double[][] x = new double[n][m];
//        double[] y = new double[n];
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                x[i][j] = data[i][j];
//            }
//            y[i] = target[i];
//        }
//
//        double x_sum, y_sum, xx_sum, xy_sum;
//        x_sum = y_sum = xx_sum = xy_sum = 0;
//
//        for (int i = 0; i < n; i++) {
//            x_sum += x[i][0];
//            y_sum += y[i];
//            xx_sum += x[i][0] * x[i][0];
//            xy_sum += x[i][0] * y[i];
//        }
//
//        coefficients = new double[m];
//        coefficients[0] = (xy_sum - x_sum * y_sum / n) / (xx_sum - x_sum * x_sum / n);
//        coefficients[1] = y_sum / n - coefficients[0] * x_sum / n;
//    }
//
//    public double predict(int[] input) {
//        return coefficients[0] * input[0] + coefficients[1];
//    }
//
//    public int[] predict(int heartRate, int mentalWellness, int physicalWellness) {
//        double[] inputData = new double[] {heartRate, mentalWellness, physicalWellness};
//        double prediction = model.predict(inputData);
//        int[] predictedResponses = new int[actList.length];
//        for (int i = 0; i < actList.length; i++) {
//            predictedResponses[i] = (int) Math.round(prediction + actList[i].getResponse());
//        }
//        Arrays.sort(predictedResponses);
//        return predictedResponses;
//    }
//
//    public Activities[] getSortedActivities(Activities[] actList) {
//        int n = actList.length;
//        ArrayList<Activities> sortedActs = new ArrayList<>();
//
//        for (Activities act : actList) {
//            int heartRate = act.getHeartRate();
//            int mentalWellness = act.getMentalWellness();
//            int physicalWellness = act.getPhysicalWellness();
//            int[] input = {heartRate, mentalWellness, physicalWellness};
//            act.setResponseScore(predict(input));
//            sortedActs.add(act);
//        }
//
//        sortedActs.sort((a, b) -> Double.compare(b.getResponseScore(), a.getResponseScore()));
//        return sortedActs.toArray(new Activities[0]);
//    }
//}
