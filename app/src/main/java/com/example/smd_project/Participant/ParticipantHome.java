package com.example.smd_project.Participant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.smd_project.Participant.DisplayNews.EventNewsActivity;
import com.example.smd_project.Participant.DisplayScore.ScoreboardActivity;
import com.example.smd_project.Participant.DisplayVenues.VenuesMainActivity;
import com.example.smd_project.R;

public class ParticipantHome extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_participant);
    }

    public void Participant_SignUp(View view)
    {
        startActivity(new Intent(ParticipantHome.this,SignUpActivity.class));
    }

    public void View_Scoreboard(View view)
    {
        startActivity(new Intent(ParticipantHome.this, ScoreboardActivity.class));
    }

    public void All_Venues(View view)
    {
        startActivity(new Intent(ParticipantHome.this, VenuesMainActivity.class));
    }

    public void Log_out(View view)
    {
        startActivity(new Intent(ParticipantHome.this, LoginActivity.class));
    }

    public void Display_Event_News(View view)
    {
        startActivity(new Intent(ParticipantHome.this, EventNewsActivity.class));
    }
}