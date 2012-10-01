/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.ass2note.notepad;



import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.ass2note.R;
import com.example.ass2note.location.GoogleMapsActivity;

public class NoteEdit extends Activity  {
	
	private TextView mydateview;
	private TextView mytimeview;
    private EditText mTitleText;
    private EditText mBodyText;
  
   
  
    private Long mRowId;
    private NotesDbAdapter mDbHelper;
    
    private int day = 1;
public String date = " HELLO";
    
//Date
//private DateFormat fmtDate = DateFormat.getDateInstance();
Calendar myCalendar = Calendar.getInstance();

DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
public void onDateSet(DatePicker view, int year, int monthOfYear,
		int dayOfMonth) {
myCalendar.set(Calendar.YEAR, year);
myCalendar.set(Calendar.MONTH, monthOfYear);
myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

updateLabel( year, monthOfYear,  dayOfMonth);

}
};

TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
		myCalendar.set(Calendar.MINUTE, minute);
		updateTime(hourOfDay, minute);
	}
};
private void updateLabel(int year, int monthOfYear, int dayOfMonth) {
	mydateview.setText(dayOfMonth+"/"+monthOfYear+"/"+year); 
}
private void updateTime(int h, int m){
	mytimeview.setText(h+"/"+m);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHelper = new NotesDbAdapter(this);
        mDbHelper.open();

        setContentView(R.layout.note_edit);
        setTitle(R.string.edit_note);

        mTitleText = (EditText) findViewById(R.id.title);
        mBodyText = (EditText) findViewById(R.id.body);
        mydateview = (TextView) findViewById(R.id.date);
        mytimeview = (TextView) findViewById(R.id.time);
        
    	Button datebutton = (Button) findViewById(R.id.dateButton);
    	Button timebutton = (Button) findViewById(R.id.timeButton);
    	Button positionbutton = (Button) findViewById(R.id.positionButton);
        Button confirmButton = (Button) findViewById(R.id.confirm);
       
        
        mRowId = (savedInstanceState == null) ? null :
            (Long) savedInstanceState.getSerializable(NotesDbAdapter.KEY_ROWID);
		if (mRowId == null) {
			Bundle extras = getIntent().getExtras();
			mRowId = extras != null ? extras.getLong(NotesDbAdapter.KEY_ROWID)
									: null;
		}

		populateFields();
		
	timebutton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				new TimePickerDialog(NoteEdit.this, t, myCalendar
						.get(Calendar.HOUR_OF_DAY), myCalendar
						.get(Calendar.MINUTE), true).show();
				
			}
		});
		
	datebutton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				new DatePickerDialog(NoteEdit.this, d, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();
				
			}
		});
	
	positionbutton.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			startGoogleMapsActivity();
		}
	});
	
    confirmButton.setOnClickListener(new View.OnClickListener() {

        public void onClick(View view) {
            setResult(RESULT_OK);
            finish();
        }

    });
        
   
    }
    
    public void startGoogleMapsActivity(){
    	Intent il = new Intent(NoteEdit.this, GoogleMapsActivity.class);
    	startActivity(il);
    }

    private void populateFields() {
        if (mRowId != null) {
            Cursor note = mDbHelper.fetchNote(mRowId);
            startManagingCursor(note);
            mTitleText.setText(note.getString(
                    note.getColumnIndexOrThrow(NotesDbAdapter.KEY_TITLE)));
            mBodyText.setText(note.getString(
                    note.getColumnIndexOrThrow(NotesDbAdapter.KEY_BODY)));
            mydateview.setText(note.getString(
                    note.getColumnIndexOrThrow(NotesDbAdapter.KEY_DAY)));
            mytimeview.setText(note.getString(
                    note.getColumnIndexOrThrow(NotesDbAdapter.KEY_TIME)));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState();
        outState.putSerializable(NotesDbAdapter.KEY_ROWID, mRowId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateFields();
    }

    private void saveState() {
        String title = mTitleText.getText().toString();
        String body = mBodyText.getText().toString();
        String day = mydateview.getText().toString();
        String time = mytimeview.getText().toString();
        
        if (mRowId == null) {
            long id = mDbHelper.createNote(title, body,day, time);
            if (id > 0) {
                mRowId = id;
            }
        } else {
            mDbHelper.updateNote(mRowId, title, body, day, time);
        }
    }
   
    public void startGoogleMaps(View view){
    	Intent i = new Intent(this, GoogleMapsActivity.class);
    	startActivity(i);
    }

}
