package com.example.smd_project.oraginzation.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smd_project.R;
import com.example.smd_project.admin.authentication.RegisterActivity;
import com.example.smd_project.mentors.RegisterMentor;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrganizationLoginActivity extends AppCompatActivity {
    TextInputEditText etLoginUsername;
    TextInputEditText etLoginPassword;
    SharedPreferences sharedPreferences;
    TextView tvRegisterHere;
    Button btnLogin;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_organization);
        sharedPreferences = getSharedPreferences("MentorFile",0);
        etLoginUsername = findViewById(R.id.etLoginUsername);
        etLoginPassword = findViewById(R.id.etLoginPass);
        tvRegisterHere = findViewById(R.id.tvRegisterHere);
        btnLogin = findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> {
            loginUser();
        });
        tvRegisterHere.setOnClickListener(view ->{
            startActivity(new Intent(OrganizationLoginActivity.this, RegisterActivity.class));
        });
    }

    private void loginUser(){
        String username = etLoginUsername.getText().toString();
        String password = etLoginPassword.getText().toString();

        if (TextUtils.isEmpty(username)){
            etLoginUsername.setError("Username cannot be empty");
            etLoginUsername.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etLoginPassword.setError("Password cannot be empty");
            etLoginPassword.requestFocus();
        }else{
            mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null){
                Toast.makeText(this, "" + user.getUid(), Toast.LENGTH_SHORT).show();
            }
            database = FirebaseDatabase.getInstance("https://smd-project-289fc-default-rtdb.firebaseio.com/");
            reference = database.getReference("organization").child("auth");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.hasChild(username)) {
                        String fetchPassword = snapshot.child(username).child("password").getValue().toString();
                        if(password.equals(fetchPassword)){
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.clear();
                            editor.putString("username",username);
                            editor.commit();
                            Toast.makeText(OrganizationLoginActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(OrganizationLoginActivity.this, RegisterMentor.class));
                        }else{
                            Toast.makeText(OrganizationLoginActivity.this, "Password Incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(OrganizationLoginActivity.this, "UserName Not Exist", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(OrganizationLoginActivity.this, "Error For Inserting", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}