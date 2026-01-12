package com.example.eventplanner01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eventplanner01.data.Event;
import com.example.eventplanner01.data.EventDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private TextView textNextEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button btnAll = findViewById(R.id.btn_all_events);
        Button btnNew = findViewById(R.id.btn_new_event);

        btnAll.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AllEventsActivity.class);
            startActivity(intent);
        });

        btnNew.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditEventActivity.class);
            startActivity(intent);
        });

        TextView textCurrentTime = findViewById(R.id.text_current_time);
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM d · HH:mm", Locale.getDefault());
        textCurrentTime.setText(formatter.format(new Date()));

        textNextEvent = findViewById(R.id.text_next_event);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Executors.newSingleThreadExecutor().execute(() -> {
            Event nextEvent = EventDatabase.getInstance(getApplicationContext())
                    .eventDao()
                    .getNextEvent();
            runOnUiThread(() -> {
                if (nextEvent == null) {
                    textNextEvent.setText("Next event: None yet");
                } else {
                    String summary = nextEvent.getName() + " · " + nextEvent.getDate()
                            + " " + nextEvent.getTime();
                    textNextEvent.setText("Next event: " + summary);
                }
            });
        });
    }
}
