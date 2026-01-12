package com.example.eventplanner01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}