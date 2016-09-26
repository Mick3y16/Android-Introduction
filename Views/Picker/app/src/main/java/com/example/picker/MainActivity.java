package com.example.picker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    /**
     * The default format of date and time in the system.
     */
    private DateFormat formatDateTime = DateFormat.getDateTimeInstance();

    /**
     * The date and time in the system.
     */
    private Calendar dateAndTime = Calendar.getInstance();

    /**
     * Text view where the date and time are presented.
     */
    private TextView textViewDateAndTime;

    /**
     * Allows picking the date and associate it with the Calendar object.
     */
    private DatePickerDialog.OnDateSetListener datePickerDialog = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            MainActivity.this.dateAndTime.set(Calendar.YEAR, year);
            MainActivity.this.dateAndTime.set(Calendar.MONTH, monthOfYear);
            MainActivity.this.dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    /**
     * Allows picking the time and associate it with the Calendar object.
     */
    private TimePickerDialog.OnTimeSetListener timePickerDialog = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minuteOfDay) {
            MainActivity.this.dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            MainActivity.this.dateAndTime.set(Calendar.MINUTE, minuteOfDay);
            updateLabel();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.textViewDateAndTime = (TextView) findViewById(R.id.textView);
        updateLabel();
    }

    /**
     * Called when the user selects the date.
     *
     * @param view Date Picker.
     */
    public void chooseDate(View view) {
        new DatePickerDialog(this, this.datePickerDialog, this.dateAndTime.get(Calendar.YEAR),
                this.dateAndTime.get(Calendar.MONTH), this.dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
    }

    /**
     * Called when the user selects the time.
     *
     * @param view Time Picker.
     */
    public void chooseTime(View view) {
        new TimePickerDialog(this, this.timePickerDialog, this.dateAndTime.get(Calendar.HOUR_OF_DAY),
                this.dateAndTime.get(Calendar.MINUTE), true).show();
    }

    /**
     * Places the current date and time in the time view, using the system's format.
     */
    private void updateLabel() {
        this.textViewDateAndTime.setText(this.formatDateTime.format(this.dateAndTime.getTime()));
    }

}
