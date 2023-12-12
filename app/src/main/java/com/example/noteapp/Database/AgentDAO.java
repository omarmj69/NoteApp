package com.example.noteapp.Database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.noteapp.Models.Agent;
import com.example.noteapp.Models.Dependencies;

import java.util.List;

@Dao
public interface AgentDAO {
    @Insert(onConflict = REPLACE)
    void insert(Agent agent);
    @Query("SELECT * FROM Agent ORDER BY Agent_id DESC")
    List<Agent> getAll();

    @Query("UPDATE Agent SET name = :name,descraption = :descraption,note_id =:note_id WHERE AGENT_ID = :id")
    void update(int id,String name,String descraption,int note_id);

}
