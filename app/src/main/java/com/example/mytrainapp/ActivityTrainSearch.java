package com.example.mytrainapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;

public class ActivityTrainSearch extends AppCompatActivity {


    EditText date;
    ImageView dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_search);


        //Calendar
        date = findViewById(R.id.date_selector);
        dialog = findViewById(R.id.date_dialog);

            //instance of calendar
        Calendar calendar = Calendar.getInstance();

        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // to show the date picker dialog box
                DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityTrainSearch.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    //sending selected date to the edit text
                        date.setText(i2 + "/" + (i1 + 1) + "/" + i);
                        Toast.makeText(ActivityTrainSearch.this, i2 + "-" + (i1 + 1) + "-" + i, Toast.LENGTH_SHORT).show();

                    }
                    //getting to know current date
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });

    }


}
