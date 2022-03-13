package com.mama.ai;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements  MainContract.View {
    MainContract.Presenter presenter;
    EditText epcoff,epcon;
    TextView duration;
    Button calculate;
    SetTime setTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        epcoff = findViewById(R.id.epc_off_time);
        epcon  = findViewById(R.id.epc_on_time);
        calculate = findViewById(R.id.calculate);
        duration = findViewById(R.id.duration);
        presenter = new MainPresenter();
        //attach the view to presenter
        presenter.setView(this);
        epcoff.setOnClickListener(view ->{pickTime();
            setTime = (time,timeInMilliSec) -> {
                epcoff.setText(time);
                presenter.setEPCoffTime(timeInMilliSec);
            };
        });

        epcon.setOnClickListener(view -> {pickTime();
            setTime = (time,timeInMilliSec) -> {
                epcon.setText(time);
                presenter.setEPConTime(timeInMilliSec);
            };
        });

        calculate.setOnClickListener(view -> presenter.calculate());
    }

    private void pickTime(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar,(timePicker, h, m) -> {
            calendar.set(Calendar.HOUR_OF_DAY,h);
            calendar.set(Calendar.MINUTE,m);
            long timeInMilliSec = calendar.getTimeInMillis();

            String time = calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE)+" ";
            if (calendar.get(Calendar.AM_PM) == Calendar.AM)
                time= time.concat("AM");
            else time+= "PM";
                setTime.setTime(time,timeInMilliSec);
            },hour,min,false);
        timePickerDialog.setTitle("Choose hour:");
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();
    }

    @Override
    public void setDuration(String duration) {
     this.duration.setText(duration);
    }

    interface SetTime{
        void setTime(String time,long timeInMilliSec);
    }
}