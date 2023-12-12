package com.example.noteapp.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Agent",foreignKeys = @ForeignKey(entity = Notes.class,parentColumns = "id",childColumns = "note_id",onDelete = ForeignKey.CASCADE))

public class Agent implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int Agent_id =0;

    @ColumnInfo(name = "name")
    String name = "";

    @ColumnInfo(name = "descraption")
    String descraption = "";

    @ColumnInfo(name = "note_id")
    int note_id =0;

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public int getAgent_id() {
        return Agent_id;
    }

    public void setAgent_id(int agent_id) {
        Agent_id = agent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescraption() {
        return descraption;
    }

    public void setDescraption(String descraption) {
        this.descraption = descraption;
    }
}
