package com.example.smd_project.Participant.DisplayScore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smd_project.R;
import com.example.smd_project.oraginzation.auth.ParticipantsAdapter;
import com.example.smd_project.oraginzation.auth.ParticipantsModel;
import com.google.android.gms.common.internal.ConnectionTelemetryConfiguration;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.MyViewHolder>
{
    ArrayList<ScoreModel> sList;
    Context context;


    public ScoreAdapter(Context context, ArrayList<ScoreModel> sList)
    {
        this.context = context;
        this.sList = sList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.score_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ScoreModel model = sList.get(position);
        holder.EventName.setText(model.getEventName());
        holder.Score.setText(model.getScore());
    }

    @Override
    public int getItemCount() {
        return sList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView EventName, Score;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            EventName = itemView.findViewById(R.id.event_name_score_text);
            Score = itemView.findViewById(R.id.event_score_text);
        }
    }
}
