package com.example.smd_project.Participant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Telephony;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smd_project.R;
import com.example.smd_project.SplashScreen;
import com.example.smd_project.mentors.RegisterMentor;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity
{
    EditText LoginUsername;
    EditText LoginPassword;
    Button login;
    SharedPreferences sharedPreferences;
    String Username, Password, fetchPassword;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_participant);
        LoginUsername = findViewById(R.id.etLoginUsername);
        LoginPassword = findViewById(R.id.etLoginPass);
        login = findViewById(R.id.btnLogin);
    }


    public void Participant_Login(View view)
    {
        Username = LoginUsername.getText().toString();
        Password = LoginPassword.getText().toString();
        if (TextUtils.isEmpty(Username))
        {
            LoginUsername.setError("Username cannot be empty");
            LoginUsername.requestFocus();
        }
        else if (TextUtils.isEmpty(Password))
        {
            LoginPassword.setError("Password cannot be empty");
            LoginPassword.requestFocus();
        }
        else
        {
            database = FirebaseDatabase.getInstance("https://smd-project-4565d-default-rtdb.firebaseio.com/");
            reference = database.getReference("participant").child("student");
            reference.addListenerForSingleValueEvent(new ValueEventListener()
            {
                public void onDataChange(DataSnapshot snapshot)
                {
                    if (snapshot.hasChild(Username))
                    {
                        fetchPassword = snapshot.child(Username).child("password").getValue().toString();
                        if(Password.equals(fetchPassword))
                        {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.clear();
                            editor.putString("username",Username);
                            editor.commit();


                            Toast.makeText(LoginActivity.this,
                                    "Successfully Logged In",
                                    Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(LoginActivity.this,
                                    ParticipantHome.class));
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this,
                                    "Incorrect Password. Enter Again.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,
                                "UserName Doesnot Exist",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error)
                {
                    Toast.makeText(LoginActivity.this, "Error For Inserting", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void Back_to_MAIN(View view)
    {
        startActivity(new Intent(LoginActivity.this, SplashScreen.class));
    }

    public void Go_to_Sign_Up_Activity(View view)
    {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }
}