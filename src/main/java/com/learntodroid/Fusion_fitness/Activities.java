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
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

import java.util.Arrays;


import java.io.File;
import java.util.ArrayList;

public class Activities extends AppCompatActivity {
    Button statsButton;
    Button leaderboardButton;
    Button finishButton;
    Button profileButton;
    String username;

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

        stressSpinner = findViewById(R.id.stressSpinner);

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

        finishButton = findViewById(R.id.activityFinish);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                points += 10;
                File file = new File(getFile());
                File newFile = new File(newPathname(file.getPath()));
                boolean result = file.renameTo(newFile);
            }
        });

        recentText = findViewById(R.id.recentActivity);
        recentText.setText(recentActivity);

        activity1 = findViewById(R.id.activity1);
        activity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity1.setVisibility(view.GONE);
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
        Intent intent = new Intent(this, Stats.class);
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
        activities.add(new Activity("Meditate", 1, 100, 5));
        activities.add(new Activity("Meditate", 1, 200, 10));
        activities.add(new Activity("Meditate", 1, 300, 15));
        activities.add(new Activity("Yoga", 1, 600, 15));
        activities.add(new Activity("Yoga", 1, 800, 30));
    }

    private void recommendActivities(int physical, int mental, int social){
        //physical
        if(physical < 2){
            recAc1 = new Activity("None for today!", 0, 0, 0);
        }
        if (physical < 3){
            recAc1 = activities.get(0);
        }
        else if (physical < 5){
            recAc1 = activities.get((int) Math.floor(Math.random() * 2));
        }
        else if (physical < 7){
            recAc1 = activities.get((int) Math.floor(Math.random() * 3));
        }
        else if (physical < 9){
            recAc1 = activities.get((int) Math.floor(Math.random() * (4 - 2) + 2));
        }
        else{
            recAc1 = activities.get((int) Math.floor(Math.random() * (5 - 3) + 3));
        }

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
