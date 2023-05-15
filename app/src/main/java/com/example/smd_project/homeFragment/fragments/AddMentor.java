package com.example.smd_project.homeFragment.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smd_project.R;


public class AddMentor extends Fragment {
    Button addMentorButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.mentor_home_activity,container,false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        addMentorButton = getActivity().findViewById(R.id.addMentorButton);
        addMentorButton.setOnClickListener(view ->{
            mentoradd();
        });
    }
    public void mentoradd(){
        Log.d("TAG","ADD Mentor");
    }
}
