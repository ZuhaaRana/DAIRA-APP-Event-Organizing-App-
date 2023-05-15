package com.example.smd_project.Participant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smd_project.R;
import com.example.smd_project.mentors.RegisterMentor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity
{
    EditText name;
    EditText username;
    EditText password;
    EditText eventname;
    String Name, Username, Password, Event;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference reference = db.getReference().child("Participants");

    //reference = db.getReference("participant").child("student");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_sign_up);

        name = findViewById(R.id.etRegName);
        username = findViewById(R.id.etRegUser);
        password = findViewById(R.id.etRegPass);
        eventname = findViewById(R.id.etRegEvent);

//        database = FirebaseDatabase.getInstance("https://smd-project-4565d-default-rtdb.firebaseio.com/");
//        reference = database.getReference("participant").child("student");;
//        Log.d("TAG",reference.toString());
    }

    public void Sign_Up(View view)
    {

        Name = name.getText().toString();
        Username = username.getText().toString();
        Password = password.getText().toString();
        Event = eventname.getText().toString();

        if(TextUtils.isEmpty(Event))
        {
            username.setError("Event name cannot be empty");
            username.requestFocus();
        }

        if (TextUtils.isEmpty(Name))
        {
            name.setError("Name cannot be empty");
            name.requestFocus();
        }
        if (TextUtils.isEmpty(Username)){
            username.setError("Email cannot be empty");
            username.requestFocus();
        }

        else if (TextUtils.isEmpty(Password))
        {
            password.setError("Password cannot be empty");
            password.requestFocus();
        }
        else
        {
           // reference.child("01").setValue(Name);
            HashMap<String,String> Participants = new HashMap<>();

            Participants.put("Name",Name);
            Participants.put("Username",Username);
            Participants.put("Password",Password);
            Participants.put("Event",Event);

            reference.push().setValue(Participants);

            Toast.makeText(this, "Participant Registered Successfully",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void Back_to_home_participant(View view)
    {
        startActivity(new Intent(SignUpActivity.this, ParticipantHome.class));
    }
}