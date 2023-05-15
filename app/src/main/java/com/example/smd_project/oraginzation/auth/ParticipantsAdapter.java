package com.example.smd_project.oraginzation.auth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smd_project.R;

import java.util.ArrayList;

public class ParticipantsAdapter extends RecyclerView.Adapter<ParticipantsAdapter.MyViewHolder>
{
    ArrayList <ParticipantsModel> mList;
    Context context;
    View view;

    public ParticipantsAdapter (Context context, ArrayList <ParticipantsModel> mList)
    {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.participant_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        ParticipantsModel model = mList.get(position);
        holder.participant_name.setText(model.getName());
        holder.event_name.setText(model.getEvent());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView participant_name, event_name;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            participant_name = itemView.findViewById(R.id.particpant_name_text);
            event_name = itemView.findViewById(R.id.event_name_text);
        }
    }
}
