package com.example.todolist.clickHandlers;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.todolist.R;

import java.util.Calendar;

public class DateTimeDialogClickHandler {

    Context context;
    TextView textView;

    public DateTimeDialogClickHandler(Context context, TextView textView) {
        this.context = context;
        this.textView = textView;
    }

    public void onDatePickerClicked(View view){
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                R.style.DatePickerDialogTheme,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = dayOfMonth + "-" + (month + 1) + "-" + year;
                        textView.setText(date);
                    }
                }, year, month, day);

        datePickerDialog.show();
    }

    public void onTimePickerClicked(View view){
        Calendar calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                R.style.TimePickerDialogTheme,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = hourOfDay + ":" + minute;
                        textView.setText(time);
                    }
                }, hour, minute, true);

        timePickerDialog.show();
    }
}
