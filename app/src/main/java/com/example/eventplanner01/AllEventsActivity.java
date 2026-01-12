package com.example.eventplanner01;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventplanner01.data.Event;
import com.example.eventplanner01.data.EventDatabase;

import java.util.List;
import java.util.concurrent.Executors;

public class AllEventsActivity extends AppCompatActivity {

    private EventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_events);

        RecyclerView recyclerView = findViewById(R.id.recycler_events);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new EventAdapter(event -> {
            Intent intent = new Intent(AllEventsActivity.this, EditEventActivity.class);
            intent.putExtra("event_id", event.getId());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Executors.newSingleThreadExecutor().execute(() -> {
            List<Event> events = EventDatabase.getInstance(getApplicationContext())
                    .eventDao()
                    .getAllEvents();
            runOnUiThread(() -> adapter.setItems(events));
        });
    }
}