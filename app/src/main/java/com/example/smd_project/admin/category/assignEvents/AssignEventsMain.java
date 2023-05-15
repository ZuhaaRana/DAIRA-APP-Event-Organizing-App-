package com.example.smd_project.admin.category.assignEvents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.smd_project.R;
import com.example.smd_project.admin.category.CategoryRecycler.CategoryAdapter;
import com.example.smd_project.admin.category.addCategory.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AssignEventsMain extends AppCompatActivity {
    Spinner categorydrop, mentordrop;
    Button assignEventBtn;
    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayAdapter<String> adapter;
    ArrayList<String> categoryList;

    String selectedCategory = null;
    String selectedMentor = null;
    ArrayList<String> mentorList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_events_main);
        categorydrop =findViewById(R.id.category_drop);
        mentordrop =findViewById(R.id.mentor_drop);
        assignEventBtn = findViewById(R.id.assign_event_btn);
        categoryList = new ArrayList<>();
        mentorList = new ArrayList<>();
        database = FirebaseDatabase.getInstance("https://smd-project-4565d-default-rtdb.firebaseio.com/");
        reference = database.getReference("admin").child("categories");
        LoadCategoriesFromFirebase();
        database = FirebaseDatabase.getInstance("https://smd-project-4565d-default-rtdb.firebaseio.com/");
        reference = database.getReference("mentor").child("auth");
        LoadMentorFromFirebase();
        assignEventBtn.setOnClickListener(view -> {
            assignEvent();
        });
    }

    private void assignEvent() {
        database = FirebaseDatabase.getInstance("https://smd-project-4565d-default-rtdb.firebaseio.com/");
        reference = database.getReference("admin").child("categories");
        if(selectedCategory == null){
            Toast.makeText(this, "Kindly Select The Event", Toast.LENGTH_SHORT).show();
        }else if(selectedMentor == null){
            Toast.makeText(this, "Kindly Select The Mentor", Toast.LENGTH_SHORT).show();
        }else{
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.hasChild(selectedCategory)) {
                        reference.child(selectedCategory).child("mentor").setValue(selectedMentor);
                        Toast.makeText(AssignEventsMain.this, "Event Assigned", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AssignEventsMain.this, "Event Not Exist", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(AssignEventsMain.this, "Error For Inserting", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void LoadMentorFromFirebase() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clearMentors();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    mentorList.add(snapshot.getKey());
                }
                adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, mentorList);
                mentordrop.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                mentordrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        selectedMentor = mentorList.get(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void LoadCategoriesFromFirebase() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clearCategories();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    categoryList.add(snapshot.getKey());
                }
                adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, categoryList);
                categorydrop.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                categorydrop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        selectedCategory = categoryList.get(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void clearMentors() {
        if(mentorList != null){
            mentorList.clear();
            if(adapter != null){
                adapter.notifyDataSetChanged();
            }else{
                mentorList = new ArrayList();
            }
        }
    }
    private void clearCategories() {
        if(categoryList != null){
            categoryList.clear();
            if(adapter != null){
                adapter.notifyDataSetChanged();

            }else{
                categoryList = new ArrayList();
            }
        }
    }
}