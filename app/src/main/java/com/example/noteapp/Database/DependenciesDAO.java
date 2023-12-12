package com.example.noteapp.Database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.noteapp.Models.Dependencies;
import com.example.noteapp.Models.Notes;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DependenciesDAO {
    @Insert(onConflict = REPLACE)
    void insert(Dependencies dependencies);
    @Query("SELECT * FROM Dependencies ORDER BY Dependencies_id DESC")
    List<Dependencies> getAll();

    @Query("UPDATE Dependencies SET name = :name,note_id =:note_id WHERE DEPENDENCIES_ID = :id")
    void update(int id,String name,int note_id);


}
