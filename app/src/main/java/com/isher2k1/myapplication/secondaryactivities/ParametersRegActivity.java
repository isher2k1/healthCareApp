package com.isher2k1.myapplication.secondaryactivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.isher2k1.myapplication.R;

import java.util.HashMap;
import java.util.Map;

public class ParametersRegActivity extends AppCompatActivity {

    EditText editAge, editHeight, editWeight;
    String selectedActivity, selectedGender, selectedGoal;

    ConstraintLayout layout_btn;

    private String USER_KEY = "User";
    private FirebaseAuth mAuth;
    public DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters_reg);

        editAge = findViewById(R.id.editTextAge);
        editHeight = findViewById(R.id.editTextHeight);
        editWeight = findViewById(R.id.editTextWeight);
        layout_btn = findViewById(R.id.layout_btn);

        mAuth = FirebaseAuth.getInstance();

        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);

        Spinner spinnerGoals = (Spinner) findViewById(R.id.spinner);
        Spinner spinnerGender = (Spinner) findViewById(R.id.spinnerGender);
        Spinner spinnerActivities = (Spinner) findViewById(R.id.spinnerActivities);
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapterGoals = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_items,
                android.R.layout.simple_spinner_item
        );
        ArrayAdapter<CharSequence> adapterGender = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_gender,
                android.R.layout.simple_spinner_item
        );
        ArrayAdapter<CharSequence> adapterActivities = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_activities,
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapterGoals.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterGender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterActivities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinnerGoals.setAdapter(adapterGoals);
        spinnerGender.setAdapter(adapterGender);
        spinnerActivities.setAdapter(adapterActivities);

        spinnerGoals.getViewTreeObserver().addOnGlobalLayoutListener(() -> ((TextView) spinnerGoals.getSelectedView()).setTextColor(Color.WHITE));
        spinnerGoals.getViewTreeObserver().addOnGlobalLayoutListener(() -> ((TextView) spinnerGoals.getSelectedView()).setTextSize(20));
        spinnerGender.getViewTreeObserver().addOnGlobalLayoutListener(() -> ((TextView) spinnerGender.getSelectedView()).setTextColor(Color.WHITE));
        spinnerGender.getViewTreeObserver().addOnGlobalLayoutListener(() -> ((TextView) spinnerGender.getSelectedView()).setTextSize(20));
        spinnerActivities.getViewTreeObserver().addOnGlobalLayoutListener(() -> ((TextView) spinnerActivities.getSelectedView()).setTextColor(Color.WHITE));
        spinnerActivities.getViewTreeObserver().addOnGlobalLayoutListener(() -> ((TextView) spinnerActivities.getSelectedView()).setTextSize(20));

        layout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedActivity = spinnerActivities.getSelectedItem().toString();
                selectedGoal = spinnerGoals.getSelectedItem().toString();
                selectedGender = spinnerGender.getSelectedItem().toString();

                double height = Double.parseDouble(editHeight.getText().toString());
                double weight = Double.parseDouble(editWeight.getText().toString());
                int age = Integer.parseInt(editAge.getText().toString());

                if(editAge.length() == 0 || editWeight.length() == 0 || editHeight.length() == 0){
                    Toast.makeText(getApplicationContext(), "Please, fill all data", Toast.LENGTH_SHORT).show();
                }else{
                    if(!isInteger(editAge.getText().toString()) || !isDouble(editHeight.getText().toString())
                            || !isDouble(editWeight.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Use numeric format to fill the data",
                                Toast.LENGTH_SHORT).show();
                    }else {
                        updateParams(selectedGender, selectedActivity, selectedGoal, height, weight, age);
                        Toast.makeText(getApplicationContext(), "Parameters saved", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ParametersRegActivity.this, UserInfoRegActivity.class));
                    }
                }
            }
        });
    }

    private void updateParams(String gender, String activity, String goal,
                              double height, double weight, int age) {
        FirebaseUser user = mAuth.getCurrentUser();
        Map<String, Object> userValues = new HashMap<>();
        userValues.put("gender", gender);
        userValues.put("activity", activity);
        userValues.put("goal", goal);
        userValues.put("height", height);
        userValues.put("weight", weight);
        userValues.put("age", age);

        mDataBase.child(user.getUid()).updateChildren(userValues);
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}