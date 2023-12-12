package com.example.noteapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.noteapp.Models.Agent;
import com.example.noteapp.Models.Dependencies;
import com.example.noteapp.Models.Notes;

@Database(entities = {Notes.class,Agent.class, Dependencies.class}, version = 7, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static RoomDB database;
    private static String DATABASE_NAME = "NoteApp";

    public synchronized static RoomDB getInstance(Context context){
        if (database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract MainDAO mainDAO();
    public abstract AgentDAO agentDAO();
    public abstract DependenciesDAO dependenciesDAO();
}
