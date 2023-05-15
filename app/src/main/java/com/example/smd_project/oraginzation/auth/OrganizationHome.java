package com.example.smd_project.oraginzation.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.smd_project.R;
import com.example.smd_project.SplashScreen;

public class OrganizationHome extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_organization);
    }

    public void LOGOUT(View view)
    {
        startActivity(new Intent(OrganizationHome.this, SplashScreen.class));
    }

    public void Set_Venue(View view)
    {
        startActivity(new Intent(OrganizationHome.this, SetVenue.class));
    }

    public void Go_to_SetScoreBoard_Activity(View view)
    {
        startActivity(new Intent(OrganizationHome.this, SetScoreBoard.class));
    }

    public void Display_Registered_Participants(View view)
    {
        startActivity(new Intent(OrganizationHome.this, RegisteredParticipants.class));
    }

    public void Update_NEWS(View view)
    {
        startActivity(new Intent(OrganizationHome.this, UpdateNews.class));
    }
}