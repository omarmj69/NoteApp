package com.example.noteapp;

import androidx.cardview.widget.CardView;

import com.example.noteapp.Models.Agent;
import com.example.noteapp.Models.Dependencies;
import com.example.noteapp.Models.Notes;

public interface NotesClickListener {
    void onCclick(Notes notes, Dependencies dependencies, Agent agent);

    void onLongClick(Notes notes, CardView cardView);

}
