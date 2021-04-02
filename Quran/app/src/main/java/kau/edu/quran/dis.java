package kau.edu.quran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static androidx.appcompat.app.AlertDialog.*;

public class dis extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis);
        View view=(View)findViewById(R.id.myV);
        DB a = new DB (this);





        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        Switch aSwitch=(Switch) findViewById(R.id.switch1);
        String back=sharedPreferences.getString("back","No");
        TextView tcurA=findViewById(R.id.curA);
        TextView la=findViewById(R.id.la);

        TextView textView = (TextView) findViewById(R.id.textView);
        TextView textView1=(TextView)findViewById(R.id.textView6);





if (back.equalsIgnoreCase("black")){
    aSwitch.setChecked(true);
    view.setBackgroundColor(Color.BLACK);
    textView.setTextColor(Color.WHITE);
    textView1.setTextColor(Color.WHITE);
    la.setTextColor(Color.WHITE);
    tcurA.setTextColor(Color.WHITE);
    aSwitch.setTextColor(Color.WHITE);
}
        int size=Integer.parseInt(sharedPreferences.getString("size","28"));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,size);
      //  System.out.println(size+"   "+back);
     //   ActionBar actionBar=(ActionBar)findViewById()


        ArrayList<String> aya= (ArrayList<String>) getIntent().getSerializableExtra("aya");
        ArrayList<String> ayaID= (ArrayList<String>) getIntent().getSerializableExtra("id");
        String sname=getIntent().getStringExtra("sname");

        String curA;
        try {
            curA= getIntent().getStringExtra("curA");


            ArrayList<Object> attrs = a.getAllAttr();

            String sourN=attrs.get(4).toString();
            int dpage=Integer.parseInt(attrs.get(1).toString());
            int cpage=Integer.parseInt(attrs.get(6).toString());
            int lastpage=dpage+cpage;
            if (lastpage<=604){
                Sourah sourah=new Sourah();
                NodeList nodeList=sourah.retriving_file(dis.this);



                for (int itr = 0; itr < nodeList.getLength(); itr++)
                {
                    Node node = nodeList.item(itr);
//System.out.println("\nNode Name :" + node.getNodeName());

                    if (node.getNodeType() == Node.ELEMENT_NODE)
                    {
                        Element eElement = (Element) node;
                        int num=Integer.parseInt(eElement.getElementsByTagName("page").item(0).getTextContent());
                        if (lastpage==num) {

                            String sur=eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent();

                            String lay=eElement.getElementsByTagName("aya_no").item(0).getTextContent();

                            la.setText("ينتهي وردك اليومي عند: "+sur+" أية: "+lay);
                            break;


                        }

                    }
                }





            }



            if (!curA.equalsIgnoreCase("")){

                tcurA.setText("الآية الحالية: "+sourN+"\n"+" أية: "+curA);
                tcurA.setVisibility(View.VISIBLE);
                la.setVisibility(View.VISIBLE);

            }


        }catch (NullPointerException e){


        }catch (IndexOutOfBoundsException indexOutOfBoundsException){

        }


        ActionBar actionBar=   getSupportActionBar();

        actionBar.hide();

        textView1.setText(sname);

        if(!textView1.getText().toString().equalsIgnoreCase("الفَاتِحة ")){

            textView.setText("بِسۡمِ ٱللَّهِ ٱلرَّحۡمَٰنِ ٱلرَّحِيم \n");

        }
       // ColorStateList col=textView.getTextColors();

