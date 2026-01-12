package com.example.eventplanner01;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eventplanner01.data.Event;
import com.example.eventplanner01.data.EventDatabase;

import java.util.concurrent.Executors;

public class EditEventActivity extends AppCompatActivity {

    private int eventId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        EditText inputName = findViewById(R.id.input_name);
        EditText inputDate = findViewById(R.id.input_date);
        EditText inputTime = findViewById(R.id.input_time);
        EditText inputLocation = findViewById(R.id.input_location);
        EditText inputNotes = findViewById(R.id.input_notes);
        EditText inputReminder = findViewById(R.id.input_reminder);
        EditText inputAttendance = findViewById(R.id.input_attendance);

        Button btnSave = findViewById(R.id.btn_save);
        Button btnCancel = findViewById(R.id.btn_cancel);
        Button btnDelete = findViewById(R.id.btn_delete);

        eventId = getIntent().getIntExtra("event_id", -1);
        btnDelete.setVisibility(eventId == -1 ? View.GONE : View.VISIBLE);

        if (eventId != -1) {
            Executors.newSingleThreadExecutor().execute(() -> {
                Event event = EventDatabase.getInstance(getApplicationContext())
                        .eventDao()
                        .getEventById(eventId);
                if (event == null) {
                    return;
                }
                runOnUiThread(() -> {
                    inputName.setText(event.getName());
                    inputDate.setText(event.getDate());
                    inputTime.setText(event.getTime());
                    inputLocation.setText(event.getLocation());
                    inputNotes.setText(event.getNotes());
                    inputReminder.setText(event.getReminderTime());
                    inputAttendance.setText(event.getAttendance());
                });
            });
        }

        btnSave.setOnClickListener(v -> {
            String name = inputName.getText().toString().trim();
            String date = inputDate.getText().toString().trim();
            String time = inputTime.getText().toString().trim();
            String location = inputLocation.getText().toString().trim();
            String notes = inputNotes.getText().toString().trim();
            String reminderTime = inputReminder.getText().toString().trim();
            String attendance = inputAttendance.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(date) || TextUtils.isEmpty(time)) {
                Toast.makeText(this, "Name, date, and time are required.", Toast.LENGTH_SHORT).show();
                return;
            }

            Event event = new Event(name, date, time, location, notes, reminderTime, attendance);

            Executors.newSingleThreadExecutor().execute(() -> {
                if (eventId == -1) {
                    EventDatabase.getInstance(getApplicationContext()).eventDao().insert(event);
                } else {
                    event.setId(eventId);
                    EventDatabase.getInstance(getApplicationContext()).eventDao().update(event);
                }
                runOnUiThread(this::finish);
            });
        });

        btnCancel.setOnClickListener(v -> finish());

        btnDelete.setOnClickListener(v -> {
            if (eventId == -1) {
                return;
            }
            Executors.newSingleThreadExecutor().execute(() -> {
                Event event = new Event("", "", "", "", "", "", "");
                event.setId(eventId);
                EventDatabase.getInstance(getApplicationContext()).eventDao().delete(event);
                runOnUiThread(this::finish);
            });
        });
    }
}