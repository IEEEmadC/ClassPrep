package com.aerial.classprep;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class add_subject extends AppCompatActivity {
    Database db;
    Calendar myCalendar;
    Calendar trTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        Toolbar tb=(Toolbar)findViewById(R.id.toolbarSub);
        setSupportActionBar(tb);

        getSupportActionBar().setTitle("Add Subject");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db=new Database(this);
        myCalendar = Calendar.getInstance();
        trTime= (Calendar) myCalendar.clone();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void addSubject(View view) {
        EditText nt=(EditText) findViewById(R.id.nameText);
        EditText ct=(EditText) findViewById(R.id.codeText);
        EditText sdt=(EditText) findViewById(R.id.stDateText);
        EditText endt=(EditText) findViewById(R.id.endDateText);
        EditText wt=(EditText) findViewById(R.id.nfwText);
        Spinner dt=(Spinner)findViewById(R.id.dayText);
        String sid=db.getLastSID();
        int ssid=Integer.parseInt(sid)+1;
        sid=Integer.toString(ssid);
        int weeks=Integer.parseInt(wt.getText().toString());

        Subject s=new Subject(sid,nt.getText().toString(),ct.getText().toString(),sdt.getText().toString(),
                endt.getText().toString(),dt.getSelectedItem().toString(),weeks);
        db.insertSubjectData(s);

        EditText lt=(EditText)findViewById(R.id.lessonText);
        String ldata=lt.getText().toString();
        String[] split = ldata.split("\n");
        int llid=Integer.parseInt(db.getLastLID())+1;

        for(int i=0;i<split.length;i++){
            Lesson l=new Lesson(Integer.toString(llid+i),sid,split[i],nt.getText().toString(),dt.getSelectedItem().toString());
            db.insertLessonData(l);
        }
        alert("Get ready to the next class","Checkout what will be in your next class");

        Intent nn=new Intent(this,MainActivity.class);
        this.startActivity(nn);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void alert(String sub,String lss){
        Intent intent=new Intent(this,AlertReceiver.class);
        intent.putExtra("subject",sub);
        intent.putExtra("lesson",lss);
        AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,trTime.getTimeInMillis(), PendingIntent.getBroadcast(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT));
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,trTime.getTimeInMillis(),1000*60*60*24*7,PendingIntent.getBroadcast(this,1,intent,PendingIntent.FLAG_UPDATE_CURRENT));
    }


    public void showContent(View view) {
        Lesson data = db.getData("1");
        ArrayList<DaySL> lessons = db.getLessons("Saturday");
        Toast.makeText(getApplicationContext(),lessons.size(),Toast.LENGTH_LONG).show();
    }

    public void setStartDate(View view) {

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                trTime.set(Calendar.YEAR, year);
                trTime.set(Calendar.MONTH, month);
                trTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "MM/dd/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                EditText sdt=(EditText)findViewById(R.id.stDateText);
                sdt.setText(sdf.format(myCalendar.getTime()));
            }
        };
        new DatePickerDialog(this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();


    }

    public void setEndDate(View view) {

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "MM/dd/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                EditText sdt=(EditText)findViewById(R.id.endDateText);
                sdt.setText(sdf.format(myCalendar.getTime()));
            }
        };
        new DatePickerDialog(this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();


    }

    public void setAlarmTime(View view) {

        TimePickerDialog.OnTimeSetListener date = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                myCalendar.set(Calendar.MINUTE, minute);

                trTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                trTime.set(Calendar.MINUTE, minute);

                String myFormat = "hh:mm"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                EditText sdt=(EditText)findViewById(R.id.timeText);
                sdt.setText(sdf.format(myCalendar.getTime()));
            }

        };
        new TimePickerDialog(this, date, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE),true).show();


    }
}
