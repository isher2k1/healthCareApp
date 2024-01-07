package com.isher2k1.myapplication.about;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.isher2k1.myapplication.R;
import com.isher2k1.myapplication.homeactivities.MyHealthActivity;

public class AboutDailyCalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_daily_cal);

        ConstraintLayout btnBack = findViewById(R.id.oval_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AboutDailyCalActivity.this, MyHealthActivity.class));
            }
        });
    }
}