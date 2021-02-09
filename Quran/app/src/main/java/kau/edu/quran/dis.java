package kau.edu.quran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class dis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis);
        ArrayList<String> aya= (ArrayList<String>) getIntent().getSerializableExtra("aya");

        String ve="";
        for (int i=0;i<aya.size();i++){

            ve+=aya.get(i);
        }
       /* TextView t=(TextView)findViewById(R.id.textView);
        System.out.println("actvity222222222222222222222");

        t.setText(ve);
*/
        SpannableString [] ss = new SpannableString[aya.size()];
        for (int i=0;i<aya.size();i++){
            System.out.println(i);
            ss[i]= SpannableString.valueOf(aya.get(i));
        }

        ClickableSpan [] clickableSpan = new ClickableSpan[ss.length];
        for (int i=0;i<clickableSpan.length;i++){
            System.out.println("clickable span: "+i);
            int finalI = i;
            clickableSpan[i]= new ClickableSpan() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View textView) {
                    // startActivity(new Intent(MyActivity.this, NextActivity.class));
                    Toast.makeText(dis.this,"aya: "+ (finalI+1), Toast.LENGTH_SHORT).show();
                }
                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(true);
                }
            };
        }


       /* ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
               // startActivity(new Intent(MyActivity.this, NextActivity.class));
                Toast.makeText(dis.this, "hi", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        */

        TextView textView = (TextView) findViewById(R.id.textView);
for (int i=0;i<ss.length;i++){
    System.out.println("ss len"+i);
    System.out.println("ss i:"+ss[i]);
    ss[i].setSpan(clickableSpan[i], ss[i].length()-2, ss[i].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    textView.append(ss[i]);

}




        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(Color.TRANSPARENT);
        Button bt2=(Button)findViewById(R.id.button2);
        Button bt=(Button)findViewById(R.id.button);

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setTextSize(0, textView.getTextSize() + 8.0f);
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setTextSize(0, textView.getTextSize() - 8.0f);
            }
        });
    }
}