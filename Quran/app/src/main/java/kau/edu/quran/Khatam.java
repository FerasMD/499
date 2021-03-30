package kau.edu.quran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CompoundButton;

import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Khatam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        createNotificationChannel();
        DB db = new DB (this);
       if (!db.isEmpty("khatm")){
      Intent intent=new Intent(getApplicationContext(),ManageKhatam.class);
      startActivity(intent);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khatam);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bot);
        bottomNavigationView.setSelectedItemId(R.id.das);

        Button b1 = findViewById(R.id.b1);
        CalendarView calendarView=findViewById(R.id.calendarView);
        NumberPicker numberPicker= (NumberPicker) findViewById(R.id.num);

        numberPicker.setValue(10);
        numberPicker.setMaxValue(200);
        numberPicker.setMinValue(1);
       // numberPicker.setMin(1);

        //numberPicker.setMax(200);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.das:


                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(),Settings.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
        RadioButton radioButton=findViewById(R.id.r1);
        radioButton.setChecked(true);
        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (!b){
                    System.out.println("iuvgbbbbbbbbbbbbbbbbbbbbbbbbbbd");


                    calendarView.setVisibility(View.GONE);

                }else {
                    calendarView.setVisibility(View.VISIBLE);
                }
            }
        });

        RadioButton radioButton2=findViewById(R.id.r2);

radioButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        if (b){
            numberPicker.setVisibility(View.VISIBLE);


        }else{
            numberPicker.setVisibility(View.GONE);
        }
    }
});
        calendarView.setMinDate(calendarView.getDate());
        final String[] sss = {""};
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                sss[0] =i2+"/"+(i1+1)+"/"+i;
                System.out.println(sss[0] );
            }
        });





    b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Switch aSwitch=findViewById(R.id.switch2);
            if (aSwitch.isChecked()) {
                TimePicker timePicker=findViewById(R.id.timePicker1);
                System.out.println(timePicker.getHour());
                System.out.println(timePicker.getMinute());

                System.out.println("yes");


                sendNotification(timePicker.getHour(),timePicker.getMinute());
              // Toast.makeText(Khatam.this, "Remid", Toast.LENGTH_SHORT).show();

            }

          //  db.delete("1");
        if(db.isEmpty("khatm")){
            if(radioButton.isChecked()) {
                String startDate = dateFormat.format(cal.getTime());
                String endDate = sss[0];
                long daysDiff = getDateDiff(dateFormat, startDate, endDate);
                if (daysDiff > 0) {
                    int dailyPages = (int) ((int) 604 / daysDiff);
                    db.insertInto1(1, dailyPages, startDate, endDate, "الفَاتِحة ", 1, 1);
                    Toast.makeText(Khatam.this, "Your schedule has been created", Toast.LENGTH_SHORT).show();
                    ArrayList<Object> attrs = db.getAllAttr();
                    System.out.println(attrs.get(0) + " " + attrs.get(1) + " " + attrs.get(2) + " " + attrs.get(3) + " " + attrs.get(4)
                            + " " + attrs.get(5) + " " + attrs.get(6));
                    startActivity(new Intent(getApplicationContext(),ManageKhatam.class));
                }else{
                    Toast.makeText(Khatam.this, "لا تستطيع اختيار نفس اليوم", Toast.LENGTH_SHORT).show();
                }


            }

                //System.out.println(day);

                /*db.insertInto1(1,0,startDate,endDate,"الفاتحة", 1,1);
                Toast.makeText(Khatam.this, "Your schedule has been created", Toast.LENGTH_SHORT).show();
                ArrayList<Object> attrs = db.getAllAttr();
                System.out.println(attrs.get(0)+" "+attrs.get(1)+" "+attrs.get(2)+" "+attrs.get(3)+" "+attrs.get(4)
                        +" "+attrs.get(5)+" "+attrs.get(6));*/

            else {
                int dailyPages = numberPicker.getValue();
                String startDate = dateFormat.format(cal.getTime());
                int daysToFinish = 604/ dailyPages;
              Date date=  cal.getTime();
                cal.add(Calendar.DATE, daysToFinish);

                String endDate = dateFormat.format(cal.getTime());
                cal.setTime(date);
                System.out.println(cal.getTime());
                db.insertInto1(1,dailyPages,startDate,endDate,"الفَاتِحة ", 1,1);
                ArrayList<Object> attrs = db.getAllAttr();
                System.out.println(attrs.get(0)+" "+attrs.get(1)+" "+attrs.get(2)+" "+attrs.get(3)+" "+attrs.get(4)
                        +" "+attrs.get(5)+" "+attrs.get(6));
                Toast.makeText(Khatam.this, "Your schedule has been created", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),ManageKhatam.class));
                /*ArrayList<Object> attrs = db.getAllAttr();
                System.out.println(attrs.get(0)+" "+attrs.get(1)+" "+attrs.get(2)+" "+attrs.get(3)+" "+attrs.get(4)
                        +" "+attrs.get(5)+" "+attrs.get(6));*/
                /*boolean r = db.update("1","",10,"aa",12,2);
                if(r==true)
                    Toast.makeText(Khatam.this, "tru", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Khatam.this, "no", Toast.LENGTH_SHORT).show();*/
                //int t =db.delete("1");
                //System.out.println(t);
            }}  else
            Toast.makeText(Khatam.this, "You have a schedule already.", Toast.LENGTH_SHORT).show();
            //Toast.makeText(Khatam.this, "hhh", Toast.LENGTH_SHORT).show();



    }});

    }


    public static long getDateDiff(SimpleDateFormat format, String oldDate, String newDate) {
        try {
            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }



    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Ahmed";
            String description = "pleaz work";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("noti", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    private  void sendNotification(int hour, int minute){
        Intent intent = new Intent(Khatam.this, bodRec.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Khatam.this, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);
        // long timeAtb = System.currentTimeMillis();
        //   long ten = 1000 * 10;


        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);


    }

}
