package com.isher2k1.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.isher2k1.myapplication.homeactivities.MedicineActivity;
import com.isher2k1.myapplication.homeactivities.MyHealthActivity;
import com.isher2k1.myapplication.homeactivities.OrdersActivity;
import com.isher2k1.myapplication.homeactivities.ProfileActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Logout Card
        CardView logout = findViewById(R.id.cardLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                FirebaseAuth.getInstance().signOut();
            }
        });

        //Profile Card
        CardView profile = findViewById(R.id.cardProfile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
            }
        });

        //Buy Medicine Card
        CardView buyMedicine = findViewById(R.id.cardBuyMedicine);
        buyMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, MedicineActivity.class));
            }
        });

        //Orders Card
        CardView orders = findViewById(R.id.cardOrders);
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, OrdersActivity.class));
            }
        });

        //Health Card
        CardView health = findViewById(R.id.cardHealth);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, MyHealthActivity.class));
            }
        });
    }
}