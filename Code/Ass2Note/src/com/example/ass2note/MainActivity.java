package com.example.ass2note;

import com.example.ass2note.notepad.Notepad;
import com.example.ass2note.notepad.NotesDbAdapter;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noteblock_activity);
      //  startNoteblock();
        
        // solveig was here!!!!!!!
    }
    		//hei
    //Martin var også her!
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
   public void startNoteblock(){
	   Intent intent = new Intent (this, Notepad.class);
       startActivity(intent);
   }
}
