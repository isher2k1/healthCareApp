package com.isher2k1.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.isher2k1.myapplication.classes.User;
import com.isher2k1.myapplication.secondaryactivities.ParametersRegActivity;

public class RegistrationActivity extends AppCompatActivity {

    EditText editRgEmail, editRgPass, editRgPass2;
    TextView textSignIn;

    User newUser;
    ConstraintLayout btn_reg;
    private String test1 = "test";
    private double test2 = 0;
    private  int test3 = 0;
    private String USER_KEY = "User";
    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editRgEmail = findViewById(R.id.editTextRegistrationEmail);
        editRgPass = findViewById(R.id.editTextRegistrationPassword);
        editRgPass2 = findViewById(R.id.editTextRegistrationPassword2);
        textSignIn = findViewById(R.id.textViewSignIn);
        btn_reg = findViewById(R.id.layout_btn_reg);

        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);


        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = mDataBase.getKey();

                String email = editRgEmail.getText().toString();
                String password = editRgPass.getText().toString();
                String password2 = editRgPass2.getText().toString();

                if(email.length() == 0 || password.length() == 0 || password2.length() == 0){
                    Toast.makeText(getApplicationContext(), "Please, fill all data", Toast.LENGTH_SHORT).show();
                } else{
                    if(LoginActivity.patternMatches(email, LoginActivity.regexPattern)){
                        if (password.compareTo(password2) == 0){
                            if (isValid(password)){
                                mAuth.createUserWithEmailAndPassword(email, password).
                                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if(task.isSuccessful()){
                                                    FirebaseUser user = mAuth.getCurrentUser();
                                                    assert user != null;
                                                    newUser = new User(user.getUid(), email, password, test1, test1, test1, test1, test1, test1, test1, test2, test2, test3);
                                                    mDataBase.child(user.getUid()).setValue(newUser);

                                                    Toast.makeText(getApplicationContext(), "Registration Success", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(RegistrationActivity.this, ParametersRegActivity.class));
                                                }else {
                                                    Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }else{
                                Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters, 1 digit, 1 spec symbol", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else{
                        Toast.makeText(getApplicationContext(), "Email must be valid", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        textSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
    }

    public static boolean isValid(String pass){
        int f1=0, f2=0, f3=0;
        if (pass.length() < 8){
            return false;
        } else{
            for(int p = 0; p<pass.length(); p++){
                if(Character.isLetter(pass.charAt(p))){
                    f1=1;
                }
            }
            for(int r=0; r<pass.length(); r++){
                if(Character.isDigit(pass.charAt(r))){
                    f2=1;
                }
            }
            for(int s = 0; s<pass.length(); s++){
                char c = pass.charAt(s);
                if(c>=33 && c<=46 || c==64){
                    f3 = 1;
                }
            }
            if(f1==1 && f2==1 && f3==1){
                return true;
            }
            return false;
        }
    }

    
}