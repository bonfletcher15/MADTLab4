package com.example.lab4;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Map;
public class NoteManager
{
    private static final String PREF_NAME = "NotesPref";
    private static NoteManager instance;
    private static SharedPreferences sharedPreferences;
    private NoteManager(Context context)
    {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }
    public static synchronized NoteManager getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new NoteManager(context);
        }
        return instance;
    }
    public void saveNote(String noteName, String noteContent)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(noteName, noteContent);
        editor.apply();
    }
    public ArrayList<String> getNoteNames()
    {
        ArrayList<String> noteNames = new ArrayList<>();
        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet())
        {
            noteNames.add(entry.getKey());
        }
        return noteNames;
    }
    public void deleteNote(String noteName)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(noteName);
        editor.apply();
    }
    public static String getNoteContent(MainActivity mainActivity, String noteName)
    {
        return sharedPreferences.getString(noteName, "");
    }
}
