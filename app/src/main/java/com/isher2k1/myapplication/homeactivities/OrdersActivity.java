package com.isher2k1.myapplication.homeactivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.isher2k1.myapplication.HomeActivity;
import com.isher2k1.myapplication.R;

public class OrdersActivity extends AppCompatActivity {

    private String USER_KEY = "User";
    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        ConstraintLayout oval_back = findViewById(R.id.oval_back);
        oval_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrdersActivity.this, HomeActivity.class));
            }
        });


    }
}