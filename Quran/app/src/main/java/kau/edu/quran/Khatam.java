package kau.edu.quran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;
import com.travijuu.numberpicker.library.NumberPicker;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Khatam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DB db = new DB (this);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khatam);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bot);
        bottomNavigationView.setSelectedItemId(R.id.das);

        Button b1 = findViewById(R.id.b1);
        CalendarView calendarView=findViewById(R.id.calendarView);
        NumberPicker numberPicker= (NumberPicker) findViewById(R.id.num);

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

                sss[0] =i+"/"+(i1+1)+"/"+i2;
                System.out.println(sss[0] );
            }
        });





    b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            db.delete("1");
        if(db.isEmpty("khatm")){
            if(radioButton.isChecked()){
                String startDate = dateFormat.format(cal.getTime());
                String endDate = sss[0];
                        db.insertInto1(1,0,startDate,endDate,"الفاتحة", 1,1);
                        Toast.makeText(Khatam.this, "Your schedule has been created", Toast.LENGTH_SHORT).show();
                        ArrayList<Object> attrs = db.getAllAttr();
                        System.out.println(attrs.get(0)+" "+attrs.get(1)+" "+attrs.get(2)+" "+attrs.get(3)+" "+attrs.get(4)
                                +" "+attrs.get(5)+" "+attrs.get(6));
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
                cal.add(Calendar.DATE, daysToFinish);
                String endDate = dateFormat.format(cal.getTime());

                boolean d = db.insertInto1(1,dailyPages,startDate,endDate,"الفاتحة", 1,1);
                ArrayList<Object> attrs = db.getAllAttr();
                System.out.println(attrs.get(0)+" "+attrs.get(1)+" "+attrs.get(2)+" "+attrs.get(3)+" "+attrs.get(4)
                        +" "+attrs.get(5)+" "+attrs.get(6));
                Toast.makeText(Khatam.this, "Your schedule has been created", Toast.LENGTH_SHORT).show();
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
}
