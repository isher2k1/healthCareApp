package com.isher2k1.myapplication.secondaryactivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.isher2k1.myapplication.HomeActivity;
import com.isher2k1.myapplication.R;
import com.isher2k1.myapplication.homeactivities.ProfileActivity;

public class UserInfoActivity extends AppCompatActivity {

    ConstraintLayout btn_save;
    ConstraintLayout oval_back;
    EditText editFirstName, editLastName, editEmail, editCity, editAddress;
    private String USER_KEY = "User";
    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        btn_save = findViewById(R.id.save_user_info);

        editFirstName = findViewById(R.id.editName);
        editLastName = findViewById(R.id.editLastName);
        editEmail = findViewById(R.id.editEmail);
        editCity = findViewById(R.id.editCity);
        editAddress = findViewById(R.id.editAdress);

        //editEmail.setText(userLog.getEmail());
        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);

        FirebaseUser user = mAuth.getCurrentUser();

        editEmail.setText(user.getEmail());

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = editFirstName.getText().toString();
                String lastName = editLastName.getText().toString();
                String email = editEmail.getText().toString();
                String city = editCity.getText().toString();
                String address = editAddress.getText().toString();

                if(firstName.length() == 0 || lastName.length() == 0 || email.length() == 0 || city.length() == 0 || address.length() == 0){
                    Toast.makeText(getApplicationContext(), "Please, fill all data", Toast.LENGTH_SHORT).show();
                } else{

                    //userInfo = new UserInfo(firstName, lastName, address, city);
                    //mDataBase.push().setValue(userInfo);
                    Toast.makeText(getApplicationContext(), "User`s info saved", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UserInfoActivity.this, ProfileActivity.class));
                    //System.out.println(userInfo.getFirstName() + " " + userInfo.getLastName());
                }
            }
        });

        oval_back = findViewById(R.id.oval_back);
        oval_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserInfoActivity.this, ProfileActivity.class));
            }
        });
    }
}