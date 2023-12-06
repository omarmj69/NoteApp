package com.example.noteapp.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Agent",foreignKeys = @ForeignKey(entity = Notes.class,parentColumns = "id",childColumns = "Agent_id",onDelete = ForeignKey.CASCADE))

public class Agent {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Agent_id")
    int Agent_id =0;

    @ColumnInfo(name = "name")
    String name = "";

    @ColumnInfo(name = "descraption")
    String descraption = "";

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
