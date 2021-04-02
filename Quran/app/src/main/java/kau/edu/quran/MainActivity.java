package kau.edu.quran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bot);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:

                        return true;
                    case R.id.das:

                        startActivity(new Intent(getApplicationContext(),Khatam.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(),Settings.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

        Button button=findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),trying.class));
            }
        });
       // txt=(TextView)findViewById(R.id.txt);

        Sourah sourah=new Sourah();
      NodeList nodeList=  sourah.retriving_file(this);









        /*
        // System.out.println("Root element: " + doc.getDocumentElement().getNodeName());


        // System.out.println(nodeList.getLength());


        System.out.println("node list is+ "+nodeList.getLength());
// nodeList is not iterable, so we are using for loop
        ArrayList<String> names=new ArrayList<>();
        for (int itr = 0; itr < nodeList.getLength(); itr++)
        {


            Node node = nodeList.item(itr);
//System.out.println("\nNode Name :" + node.getNodeName());

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) node;



                    if (!names.contains(eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent())) {
                        //System.out.println(names.size());
                        names.add(eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent());
                    }

                    // System.exit(0);


//System.out.println("First Name: "+ eElement.getElementsByTagName("firstname").item(0).getTextContent());
//System.out.println("Last Name: "+ eElement.getElementsByTagName("lastname").item(0).getTextContent());
//System.out.println("Subject: "+ eElement.getElementsByTagName("subject").item(0).getTextContent());
//System.out.println("Marks: "+ eElement.getElementsByTagName("marks").item(0).getTextContent());

            }



        }
*/
User user=new User();
ArrayList<String> names=   user.Select_Sourah(nodeList);


      final   ListView listView=(ListView)findViewById(R.id.listview) ;
        listView.setAdapter(new list(this, R.layout.list, names));

listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
       /* Toast.makeText(MainActivity.this, names.get(i), Toast.LENGTH_SHORT).show();
        String aya="";
        int ii=0;
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

                if (eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent().equalsIgnoreCase(names.get(i))) {
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

                    ii++;
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

        System.out.println(ii);
        Intent intent=new Intent(getApplicationContext(),dis.class);
        intent.putExtra("aya",ayas);
        intent.putExtra("sname",sname);
        intent.putExtra("id",ayaID);
       // intent.putExtra("c","main");

        startActivity(intent);
*/

        startActivity(sourah.getSourah(nodeList, names.get(i),getApplicationContext(),new String(""))) ;
    }
});
        //  txt.setText(builder.toString());
    }

}
