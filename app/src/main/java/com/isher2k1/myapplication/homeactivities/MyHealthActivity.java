package com.isher2k1.myapplication.homeactivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.isher2k1.myapplication.HomeActivity;
import com.isher2k1.myapplication.R;
import com.isher2k1.myapplication.about.AboutBMIActivity;
import com.isher2k1.myapplication.about.AboutDailyCalActivity;
import com.isher2k1.myapplication.about.AboutTargetCalActivity;

import java.util.Map;

public class MyHealthActivity extends AppCompatActivity {

    TextView textBMI, textDailyCal, textTargetCal;

    private String USER_KEY = "User";
    private FirebaseAuth mAuth;
    public DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_health);

        mAuth = FirebaseAuth.getInstance();

        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);

        setCalculatedBMI();

        ConstraintLayout btnBack = findViewById(R.id.oval_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyHealthActivity.this, HomeActivity.class));
            }
        });

        ConstraintLayout bmi_lay = findViewById(R.id.bmi_layout);
        bmi_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyHealthActivity.this, AboutBMIActivity.class));
            }
        });

        ConstraintLayout daily_cal = findViewById(R.id.daily_cal_layout);
        daily_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyHealthActivity.this, AboutDailyCalActivity.class));
            }
        });

        ConstraintLayout target_cal = findViewById(R.id.target_cal_layout);
        target_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyHealthActivity.this, AboutTargetCalActivity.class));
            }
        });
    }

    public void setCalculatedBMI(){
        FirebaseUser user = mAuth.getCurrentUser();
        mDataBase.child(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    System.out.println("Error getting data");
                }
                else {
                    Map<String, Object> healthInfo = (Map<String, Object>) task.getResult().getValue();
                    double height = Double.parseDouble(healthInfo.get("height").toString());
                    double weight = Double.parseDouble(healthInfo.get("weight").toString());
                    String activity = healthInfo.get("activity").toString();
                    int age = Integer.parseInt(healthInfo.get("age").toString());
                    String gender = healthInfo.get("gender").toString();

                    textBMI = findViewById(R.id.textBMI);
                    textBMI.setText(String.format("%.1f", weight / Math.pow(height/100, 2)));

                    double bmr = calculateBMR(height, weight, age, gender);
                    textDailyCal = findViewById(R.id.textDailyCal);
                    textDailyCal.setText(String.format("%.0f", bmr));

                    textTargetCal = findViewById(R.id.textTargetCal);
                    textTargetCal.setText(String.format("%.0f", calculateTDEE(activity, bmr)));

                }
            }
        });
    }

    public double calculateBMR(double height, double weight, int age, String gender){
        return (isMale(gender)) ? (88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age))
                : 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
    }

    public double calculateTDEE(String activity, double bmr){
        double tdee;
        switch (activity){
            case "Basal Metabolic Rate":
                tdee = bmr;
                break;
            case "Sedentary":
                tdee = bmr * 1.2;
                break;
            case "Lightly active":
                tdee = bmr * 1.375;
                break;
            case "Moderately active":
                tdee = bmr * 1.55;
                break;
            case "Active":
                tdee = bmr * 1.725;
                break;
            case "Very active":
                tdee = bmr * 1.9;
                break;
            default:
                tdee = 0;
        }
        return tdee;
    }

    public boolean isMale(String gender){
        return gender.equals("Male");
    }
}