package com.example.eventplanner01.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EventDao {

    @Insert
    long insert(Event event);

    @Update
    void update(Event event);

    @Delete
    void delete(Event event);

    @Query("SELECT * FROM events WHERE id = :id LIMIT 1")
    Event getEventById(int id);

    @Query("SELECT * FROM events ORDER BY date, time LIMIT 1")
    Event getNextEvent();

    @Query("SELECT * FROM events ORDER BY date, time")
    List<Event> getAllEvents();
}