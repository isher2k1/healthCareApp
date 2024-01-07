package com.isher2k1.myapplication.homeactivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.isher2k1.myapplication.HomeActivity;
import com.isher2k1.myapplication.R;
import com.isher2k1.myapplication.classes.CustomAdapter;
import com.isher2k1.myapplication.classes.Medicine;

import java.util.ArrayList;
import java.util.Map;

public class MedicineActivity extends AppCompatActivity {

    private String MEDICINE_KEY = "Medicine";
    public DatabaseReference mDataBase;

    ArrayList<String> medicineTitles = new ArrayList<String>();
    ArrayList<String> medicineCosts = new ArrayList<String>();
    ArrayList<String> medicineDesc = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_list);

        init();

        ConstraintLayout oval_back = findViewById(R.id.oval_back);
        oval_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MedicineActivity.this, HomeActivity.class));
            }
        });

        ConstraintLayout btnToOrders = findViewById(R.id.layout_btn_orders);
        btnToOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MedicineActivity.this, OrdersActivity.class));
            }
        });
    }

    public void init(){
        Medicine medicine = new Medicine();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference(MEDICINE_KEY);

        ListView listView = (ListView)findViewById(R.id.listviewMedicine);

        for (int i=1; i<=8; i++){
            mDataBase.child("id"+i).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        System.out.println("Error getting data");
                    }
                    else {
                        Map<String, Object> medicineInfo = (Map<String, Object>) task.getResult().getValue();
                        String title = medicineInfo.get("title").toString();
                        String cost = medicineInfo.get("cost").toString();
                        String description = medicineInfo.get("description").toString();

                        medicineTitles.add(title);
                        medicineCosts.add(cost);
                        medicineDesc.add(description);

                        if(medicineTitles.size() == 8){
                            CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), medicineTitles, medicineCosts, medicineDesc);
                            listView.setAdapter((ListAdapter) customAdapter);
                        }
                    }
                }
            });
        }
    }
}