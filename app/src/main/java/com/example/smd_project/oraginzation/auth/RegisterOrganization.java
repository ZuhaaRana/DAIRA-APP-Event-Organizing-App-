package com.example.smd_project.oraginzation.auth;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smd_project.R;
import com.example.smd_project.mentors.Mentor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterOrganization extends AppCompatActivity {
    EditText etRegUser;
    EditText etRegPassword;
    EditText etRegName;
    FirebaseDatabase database;
    DatabaseReference reference;
    Button btnRegister;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_organization);
        etRegUser = (EditText) findViewById(R.id.etRegUser);
        etRegPassword =(EditText) findViewById(R.id.etRegPass);
        etRegName = (EditText)findViewById(R.id.etRegName);
        btnRegister = findViewById(R.id.btnRegister);
        database = FirebaseDatabase.getInstance("https://smd-project-4565d-default-rtdb.firebaseio.com/");
        reference = database.getReference("organization");
        btnRegister.setOnClickListener(view -> {
            addOrganization();
        });
    }

    public void addOrganization() {
        String name = etRegName.getText().toString();
        String username = etRegUser.getText().toString();
        String password = etRegPassword.getText().toString();
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        if (TextUtils.isEmpty(name)){
            etRegUser.setError("Name cannot be empty");
            etRegUser.requestFocus();
        }
        if (TextUtils.isEmpty(username)){
            etRegUser.setError("Email cannot be empty");
            etRegUser.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etRegPassword.setError("Password cannot be empty");
            etRegPassword.requestFocus();
        }else{
            mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null){
                Toast.makeText(this, "" + user.getUid(), Toast.LENGTH_SHORT).show();
            }
            database = FirebaseDatabase.getInstance("https://smd-project-289fc-default-rtdb.firebaseio.com/");
            reference = database.getReference("mentor").child("auth");

            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (!snapshot.hasChild(username)) {
                        database = FirebaseDatabase.getInstance("https://smd-project-289fc-default-rtdb.firebaseio.com/");
                        reference = database.getReference("organization").child("auth");
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                if (!snapshot.hasChild(username)) {
                                    reference.child(username).push();
                                    reference.child(username).child("name").setValue(name);
                                    reference.child(username).child("password").setValue(password);
                                    Toast.makeText(RegisterOrganization.this, "Mentor Registered Successfully", Toast.LENGTH_SHORT).show();
//                        reference.child(email).push().setValue(name);
                                }else{
                                    Toast.makeText(RegisterOrganization.this, "UserName Already Exist", Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(RegisterOrganization.this, "Error For Inserting", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else{
                        Toast.makeText(RegisterOrganization.this, "UserName Already Exist", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(RegisterOrganization.this, "Error For Inserting", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}