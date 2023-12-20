package com.example.lab4;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity
{
    private ArrayList<String> notes;
    private ArrayAdapter<String> adapter;
    private NoteManager noteManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteManager = NoteManager.getInstance(this);
        notes = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        Button btnAddNote = findViewById(R.id.btnAddNote);
        Button btnDeleteNote = findViewById(R.id.btnDeleteNote);
        btnAddNote.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
            }
        });
        btnDeleteNote.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, DeleteNoteActivity.class));
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedNoteName = notes.get(position);
                String selectedNoteContent = NoteManager.getNoteContent(MainActivity.this, selectedNoteName);
                Intent viewNoteIntent = new Intent(MainActivity.this, ViewNoteActivity.class);
                viewNoteIntent.putExtra("NOTE_NAME", selectedNoteName);
                viewNoteIntent.putExtra("NOTE_CONTENT", selectedNoteContent);
                startActivity(viewNoteIntent);
            }
        });
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        notes.clear();
        notes.addAll(noteManager.getNoteNames());
        adapter.notifyDataSetChanged();
    }
}
