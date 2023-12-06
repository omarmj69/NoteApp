package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.noteapp.Models.Notes;
import com.google.android.material.bottomappbar.BottomAppBar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesTakerActivity extends AppCompatActivity {
    EditText editText_title,edittext_notes;
    ImageView imageView_save;
    Notes notes;
    boolean isOldNote = false;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);

        timePicker = findViewById(R.id.timepicker_id);
        imageView_save =findViewById(R.id.imageView_save);
        editText_title =findViewById(R.id.editText_title);
        edittext_notes =findViewById(R.id.edittext_notes);

        timePicker.setIs24HourView(true);

        notes = new Notes();
        try {
            notes = (Notes) getIntent().getSerializableExtra("old_note");
            editText_title.setText(notes.getTitle());
            edittext_notes.setText(notes.getNotes());
            isOldNote = true;
        }catch (Exception e){
            e.printStackTrace();
        }


        imageView_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editText_title.getText().toString();
                String descraption = edittext_notes.getText().toString();

                if (descraption.isEmpty()){
                    Toast.makeText(NotesTakerActivity.this, "Please Add Some Notes",Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat formatter = new SimpleDateFormat("EEE,d MMM yyyy HH:mm a");
                Date date = new Date();

                if (!isOldNote) {
                    notes = new Notes();
                }

                notes.setTitle(title);
                notes.setNotes(descraption);
                notes.setDate(formatter.format(date));

                Intent intent = new Intent();
                intent.putExtra("note",notes);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}