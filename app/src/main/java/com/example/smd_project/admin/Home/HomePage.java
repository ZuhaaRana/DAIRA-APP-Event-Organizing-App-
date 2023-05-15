package com.example.smd_project.admin.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.smd_project.R;
import com.example.smd_project.admin.authentication.LoginActivity;
import com.example.smd_project.admin.category.CategoryRecycler.AllCategories;
import com.example.smd_project.admin.category.addCategory.Category;
import com.example.smd_project.admin.category.assignEvents.AssignEventsMain;
import com.example.smd_project.mentors.RegisterMentor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomePage extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;
    Button btnLogOut;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            Toast.makeText(this, "" + user.getUid(), Toast.LENGTH_SHORT).show();
        }
        database = FirebaseDatabase.getInstance("https://smd-project-4565d-default-rtdb.firebaseio.com/");
        reference = database.getReference("admin");
        reference.child("FASTadasd").child("FAST CFD").child("BSCS").setValue("100000000");
    }
    public void addMentorRegister(View view) {
        startActivity(new Intent(this, RegisterMentor.class));
    }

    public void addCategory(View view) {
        startActivity(new Intent(this, Category.class));
    }

    public void showCategories(View view) {
        Toast.makeText(HomePage.this, "Log in Error: " + "Calling", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, AllCategories.class));
    }

    public void assign_event(View view) {
        startActivity(new Intent(this, AssignEventsMain.class));
    }
}