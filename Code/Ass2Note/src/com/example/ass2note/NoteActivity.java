package com.example.ass2note;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class NoteActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_note, menu);
        return true;
    }
}