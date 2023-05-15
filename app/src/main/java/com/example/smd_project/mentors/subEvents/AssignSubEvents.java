package com.example.smd_project.mentors.subEvents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.smd_project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AssignSubEvents extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_sub_events);
        database = FirebaseDatabase.getInstance("https://smd-project-289fc-default-rtdb.firebaseio.com/");
        reference = database.getReference("admin").child("categories");
        LoadDataFromFirebase();
    }
    private void LoadDataFromFirebase() {

        Log.d("TAG",reference.toString());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("TAG",dataSnapshot.toString());
//                Toast.makeText(AssignSubEvents.this, dataSnapshot.getKey(), Toast.LENGTH_LONG).show();
//                Toast.makeText(AssignSubEvents.this, "Running", Toast.LENGTH_SHORT).show();
////                clear();
//                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
////                    list.add(snapshot.getKey());
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    }
