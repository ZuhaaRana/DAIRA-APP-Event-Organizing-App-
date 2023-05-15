package com.example.smd_project.homeFragment.fragments;

import android.content.Intent;
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
import com.example.smd_project.admin.category.addCategory.Category;

public class AddCategory extends Fragment {
    Button addCategoryButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.category_home_activity,container,false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        addCategoryButton = getActivity().findViewById(R.id.addCategoryButton);
        addCategoryButton.setOnClickListener(view ->{
            categoryAdd();
        });
    }
    public void categoryAdd(){
        startActivity(new Intent(getActivity(), Category.class));
        Log.d("TAG","ADD Category");
    }
}
