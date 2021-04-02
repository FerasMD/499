package kau.edu.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FeedBack extends AppCompatActivity {
    private EditText to,sub,msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

      //  to=findViewById(R.id.edit_to);
        sub=findViewById(R.id.edit_sub);
        msg=findViewById(R.id.edit_msg);

        Button send=findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail();
            }
        });

    }




    private void mail(){

        String subj=sub.getText().toString();
        String msgg=msg.getText().toString();
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"ahmed5163@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT,subj);
        intent.putExtra(Intent.EXTRA_TEXT,msgg);


        try {
            startActivity(Intent.createChooser(intent, "Send mail by:"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(FeedBack.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}