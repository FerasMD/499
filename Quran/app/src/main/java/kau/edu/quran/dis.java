package kau.edu.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class dis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis);
        TextView t=(TextView)findViewById(R.id.textView);
        System.out.println("actvity222222222222222222222");
        String aya=getIntent().getStringExtra("aya");

        t.setText(aya);


        Button bt2=(Button)findViewById(R.id.button2);
        Button bt=(Button)findViewById(R.id.button);

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setTextSize(0, t.getTextSize() + 2.0f);
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.setTextSize(0, t.getTextSize() - 2.0f);
            }
        });
    }
}