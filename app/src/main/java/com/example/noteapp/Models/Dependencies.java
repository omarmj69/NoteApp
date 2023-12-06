package com.example.noteapp.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Dependencies",foreignKeys = @ForeignKey(entity = Notes.class,parentColumns = "id",childColumns = "Dependencies_id",onDelete = ForeignKey.CASCADE))
public class Dependencies {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Dependencies_id")
    int Dependencies_id =0;

    @ColumnInfo(name = "name")
    String name = "";


    public int getDependencies_id() {
        return Dependencies_id;
    }

    public void setDependencies_id(int dependencies_id) {
        Dependencies_id = dependencies_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
