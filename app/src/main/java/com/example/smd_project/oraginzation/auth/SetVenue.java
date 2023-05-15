package com.example.smd_project.oraginzation.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smd_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SetVenue extends AppCompatActivity {

    EditText EventName;
    EditText Venue;
    String name;
    String venue;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference reference = db.getReference().child("Venues");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_venue);
        EventName = findViewById(R.id.eventName);
        Venue = findViewById(R.id.venue);
    }

    public void Set_Venue(View view)
    {
        name = EventName.getText().toString();
        venue = Venue.getText().toString();

        if(TextUtils.isEmpty(name))
        {
            EventName.setError("Event name cannot be empty");
            EventName.requestFocus();
        }

        else if (TextUtils.isEmpty(venue))
        {
            Venue.setError("Venue cannot be empty");
            Venue.requestFocus();
        }
        else
        {
            // reference.child("01").setValue(Name);
            HashMap<String,String> Venues = new HashMap<>();

            Venues.put("Name",name);
            Venues.put("Venue",venue);


            reference.push().setValue(Venues).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(SetVenue.this,
                            "Venue has been set successfully.",
                            Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    public void BACK(View view)
    {
        startActivity(new Intent(SetVenue.this, OrganizationHome.class));
    }
}