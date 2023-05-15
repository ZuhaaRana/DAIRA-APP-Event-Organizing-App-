package com.example.smd_project.mentors;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smd_project.MainActivity;
import com.example.smd_project.R;
import com.example.smd_project.SplashScreen;
import com.example.smd_project.admin.authentication.RegisterActivity;
import com.example.smd_project.oraginzation.auth.OrganizationHome;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity
{
    TextInputEditText etLoginUsername;
    TextInputEditText etLoginPassword;
    SharedPreferences sharedPreferences;
    TextView tvRegisterHere;
    Button btnLogin;
    FirebaseDatabase database;
    DatabaseReference reference;
    Button btnRegister;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mentor);
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
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }

    private void loginUser()
    {
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
            database = FirebaseDatabase.getInstance("https://smd-project-4565d-default-rtdb.firebaseio.com/");
            reference = database.getReference("mentor").child("auth");
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
                           Toast.makeText(LoginActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(LoginActivity.this,RegisterMentor.class));
                       }else{
                           Toast.makeText(LoginActivity.this, "Password Incorrect", Toast.LENGTH_SHORT).show();
                       }
                    }else{
                        Toast.makeText(LoginActivity.this, "UserName Not Exist", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(LoginActivity.this, "Error For Inserting", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    public void BACK(View view)
    {
        startActivity(new Intent(LoginActivity.this, SplashScreen.class));
    }

    public void Go_to_OrganizationHome_Activity(View view)
    {
        startActivity(new Intent(LoginActivity.this, OrganizationHome.class));
    }
}

