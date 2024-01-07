package com.isher2k1.myapplication.about;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.isher2k1.myapplication.HomeActivity;
import com.isher2k1.myapplication.R;
import com.isher2k1.myapplication.homeactivities.MyHealthActivity;

public class AboutBMIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_bmiactivity);

        TextView link1 = findViewById(R.id.textLinkHealth1);
        link1.setMovementMethod(LinkMovementMethod.getInstance());

        TextView link2 = findViewById(R.id.textLinkHealth2);
        link2.setMovementMethod(LinkMovementMethod.getInstance());

        TextView link3 = findViewById(R.id.textLinkHealth3);
        link3.setMovementMethod(LinkMovementMethod.getInstance());

        TextView link4 = findViewById(R.id.textLinkHealth4);
        link4.setMovementMethod(LinkMovementMethod.getInstance());

        ConstraintLayout btnBack = findViewById(R.id.oval_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AboutBMIActivity.this, MyHealthActivity.class));
            }
        });
    }
}