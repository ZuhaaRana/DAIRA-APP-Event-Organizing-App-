package com.example.smd_project.admin.category.addCategory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smd_project.R;
import com.example.smd_project.admin.category.CategoryRecycler.CategoryGetterSetter;
import com.example.smd_project.mentors.Mentor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Category extends AppCompatActivity {
    EditText etRegCategory;
    EditText etRegPassword;
    EditText etRegName;
    FirebaseDatabase database;
    DatabaseReference reference;
    Button addBtnCategory;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        etRegCategory = (EditText) findViewById(R.id.etRegCategory);
        addBtnCategory = findViewById(R.id.addBtnCategory);
        database = FirebaseDatabase.getInstance("https://smd-project-4565d-default-rtdb.firebaseio.com/");
        reference = database.getReference("admin");
        Log.d("TAG",reference.toString());
        addBtnCategory.setOnClickListener(view -> {
            addMentor();
        });
    }
    public void addMentor() {
        String category = etRegCategory.getText().toString();
        if (TextUtils.isEmpty(category)){
            etRegCategory.setError("Category cannot be empty");
            etRegCategory.requestFocus();
        }else{
            mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null){
                Toast.makeText(this, "" + user.getUid(), Toast.LENGTH_SHORT).show();
            }
            database = FirebaseDatabase.getInstance("https://smd-project-4565d-default-rtdb.firebaseio.com/");
            reference = database.getReference("admin").child("categories");
            Boolean value = false;
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (!snapshot.hasChild(category)) {
                        reference.child(category).push().setValue(0);
                    }else{
                        Toast.makeText(Category.this, "Already Exist", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Category.this, "Error For Inserting", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}