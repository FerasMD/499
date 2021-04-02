package kau.edu.quran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ManageKhatam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_manage_khatam);
        CalendarView calendarView=findViewById(R.id.calendarView);
        NumberPicker numberPicker= (NumberPicker) findViewById(R.id.num);
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
        DB db = new DB (this);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        TextView t1 = findViewById(R.id.curSo);
        TextView t2 = findViewById(R.id.curSo2);
        TextView t3 = findViewById(R.id.curSo3);
        TextView t4 = findViewById(R.id.curSo5);
        TextView t5= findViewById(R.id.curSo4);
        String s1 = (String) t1.getText();
        String s2 = (String) t2.getText();
        String s3 = (String) t3.getText();
        String s4 = (String) t4.getText();
        String s5 = (String) t5.getText();

        ArrayList<Object> attrs = db.getAllAttr();
        t1.setText("السورة الحالية: "+attrs.get(4));
        t2.setText(t2.getText()+" "+attrs.get(6));
        t3.setText(t3.getText()+" "+attrs.get(5));
        t4.setText(t4.getText()+" "+attrs.get(1)+" صفحة");
        t5.setText(t5.getText()+" "+attrs.get(3));

        numberPicker.setValue((Integer) attrs.get(1));
        numberPicker.setMaxValue(200);
        numberPicker.setMinValue(1);

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

        final String[] sss = {""};
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                sss[0] =i2+"/"+(i1+1)+"/"+i;
                System.out.println(sss[0] );
            }
        });

        Button b1 = findViewById(R.id.update);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioButton.isChecked()){
                    String newEndDate = sss[0];
                    int id = (int) attrs.get(0);
                    int dailyPages = (int) attrs.get(1);
                    String startDate = (String) attrs.get(2);
                    String endDate = (String) attrs.get(3);
                    String currentSourah = (String) attrs.get(4);
                    int currentVerse = (int) attrs.get(5);
                    int currentPage = (int) attrs.get(6);
                    String currentDate = dateFormat.format(cal.getTime());
                    long daysDiff = getDateDiff(dateFormat,currentDate,newEndDate);
                    if(daysDiff>0){
                        int remainingPages = 604-currentPage;
                        dailyPages = (int) (remainingPages/daysDiff);
                        db.update("1",dailyPages,newEndDate,currentSourah,currentVerse,currentPage);
                        ArrayList<Object>attr=db.getAllAttr();
                        System.out.println(attr.get(0) + " " + attr.get(1) + " " + attr.get(2) + " " + attr.get(3) + " " + attr.get(4)
                                + " " + attr.get(5) + " " + attr.get(6));
                        t1.setText(s1+" "+attr.get(4));
                        t2.setText(s2+" "+attr.get(6));
                        t3.setText(s3+" "+attr.get(5));
                        t4.setText(s4+" "+attr.get(1));
                        t5.setText(s5+" "+attr.get(3));
                    }else
                        Toast.makeText(ManageKhatam.this, "لا تستطيع اختيار نفس اليوم", Toast.LENGTH_SHORT).show();


                }else {
                    int id = (int) attrs.get(0);
                    int dailyPages = numberPicker.getValue();
                    String startDate = (String) attrs.get(2);
                    String endDate = (String) attrs.get(3);
                    String currentSourah = (String) attrs.get(4);
                    int currentVerse = (int) attrs.get(5);
                    int currentPage = (int) attrs.get(6);
                    int remainingPages = 604-currentPage;
                    int daysToFinish = remainingPages/numberPicker.getValue();
                    Date date=  cal.getTime();
                    cal.add(Calendar.DATE, daysToFinish);

                    String newEndDate = dateFormat.format(cal.getTime());
                    cal.setTime(date);
                    db.update("1",dailyPages,newEndDate,currentSourah,currentVerse,currentPage);
                    ArrayList<Object>attr=db.getAllAttr();
                    t1.setText(s1+" "+attr.get(4));
                    t2.setText(s2+" "+attr.get(6));
                    t3.setText(s3+" "+attr.get(5));
                    t4.setText(s4+" "+attr.get(1));
                    t5.setText(s5+" "+attr.get(3));
                    System.out.println(attr.get(0) + " " + attr.get(1) + " " + attr.get(2) + " " + attr.get(3) + " " + attr.get(4)
                            + " " + attr.get(5) + " " + attr.get(6));
                }
            }
        });

        Button button=findViewById(R.id.delete);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num=  db.delete("1");
                System.out.println(num);
                startActivity(new Intent(getApplicationContext(), Khatam.class));

            }
        });


        Button button1=findViewById(R.id.read);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
Sourah sourah=new Sourah();
NodeList nodeList=sourah.retriving_file(ManageKhatam.this);



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
                // System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

                NodeList nodeList = doc.getElementsByTagName("ROW");
                */

                // System.out.println(nodeList.getLength());
// nodeList is not iterable, so we are using for loop
              /*
                ArrayList<String>ayas=new ArrayList<>();
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

                        if (eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent().equalsIgnoreCase(attrs.get(4).toString())) {
                            System.out.println("weogiuiuiuiuiuiuiuiuiuiuiuiuiuiupwoghwegwegwegwegwegwe");
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
                intent.putExtra("curA",attrs.get(5).toString());
               // intent.putExtra("c","man");
                startActivity(intent);
*/



startActivity(  sourah.getSourah(nodeList,attrs.get(4).toString(),getApplicationContext(),attrs.get(5).toString()));








            }
        });



/*
      button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             int pages=  Integer.parseInt(attrs.get(1).toString());

                System.out.println(pages);
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
                // System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

                NodeList nodeList = doc.getElementsByTagName("ROW");

                ArrayList<String> strings =new ArrayList<>();
                ArrayList<String>ayas=new ArrayList<>();
                ArrayList<String>ayaID=new ArrayList<>();
                String sname="";
                for (int itr = 0; itr < nodeList.getLength(); itr++)
                {
                    Node node = nodeList.item(itr);
//System.out.println("\nNode Name :" + node.getNodeName());

                    if (node.getNodeType() == Node.ELEMENT_NODE)
                    {
                        Element eElement = (Element) node;
                        int num=Integer.parseInt(eElement.getElementsByTagName("page").item(0).getTextContent());
                        if (num<=pages) {
                            ayaID.add(eElement.getElementsByTagName("id").item(0).getTextContent());
                            ArrayList<String>strings=new ArrayList<>();
                            if (!strings.contains(eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent())){
                                strings.add(eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent());

                            }*



                            String sssss=eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent();

                            if (!strings.contains(sssss)){
                                System.out.println("aaaaaaaaaaaaaaa");
                                ayas.add("\n\n"+sssss+"\n");
                                strings.add(sssss);
                            }




                            //   sname = (eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent());

                            //   System.out.println(eElement.getElementsByTagName("aya_text").item(0).getTextContent());

                            ayas.add(eElement.getElementsByTagName("aya_text").item(0).getTextContent() + " ");
                            //  aya+=eElement.getElementsByTagName("aya_text").item(0).getTextContent()+" ";
                            //System.out.println(aya);

                        }

                    }
                }


                for (int ii=0;ii<strings.size();ii++){

                    sname+=","+strings.get(ii);
                }
                Intent intent=new Intent(getApplicationContext(),dis.class);
                intent.putExtra("aya",ayas);
                System.out.println(sname);
                intent.putExtra("sname",sname);
                intent.putExtra("id",ayaID);
                startActivity(intent);














            }
        });


*/




    }

    public static long getDateDiff(SimpleDateFormat format, String oldDate, String newDate) {
        try {
            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
