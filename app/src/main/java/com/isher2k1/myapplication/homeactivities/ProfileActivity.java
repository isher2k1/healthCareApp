package com.isher2k1.myapplication.homeactivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
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
import com.isher2k1.myapplication.secondaryactivities.ParametersActivity;
import com.isher2k1.myapplication.R;
import com.isher2k1.myapplication.secondaryactivities.UserInfoActivity;

import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    TextView firstLastName, email;
    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;
    private String USER_KEY = "User";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firstLastName = findViewById(R.id.textView8);
        email = findViewById(R.id.textView9);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);

        email.setText(user.getEmail());
        init();

        ConstraintLayout oval_back = findViewById(R.id.oval_back);
        oval_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
            }
        });

        ConstraintLayout param = findViewById(R.id.parameters);
        param.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, ParametersActivity.class));
            }
        });

        ConstraintLayout userInfo = findViewById(R.id.userInfo);
        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, UserInfoActivity.class));
            }
        });
    }

    public void init(){
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mDataBase.child(currentUser.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    System.out.println("Error getting data");
                }
                else {
                    Map<String, Object> personalinfo = (Map<String, Object>) task.getResult().getValue();
                    String firstName = personalinfo.get("firstName").toString();
                    String lastName = personalinfo.get("lastName").toString();
                    firstLastName.setText(firstName + " " + lastName);
                }
            }
        });
    }
}