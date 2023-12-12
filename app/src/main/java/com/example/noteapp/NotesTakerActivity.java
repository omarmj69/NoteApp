package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.noteapp.Models.Agent;
import com.example.noteapp.Models.Dependencies;
import com.example.noteapp.Models.Notes;
import com.google.android.material.bottomappbar.BottomAppBar;

import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NotesTakerActivity extends AppCompatActivity {
    EditText editText_title,edittext_notes,editText_Agent,editText_dependencies,editText_descraption;
    ImageView imageView_save;
    Notes notes;
    Agent agent;
    Dependencies dependencies;
    boolean isOldNote = false;
    TimePicker timePicker;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);

        timePicker = findViewById(R.id.timepicker_id);
        datePicker = findViewById(R.id.datepicker);
        editText_Agent = findViewById(R.id.Agent);
        editText_dependencies =findViewById(R.id.dependencies);
        editText_descraption = findViewById(R.id.descraption);
        imageView_save =findViewById(R.id.imageView_save);
        editText_title =findViewById(R.id.editText_title);
        edittext_notes =findViewById(R.id.edittext_notes);

        timePicker.setIs24HourView(true);

        notes = new Notes();
        dependencies = new Dependencies();
        agent = new Agent();
        try {
            notes = (Notes) getIntent().getSerializableExtra("old_note");
            editText_title.setText(notes.getTitle());
            edittext_notes.setText(notes.getNotes());
            dependencies = (Dependencies) getIntent().getSerializableExtra( "old_dependencies");
            editText_dependencies.setText(dependencies.getName());
            agent = (Agent) getIntent().getSerializableExtra("old_agent");
            editText_Agent.setText(agent.getName());
            editText_descraption.setText(agent.getDescraption());
            timePicker.setHour(1);
            isOldNote = true;
        }catch (Exception e){
            e.printStackTrace();
        }


        imageView_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editText_title.getText().toString();
                String descraption = edittext_notes.getText().toString();
                String Agent = editText_Agent.getText().toString();
                String des = editText_descraption.getText().toString();
                String name = editText_dependencies.getText().toString();
                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year,month,day);


                if (descraption.isEmpty()){
                    Toast.makeText(NotesTakerActivity.this, "Please Add Some Notes",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (title.isEmpty()){
                    Toast.makeText(NotesTakerActivity.this, "Please Add The Title",Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat formatter = new SimpleDateFormat("EEE,d MMM yyyy HH:mm a");
                Date date = new Date();

                if (!isOldNote) {
                    notes = new Notes();
                    dependencies = new Dependencies();
                    agent = new Agent();
                }


                dependencies.setName(name);
                agent.setName(Agent);
                agent.setDescraption(des);
                notes.setTitle(title);
                notes.setNotes(descraption);
                notes.setDate(formatter.format(date));

                Intent intent = new Intent();
                intent.putExtra("note",notes);
                intent.putExtra("dependencies",dependencies);
                intent.putExtra("agent",agent);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}