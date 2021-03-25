package kau.edu.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.RadioButton;

public class ManageKhatam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_khatam);
        CalendarView calendarView=findViewById(R.id.calendarView);
        NumberPicker numberPicker= (NumberPicker) findViewById(R.id.num);


















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
    }
}