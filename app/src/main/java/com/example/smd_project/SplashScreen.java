package com.example.smd_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.smd_project.Participant.LoginActivity;
import com.example.smd_project.Participant.ParticipantHome;
import com.example.smd_project.oraginzation.auth.OrganizationHome;

public class SplashScreen extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    public void Go_to_home_participant(View view)
    {
        startActivity(new Intent(SplashScreen.this, LoginActivity.class));
    }

    public void Go_to_home_organizing_committee(View view)
    {
        startActivity(new Intent(SplashScreen.this, OrganizationHome.class));
    }
}