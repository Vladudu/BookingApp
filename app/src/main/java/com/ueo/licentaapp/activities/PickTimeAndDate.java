package com.ueo.licentaapp.activities;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.ueo.licentaapp.DataCatcher;
import com.ueo.licentaapp.MainActivity;
import com.ueo.licentaapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PickTimeAndDate extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_time_date);

        Intent intent = new Intent(PickTimeAndDate.this, PageActivity.class);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        long startOfWeek = calendar.getTimeInMillis();
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        long endOfWeek = calendar.getTimeInMillis();

        DatePicker datePicker = findViewById(R.id.datePicker);
        datePicker.setMinDate(startOfWeek);
        datePicker.setMaxDate(endOfWeek);

        TimePicker timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        timePicker.setMinute(0);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if (minute < 30 && minute > 0 ) {
                    Toast.makeText(PickTimeAndDate.this, "Alege doar ora fixa(00) sau ora jumatate(30)", Toast.LENGTH_SHORT).show();
                    view.setMinute(0);
                }else if  (minute > 30 && minute <= 59 ) {
                        Toast.makeText(PickTimeAndDate.this, "Alege doar ora fixa(00) sau ora jumatate(30)", Toast.LENGTH_SHORT).show();
                        view.setMinute(30);
                }
                if (hourOfDay < 10) {
                    Toast.makeText(PickTimeAndDate.this, "Alege o ora din intervalul orar 10:00 - 18:00", Toast.LENGTH_SHORT).show();
                    view.setCurrentHour(10);
                } else if (hourOfDay > 18) {
                    Toast.makeText(PickTimeAndDate.this, "Alege o ora din intervalul orar 10:00 - 18:00", Toast.LENGTH_SHORT).show();
                    view.setCurrentHour(18);
                } else if (hourOfDay == 18 && minute != 0) {
                    view.setCurrentMinute(0);
                }
            }
        });


        /*Button button = findViewById(R.id.selectButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("year", DataCatcher.getInstance().getYear());
                intent.putExtra("month", DataCatcher.getInstance().getMonth());
                intent.putExtra("day", DataCatcher.getInstance().getDay());
                startActivity(intent);

            }
        });*/

        datePicker.init(2020, 0, 1, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                DataCatcher.getInstance().setYear(year);
                DataCatcher.getInstance().setMonth(monthOfYear);
                DataCatcher.getInstance().setDay(dayOfMonth);
            }
        });

            }

    public void saveTime(View view) {
        List<String> unavailableTimes = new ArrayList<>();
        Intent intent = new Intent(PickTimeAndDate.this, PageActivity.class);
        TimePicker timePicker = findViewById(R.id.timePicker);
        int hourOfDay = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();

        String selectedTime = hourOfDay + ":" + minute;
        if (unavailableTimes.contains(selectedTime)) {
            Toast.makeText(PickTimeAndDate.this, "Aceasta ora este deja aleasa", Toast.LENGTH_SHORT).show();
        } else {
            unavailableTimes.add(selectedTime);
        DataCatcher timeSingleton = DataCatcher.getInstance();
        timeSingleton.setHour(hourOfDay);
        timeSingleton.setMinute(minute);
        intent.putExtra("hour", hourOfDay);
        intent.putExtra("minute", minute);
        intent.putExtra("year", DataCatcher.getInstance().getYear());
        intent.putExtra("month", DataCatcher.getInstance().getMonth());
        intent.putExtra("day", DataCatcher.getInstance().getDay());
        startActivity(intent);}
    }
    }