Drawable drawable=view.getBackground();

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b==true){
                    view.setBackgroundColor(Color.BLACK);
                    textView.setTextColor(Color.WHITE);
                    textView1.setTextColor(Color.WHITE);
                    la.setTextColor(Color.WHITE);
                    tcurA.setTextColor(Color.WHITE);
                    aSwitch.setTextColor(Color.WHITE);
                }else{
                    textView.setTextColor(Color.BLACK);
                    textView1.setTextColor(Color.BLACK);
                    la.setTextColor(Color.BLACK);
                    aSwitch.setTextColor(Color.BLACK);
                    tcurA.setTextColor(Color.BLACK);
                    view.setBackgroundColor(getResources().getColor(android.R.color.white));
                }
            }
        });


       /* TextView t=(TextView)findViewById(R.id.textView);
        System.out.println("actvity222222222222222222222");

        t.setText(ve);
*/
        SpannableString [] ss = new SpannableString[aya.size()];
        for (int i=0;i<aya.size();i++){
         //   System.out.println(i);
            ss[i]= SpannableString.valueOf(aya.get(i));
        }

        ClickableSpan [] clickableSpan = new ClickableSpan[ss.length];
        for (int i=0;i<clickableSpan.length;i++){

          //  System.out.println("clickable span: "+i);
            int finalI = i;
            clickableSpan[i]= new ClickableSpan() {
                @SuppressLint("ResourceType")
                @Override
                public void onClick(View textView) {
                    // startActivity(new Intent(MyActivity.this, NextActivity.class));
                    if (a.isEmpty("1")){

                    }
                    else {
                    /* if ((finalI+1)==clickableSpan.length){
                         System.out.println("yes");
                         Builder builder = new Builder(dis.this);

                         builder.setTitle("الانتقال");
                         builder.setMessage("هل تريد الانتقال الى السورة التالية");
builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        DB db = new DB (getApplicationContext());
        ArrayList<Object> attrs = db.getAllAttr();

      nextSourah(textView1.getText().toString(),attrs.get(5).toString());
        dialogInterface.dismiss();

    }
});
                         builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {

                             @Override
                             public void onClick(DialogInterface dialog, int which) {

                                 // Do nothing
                                 dialog.dismiss();
                             }
                         });
                         AlertDialog alert = builder.create();
                         alert.show();


                     }
*/

                    Toast.makeText(dis.this,"aya: "+ (finalI+1), Toast.LENGTH_SHORT).show();
                //    System.out.println(ayaID.get(finalI));
                    Builder builder = new Builder(dis.this);

                    builder.setTitle("إنهاء الورد اليومي");
                    builder.setMessage("هل تريد التوقف عند هذه الآية");

                    builder.setPositiveButton("نعم", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing but close the dialog


                            Sourah sourah=new Sourah();
                           NodeList nodeList =sourah.retriving_file(dis.this);


/*
                            InputStream is = null;
                            try {
                                is = getAssets().open("hafs_v14.xml");
                                // System.out.println();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
//an instance of factory that gives a document builder
                            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//an instance of builder to parse the specified xml file
                            DocumentBuilder db = null;
                            try {
                                db = dbf.newDocumentBuilder();
                            } catch (ParserConfigurationException e) {
                                e.printStackTrace();
                            }
                            Document doc = null;
                            try {
                                doc = db.parse(is);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (SAXException e) {
                                e.printStackTrace();
                            }
                            doc.getDocumentElement().normalize();


                            NodeList nodeList = doc.getElementsByTagName("ROW");
                            */
                            for (int itr = 0; itr < nodeList.getLength(); itr++)
                            {
                                Node node = nodeList.item(itr);
//System.out.println("\nNode Name :" + node.getNodeName());

                                if (node.getNodeType() == Node.ELEMENT_NODE)
                                {
                                    Element eElement = (Element) node;
                                    int num=Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent());
                                    if (num==Integer.parseInt(ayaID.get(finalI))) {
                                        String updatedSourah =eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent();
                                        int updatedVerse =Integer.parseInt(eElement.getElementsByTagName("aya_no").item(0).getTextContent());
                                        int updatedPage =Integer.parseInt(eElement.getElementsByTagName("page").item(0).getTextContent());
                                      //  System.out.println(eElement.getElementsByTagName("aya_text").item(0).getTextContent());
                                        ArrayList<Object>attr=a.getAllAttr();
                                        int remainingPages = 604-updatedPage;
                                        int daysToFinish = remainingPages/(int)attr.get(1);
                                       // daysToFinish++;
                                        Calendar cal = Calendar.getInstance();
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                        Date date=  cal.getTime();
                                        cal.add(Calendar.DATE, daysToFinish);
                                        String newEndDate = dateFormat.format(cal.getTime());
                                        cal.setTime(date);
                                        a.update("1",(int)attr.get(1),newEndDate,updatedSourah,updatedVerse,updatedPage);
                                        //a.update("1",dailyPages,newEndDate,currentSourah,currentVerse,currentPage);
                                    }

                                }
                            }
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("لا", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Do nothing
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                }
                }
                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);

                    ds.setUnderlineText(false);
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


for (int i=0;i<ss.length;i++){
    //System.out.println("ss len"+i);
  //  System.out.println("ss i:"+ss[i]);

    if (i<9){
        ss[i].setSpan(clickableSpan[i], ss[i].length()-3, ss[i].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
    else{


    try {

        ss[i].setSpan(clickableSpan[i], ss[i].length()-4, ss[i].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }catch (Exception e ){
        ss[i].setSpan(clickableSpan[i], ss[i].length()-4, ss[i].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
    }
   // System.out.println("ss lengthhhhhhhhhhhhh"+ss.length);
    textView.append(ss[i]);
    //textView.append(ayaID.get(i-3));
    //System.out.println(ayaID.get(i-3));


}
Button button=findViewById(R.id.next);

button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        TextView te=findViewById(R.id.curA);

        if (!te.getText().toString().equalsIgnoreCase("")){
            DB db = new DB (getApplicationContext());
            ArrayList<Object> attrs = db.getAllAttr();

            nextSourah(textView1.getText().toString(),attrs.get(5).toString());

        }else

            {

            nextSourah(textView1.getText().toString(),"");

        }



    }
});
        //System.out.println(ss.length);
        //System.out.println(ayaID.size());



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
    } @Override
    public void onBackPressed() {

    Intent i=new Intent(dis.this,MainActivity.class);

    startActivity(i);
    finish();



    }

    public void nextSourah(String SourahName,String att){
Sourah sourah=new Sourah();

NodeList nodeList=sourah.retriving_file(this);

        int neW=0;
        ArrayList<String> ayas=new ArrayList<>();
        ArrayList<String>ayaID=new ArrayList<>();
        String sname="";
        ArrayList<String> names=new ArrayList<>();
        for (int itr = 0; itr < nodeList.getLength(); itr++)
        {
            Node node = nodeList.item(itr);
//System.out.println("\nNode Name :" + node.getNodeName());

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                //Element eElement = (Element) node;
                //  if (!eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent().equalsIgnoreCase(attrs.get(4).toString())) {
                Element eElement = (Element) node;

                if (eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent().equalsIgnoreCase(SourahName)) {
                    int page=Integer.parseInt(eElement.getElementsByTagName("sora_no").item(0).getTextContent());
                     neW=page+1;
                    System.out.println(neW);
                }
                    else if (Integer.parseInt(eElement.getElementsByTagName("sora_no").item(0).getTextContent())==neW){

                        System.out.println(eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent());



                    String q="";
                    if (Integer.parseInt(eElement.getElementsByTagName("aya_no").item(0).getTextContent())>9){
                        String a=eElement.getElementsByTagName("aya_text").item(0).getTextContent();

                        String c=a.substring(a.length()-3,a.length());
                        StringBuilder b =new StringBuilder();

                        b.append(c);

                        q=a.substring(0,a.length()-3)+""+b.reverse().toString();
                    }else {
                        q=eElement.getElementsByTagName("aya_text").item(0).getTextContent();
                    }



                    int nummm=0;
                    sname=(eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent());
                    //  System.out.println( eElement.getElementsByTagName("aya_text").item(0).getTextContent());

                    ayas.add(q+" ");
                    ayaID.add(eElement.getElementsByTagName("id").item(0).getTextContent());
                    //  aya+=eElement.getElementsByTagName("aya_text").item(0).getTextContent()+" ";
                    //System.out.println(aya);
                    nummm++;
                }

            }

        }





        Intent intent=new Intent(getApplicationContext(),dis.class);
        intent.putExtra("aya",ayas);
        intent.putExtra("sname",sname);
        intent.putExtra("id",ayaID);
        if (!att.equalsIgnoreCase("")){
            intent.putExtra("curA",att);
            // intent.putExtra("c","man");
            startActivity(intent);
        }else{
            startActivity(intent);

        }





    }
}
