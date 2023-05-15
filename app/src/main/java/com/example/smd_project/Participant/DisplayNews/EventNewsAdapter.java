package com.example.smd_project.Participant.DisplayNews;

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

import java.util.ArrayList;

public class EventNewsAdapter extends RecyclerView.Adapter<EventNewsAdapter.MyViewHolder>
{
    private ArrayList<EventNewsModel> nlist;
    Context context;
    View view;

    public EventNewsAdapter(Context context, ArrayList<EventNewsModel> nlist)
    {
        this.context = context;
        this.nlist = nlist;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.event_news_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        EventNewsModel model = nlist.get(position);
        holder.tv1.setText(model.getEvent_Name());
        holder.tv2.setText(model.getNews());
    }

    @Override
    public int getItemCount() {
        return nlist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView tv1,tv2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.event_name_text_1);
            tv2 = itemView.findViewById(R.id.event_news_text_1);
        }
    }
}
