package kau.edu.quran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.PhoneNumberUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;
import com.travijuu.numberpicker.library.NumberPicker;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.net.URLEncoder;

public class Settings extends AppCompatActivity {
Boolean sw=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        Context c=this;
        BottomNavigationView bottomNavigationView=findViewById(R.id.bot);
        bottomNavigationView.setSelectedItemId(R.id.about);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.das:

                        startActivity(new Intent(getApplicationContext(),Khatam.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.about:

                        return true;

                }
                return false;
            }
        });


        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.number_picker_default);
        Switch aSwitch=(Switch) findViewById(R.id.switch1);
        TextView textView=findViewById(R.id.textView5);
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(c);

        ChangeTheme(textView, aSwitch,  sharedPreferences);
        changeTextSize(textView,numberPicker,sharedPreferences);

textView.setMovementMethod(new ScrollingMovementMethod());




        Button button=findViewById(R.id.feed);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] colors = {"Whatsapp", "Email"};

                AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                builder.setTitle("Pick a platform to send your feedback");
                builder.setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]
                        if (which==0){
                            //try {
                                Intent sendIntent = new Intent();
                                sendIntent.setAction(Intent.ACTION_VIEW);
                                String url = "https://api.whatsapp.com/send?phone=" + "+966558111297" + "&text=" + "";
                                sendIntent.setData(Uri.parse(url));
                                startActivity(sendIntent);

                        }else{
                            startActivity(new Intent(getApplicationContext(),FeedBack.class));
                        }


                    }
                });
                builder.show();



            }
        });
        Button bt4=findViewById(R.id.button4);
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(c);
               SharedPreferences.Editor editor=sharedPreferences.edit();
               if (aSwitch.isChecked()) {
                   editor.putString("back", "black");
                   editor.putString("size",numberPicker.getValue()+"");
                   editor.commit();
               }else{
                   editor.putString("back", "white");
                   editor.putString("size",numberPicker.getValue()+"");
                   editor.commit();
               }

            }
        });




    }


   public void ChangeTheme(TextView textView,Switch aSwitch, SharedPreferences sharedPreferences){

       String back =sharedPreferences.getString("back","white");
       if (back.equalsIgnoreCase("black")){
aSwitch.setChecked(true);
            textView.setBackgroundResource(R.color.black);
            textView.setTextColor(Color.WHITE);
        }
       aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @SuppressLint("ResourceType")
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if (b==true){
                   sw=true;
                   textView.setBackgroundResource(R.color.black);
                   textView.setTextColor(Color.WHITE);
               }else {
                   sw=false;
                   textView.setBackgroundResource(R.color.white);
                   textView.setTextColor(Color.BLACK);
               }
           }
       });




   }


    public void changeTextSize( TextView textView,NumberPicker numberPicker, SharedPreferences sharedPreferences){
        String Size=sharedPreferences.getString("size","28");

        int si=Integer.parseInt(Size);


        numberPicker.setMax(80);
        numberPicker.setMin(18);
        numberPicker.setUnit(2);
        numberPicker.setValue(si);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,si);
        numberPicker.setValueChangedListener(new ValueChangedListener() {
            @Override
            public void valueChanged(int value, ActionEnum action) {
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,value);
                System.out.println(value);
            }
        });


    }


}