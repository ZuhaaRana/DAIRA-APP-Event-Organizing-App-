package com.example.smd_project.oraginzation.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class UpdateNews extends AppCompatActivity
{

    EditText EventName;
    EditText News;
    String name;
    String news;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference reference = db.getReference().child("Event_News");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_news);
        EventName = findViewById(R.id.update_event_name);
        News = findViewById(R.id.update_news);
    }

    public void Update_News(View view)
    {
        name = EventName.getText().toString();
        news = News.getText().toString();

        if(TextUtils.isEmpty(name))
        {
            EventName.setError("Event name cannot be empty");
            EventName.requestFocus();
        }

        else if (TextUtils.isEmpty(news))
        {
            News.setError("News cannot be empty");
            News.requestFocus();
        }
        else
        {
            // reference.child("01").setValue(Name);
            HashMap<String,String> NEWS = new HashMap<>();

            NEWS.put("Event_Name",name);
            NEWS.put("News",news);


            reference.push().setValue(NEWS).addOnCompleteListener(new OnCompleteListener<Void>()
            {
                @Override
                public void onComplete(@NonNull Task<Void> task)
                {
                    Toast.makeText(UpdateNews.this,
                            "News Updated.",
                            Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}