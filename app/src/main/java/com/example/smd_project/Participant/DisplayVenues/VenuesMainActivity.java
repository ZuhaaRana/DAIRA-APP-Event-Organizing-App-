package com.example.smd_project.Participant.DisplayVenues;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.smd_project.Participant.DisplayNews.EventNewsActivity;
import com.example.smd_project.Participant.ParticipantHome;
import com.example.smd_project.R;
import com.example.smd_project.oraginzation.auth.ParticipantsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VenuesMainActivity extends AppCompatActivity {

    RecyclerView rv;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference reference = db.getReference().child("Venues");

    private VenuesAdapter adapter;
    private ArrayList<VenuesModel> venue_list;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venues_main);

        rv = findViewById(R.id.VenuesRecyclerView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        venue_list = new ArrayList<>();
        adapter = new VenuesAdapter(this, venue_list);
        rv.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    VenuesModel model = dataSnapshot.getValue(VenuesModel.class);
                    venue_list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void Back(View view)
    {
        startActivity(new Intent(VenuesMainActivity.this, ParticipantHome.class));
    }
}