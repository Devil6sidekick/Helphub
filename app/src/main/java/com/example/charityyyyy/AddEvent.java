package com.example.charityyyyy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddEvent extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private Button addeventbutton;

    private EditText editTextDate,editTextDesc,editTextReq,editTextTitle;
    Button SelectImage;
    private Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        editTextDate = findViewById(R.id.editTextDate);
        editTextTitle=findViewById(R.id.editTextTitle);
        editTextDesc=findViewById(R.id.editTextDescription);
        editTextReq=findViewById(R.id.editTextRequirement);
        // Set current date as default
        addeventbutton=findViewById(R.id.buttonAddEvent);
        calendar = Calendar.getInstance();

        // on below line we are adding click listener
        // for our pick date button
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        AddEvent.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our edit text.
                                editTextDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });
        addeventbutton.setOnClickListener(view -> {
            Toast.makeText(this, "event added!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddEvent.this , homePage.class);
            sendData(view);
            startActivity(intent);
        });

    }


    public void writeNewEvent(){
        Event event = new Event(editTextTitle.getText().toString(),
                editTextDate.getText().toString(),
                editTextDesc.getText().toString(),
                editTextReq.getText().toString());
        HomeFragment.generateEvents(event);
        mDatabase.child("events").child(event.getName()).setValue(event);
    }
    public void sendData(View view){
        writeNewEvent();
    }

}
