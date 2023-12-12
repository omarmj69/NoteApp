package com.example.noteapp.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Dependencies",foreignKeys = @ForeignKey(entity = Notes.class,parentColumns = "id",childColumns = "note_id",onDelete = ForeignKey.CASCADE))
public class Dependencies implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int Dependencies_id =0;

    @ColumnInfo(name = "name")
    String name = "";

    @ColumnInfo(name = "note_id")
    int note_id =0;

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

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
