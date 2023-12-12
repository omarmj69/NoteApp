package com.example.noteapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.noteapp.Adapters.NotesListAdapter;
import com.example.noteapp.Database.RoomDB;
import com.example.noteapp.Models.Agent;
import com.example.noteapp.Models.Dependencies;
import com.example.noteapp.Models.Notes;
import com.google.android.material.R.id;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  PopupMenu.OnMenuItemClickListener{
    RecyclerView recyclerView;
    NotesListAdapter notesListAdapter;
    List<Notes> notes = new ArrayList<>();
    List<Dependencies> dependencies = new ArrayList<>();
    List<Agent> agents = new ArrayList<>();
    RoomDB database;
    FloatingActionButton fab_add;
    SearchView searchView_home;
    Notes selectedNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_home);
        fab_add = findViewById(R.id.fab_add);
        searchView_home = findViewById(R.id.searchView_home);

        database = RoomDB.getInstance(this);
        dependencies = database.dependenciesDAO().getAll();
        agents = database.agentDAO().getAll();
        notes = database.mainDAO().getAll();

    updateRecycler(notes);
    fab_add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, NotesTakerActivity.class);
            startActivityForResult(intent, 101);
        }
    });

        searchView_home.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            filter(newText);
            return true;
        }
    });

    }

    private void filter(String newText) {
        List<Notes> filteredList = new ArrayList<>();
        for (Notes singleNote : notes){
            if (singleNote.getTitle().toLowerCase().contains(newText.toLowerCase())
             || singleNote.getNotes().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(singleNote);
            }
        }
        notesListAdapter.filterList(filteredList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101){
            if (resultCode == Activity.RESULT_OK){
                Notes new_notes = (Notes) data.getSerializableExtra("note");
                Dependencies new_Dependencies = (Dependencies) data.getSerializableExtra("dependencies");
                Agent new_Agent = (Agent) data.getSerializableExtra("agent");
                database.mainDAO().insert(new_notes);
                notes.clear();
                notes.addAll(database.mainDAO().getAll());
                int maxnoteid = database.mainDAO().getmaxid();
                new_Dependencies.setNote_id(maxnoteid);
                database.dependenciesDAO().insert(new_Dependencies);
                dependencies.clear();
                dependencies.addAll(database.dependenciesDAO().getAll());
                new_Agent.setNote_id(maxnoteid);
                database.agentDAO().insert(new_Agent);
                agents.clear();
                agents.addAll(database.agentDAO().getAll());
                notesListAdapter.notifyDataSetChanged();
            }
        } else if (requestCode == 102) {
            if (resultCode == Activity.RESULT_OK){
                Notes new_notes = (Notes) data.getSerializableExtra("note");
                Dependencies new_Dependencies = (Dependencies) data.getSerializableExtra("dependencies");
                Agent new_Agent = (Agent) data.getSerializableExtra("agent");
                database.mainDAO().update(new_notes.getId(), new_notes.getTitle(), new_notes.getNotes());
                database.dependenciesDAO().update(new_Dependencies.getDependencies_id(), new_Dependencies.getName(),new_Dependencies.getNote_id());
                database.agentDAO().update(new_Agent.getAgent_id(),new_Agent.getName(),new_Agent.getDescraption(),new_Agent.getNote_id());
                notes.clear();
                dependencies.clear();
                agents.clear();
                dependencies.addAll(database.dependenciesDAO().getAll());
                notes.addAll(database.mainDAO().getAll());
                agents.addAll(database.agentDAO().getAll());
                notesListAdapter.notifyDataSetChanged();

            }

        }
    }

    private void updateRecycler(List<Notes> notes) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));
        notesListAdapter = new NotesListAdapter(MainActivity.this, notes,dependencies,agents,notesClickListener);
        recyclerView.setAdapter(notesListAdapter);
    }

    private final NotesClickListener notesClickListener = new NotesClickListener() {
        @Override
        public void onCclick(Notes notes,Dependencies dependencies,Agent agent) {
            Intent intent = new Intent(MainActivity.this,NotesTakerActivity.class);
            intent.putExtra("old_note", notes);
            intent.putExtra("old_dependencies", dependencies);
            intent.putExtra("old_agent",agent);
            startActivityForResult(intent, 102);

        }

        @Override
        public void onLongClick(Notes notes, CardView cardView) {
            selectedNote = new Notes();
            selectedNote = notes;
            showPopup(cardView);

        }
    };

    private void showPopup(CardView cardView) {
        PopupMenu popupMenu = new PopupMenu(this, cardView);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();

    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.pin){
            if (selectedNote.isPinned()){
                database.mainDAO().pin(selectedNote.getId(), false);
                Toast.makeText(MainActivity.this, "Unpinned!",Toast.LENGTH_SHORT).show();

            }
            else {
                database.mainDAO().pin(selectedNote.getId(), true);
                Toast.makeText(MainActivity.this, "pinned!",Toast.LENGTH_SHORT).show();

            }
            notes.clear();
            notes.addAll(database.mainDAO().getAll());
            notesListAdapter.notifyDataSetChanged();
            return true;
        }
        else if (id== R.id.delete) {
            database.mainDAO().delete(selectedNote);
            notes.remove(selectedNote);
            notesListAdapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this,"Note Deleted!",Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}