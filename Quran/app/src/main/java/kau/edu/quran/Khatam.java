package kau.edu.quran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;
import com.travijuu.numberpicker.library.NumberPicker;
import android.widget.RadioButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Khatam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khatam);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bot);
        bottomNavigationView.setSelectedItemId(R.id.das);

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
                CalendarView calendarView=findViewById(R.id.calendarView);
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
        NumberPicker numberPicker= (NumberPicker) findViewById(R.id.num);
        if (b){
            numberPicker.setVisibility(View.VISIBLE);

        }else{
            numberPicker.setVisibility(View.GONE);
        }
    }
});

    }
}