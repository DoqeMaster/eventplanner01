package com.example.eventplanner01.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "events")
public class Event {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String date;
    private String time;
    private String location;
    private String notes;
    private String reminderTime;
    private String attendance;

    public Event(String name, String date, String time, String location,
                 String notes, String reminderTime, String attendance) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.notes = notes;
        this.reminderTime = reminderTime;
        this.attendance = attendance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }
}