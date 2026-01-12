package com.example.eventplanner01;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventplanner01.data.Event;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Event event);
    }

    private final List<Event> items = new ArrayList<>();
    private final OnItemClickListener listener;

    public EventAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setItems(List<Event> events) {
        items.clear();
        items.addAll(events);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = items.get(position);
        holder.title.setText(event.getName());
        holder.datetime.setText(event.getDate() + " " + event.getTime());
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(event);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView datetime;

        EventViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_title);
            datetime = itemView.findViewById(R.id.text_datetime);
        }
    }
}