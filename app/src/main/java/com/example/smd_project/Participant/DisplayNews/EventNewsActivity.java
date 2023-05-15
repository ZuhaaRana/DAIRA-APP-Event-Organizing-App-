package com.example.smd_project.Participant.DisplayNews;

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

public class EventNewsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EventNewsAdapter adapter;
    private ArrayList<EventNewsModel> event_news_list;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference().child("Event_News");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_news);

        recyclerView = findViewById(R.id.EventNewsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        event_news_list = new ArrayList<>();
        adapter = new EventNewsAdapter(this,event_news_list);
        recyclerView.setAdapter(adapter);

        ref.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    EventNewsModel model = dataSnapshot.getValue(EventNewsModel.class);
                    event_news_list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }

    public void Back_to_home_participant(View view)
    {
        startActivity(new Intent(EventNewsActivity.this, ParticipantHome.class));
    }
}