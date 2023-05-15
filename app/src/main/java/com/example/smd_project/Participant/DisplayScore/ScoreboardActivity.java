package com.example.smd_project.Participant.DisplayScore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.smd_project.Participant.ParticipantHome;
import com.example.smd_project.R;
import com.example.smd_project.oraginzation.auth.ParticipantsAdapter;
import com.example.smd_project.oraginzation.auth.ParticipantsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ScoreboardActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference reference = db.getReference().child("Score");

    private ScoreAdapter adapter;
    private ArrayList<ScoreModel> score_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        recyclerView = findViewById(R.id.ScoreRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        score_list = new ArrayList<>();
        adapter = new ScoreAdapter(this, score_list);
        recyclerView.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    ScoreModel model = dataSnapshot.getValue(ScoreModel.class);
                    score_list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void Back_to_home_participant(View view)
    {
        startActivity(new Intent(ScoreboardActivity.this, ParticipantHome.class));
    }
}