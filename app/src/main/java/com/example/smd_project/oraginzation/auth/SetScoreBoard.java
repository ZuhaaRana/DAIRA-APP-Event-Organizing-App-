package com.example.smd_project.oraginzation.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smd_project.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SetScoreBoard extends AppCompatActivity
{
    EditText EventName;
    EditText Score;
    String name;
    String score;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference reference = db.getReference().child("Score");
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_score_board);
        EventName = findViewById(R.id.eventName);
        Score = findViewById(R.id.score);
    }

    public void Set_Score(View view)
    {
        name = EventName.getText().toString();
        score = Score.getText().toString();

        if (TextUtils.isEmpty(name))
        {
            EventName.setError("Event name cannot be empty");
            EventName.requestFocus();
        }
        else if (TextUtils.isEmpty(score))
        {
            Score.setError("Score cannot be empty");
            Score.requestFocus();
        }
        else
        {
            // reference.child("01").setValue(Name);
            HashMap<String, String> Venues = new HashMap<>();

            Venues.put("EventName", name);
            Venues.put("Score", score);


            reference.push().setValue(Venues);
            Toast.makeText(this, "Score Added Successfully",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void BACK(View view)
    {
        startActivity(new Intent(SetScoreBoard.this, OrganizationHome.class));
    }
}