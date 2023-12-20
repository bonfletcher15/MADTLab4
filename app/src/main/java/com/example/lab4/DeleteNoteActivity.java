package com.example.lab4;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
public class DeleteNoteActivity extends AppCompatActivity
{
    private NoteManager noteManager;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> notes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);
        noteManager = NoteManager.getInstance(this);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, notes);
        ListView listView = findViewById(R.id.listViewDelete);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        refreshNoteList();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedNoteName = notes.get(position);
                noteManager.deleteNote(selectedNoteName);
                refreshNoteList();
            }
        });
    }
    private void refreshNoteList()
    {
        notes.clear();
        notes.addAll(noteManager.getNoteNames());
        adapter.notifyDataSetChanged();
    }
}

