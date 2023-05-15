package com.example.smd_project.Participant.DisplayVenues;

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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class VenuesAdapter extends RecyclerView.Adapter<VenuesAdapter.MyViewHolder>
{
    ArrayList <VenuesModel> vlist;
    Context context;

    public VenuesAdapter(Context context, ArrayList<VenuesModel> vlist)
    {
        this.vlist = vlist;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.venues_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        VenuesModel model = vlist.get(position);
        holder.Name.setText(model.getName());
        holder.Venue.setText(model.getVenue());
    }

    @Override
    public int getItemCount() {
        return vlist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView Name, Venue;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            Name = itemView.findViewById(R.id.eventNameforVenue);
            Venue = itemView.findViewById(R.id.eventvenue);
        }
    }
}
