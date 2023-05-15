package com.example.smd_project.oraginzation.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.smd_project.R;
import com.example.smd_project.SplashScreen;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RegisteredParticipants extends AppCompatActivity
{
    RecyclerView recyclerView;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference reference = db.getReference().child("Participants");

    private ParticipantsAdapter adapter;
    private ArrayList<ParticipantsModel> participant_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_participants);

        recyclerView = findViewById(R.id.ParticipantsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        participant_list = new ArrayList<>();
        adapter = new ParticipantsAdapter(this, participant_list);
        recyclerView.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {

                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    ParticipantsModel model = dataSnapshot.getValue(ParticipantsModel.class);
                    participant_list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void BACK(View view)
    {
        startActivity(new Intent(RegisteredParticipants.this, OrganizationHome.class));
    }
}