package com.example.charityyyyy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    public EventAdapter(List<Event> eventList) {
        this.eventList = eventList;
    }

    private List<Event> eventList;

    public EventAdapter(EventList eventList, ArrayList<Event> list) {
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View eventview= LayoutInflater.from(parent.getContext()).inflate(R.layout.event_view, parent,false);
        return new EventViewHolder(eventview);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.eventTitle.setText(event.getName());
        holder.eventDate.setText(event.getDate());
        holder.eventDescription.setText(event.getDescription());
        holder.eventRequirements.setText(event.getRequirements());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder{
        TextView eventTitle;
        TextView eventDate;
        TextView eventDescription;
        TextView eventRequirements;


        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventTitle=itemView.findViewById(R.id.eventNameTextView);
            eventDate=itemView.findViewById(R.id.eventDateTextView);
            eventRequirements=itemView.findViewById(R.id.eventRequirementsTextView);
            eventDescription=itemView.findViewById(R.id.eventDescriptionTextView);
        }
    }

}






