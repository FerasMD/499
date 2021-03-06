package kau.edu.quran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
DB db = new DB(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bot);
        bottomNavigationView.setSelectedItemId(R.id.das);

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
        try {
            parseXML();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }
   /* private void parseXML() throws ParserConfigurationException, SAXException, IOException {
        InputStream is = getAssets().open("hafs_v14.xml");
//an instance of factory that gives a document builder
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//an instance of builder to parse the specified xml file
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(is);
        doc.getDocumentElement().normalize();
        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

        NodeList nodeList = doc.getElementsByTagName("ROW");
        System.out.println(nodeList.getLength());
// nodeList is not iterable, so we are using for loop
        ArrayList<String>names=new ArrayList<>();
        for (int itr = 0; itr < nodeList.getLength(); itr++)
        {
            Node node = nodeList.item(itr);
//System.out.println("\nNode Name :" + node.getNodeName());

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) node;
                if (!eElement.getElementsByTagName("id").item(0).getTextContent().equalsIgnoreCase("115")) {


                    if (!names.contains(eElement.getElementsByTagName("sora_name_en").item(0).getTextContent())) {
                        //System.out.println(names.size());
                        names.add(eElement.getElementsByTagName("sora_name_en").item(0).getTextContent());
                    }

                    // System.exit(0);
                }

//System.out.println("First Name: "+ eElement.getElementsByTagName("firstname").item(0).getTextContent());
//System.out.println("Last Name: "+ eElement.getElementsByTagName("lastname").item(0).getTextContent());
//System.out.println("Subject: "+ eElement.getElementsByTagName("subject").item(0).getTextContent());
//System.out.println("Marks: "+ eElement.getElementsByTagName("marks").item(0).getTextContent());

            }

        }
    }*/
   private void parseXML() throws ParserConfigurationException, SAXException {
        XmlPullParserFactory parseFactory;
        try{
            parseFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parseFactory.newPullParser();
            InputStream is = getAssets().open("hafs_v14.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null);
            proessParsing(parser);
        }
        catch (XmlPullParserException e){
        }
        catch (IOException e){
        }
    }
    public void proessParsing(XmlPullParser parser) throws IOException, XmlPullParserException, ParserConfigurationException, SAXException {

        ArrayList<Sourah> sourahs=new ArrayList<>();
        int evenType=parser.getEventType();
        Sourah sourah=null;
        int i=1;
        while (evenType!=XmlPullParser.END_DOCUMENT){

            String id;
            String eltname=null;
            String str="";
            switch (evenType){
                case XmlPullParser.START_TAG:
                    eltname=parser.getName();

                    if ("ROW".equals(eltname)) {

                     //   System.out.println( parser.getAttributeCount());
                        sourah = new Sourah();
                        sourahs.add(sourah);


                    }

                    else if(sourah!=null){
                        if (("id".equals(eltname))){
                            id=parser.nextText();
                          //  System.out.println("ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"+parser.nextText());

                           // System.out.println(id);
                        }


                         if ("sora_name_ar".equals(eltname)){

    sourah.name=parser.nextText();

                         }

                        }

                    break;

            }

            evenType=parser.next();
        }

printPlayers(sourahs);
    }
    private void printPlayers(ArrayList<Sourah> sourahs) throws IOException, SAXException, ParserConfigurationException {
        StringBuilder builder =new StringBuilder();


        ArrayList<String>s=new ArrayList<>();
        for (Sourah sourah:sourahs){
           // System.out.println(sourah.name);
            builder.append(sourah.name);
if (!s.contains(sourah.name)) {
    s.add(sourah.name);


}

        }
        ListView listView=(ListView)findViewById(R.id.listview) ;
        listView.setAdapter(new list(this, R.layout.list, s));



        InputStream is = getAssets().open("hafs_v14.xml");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//an instance of builder to parse the specified xml file
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(is);
        doc.getDocumentElement().normalize();
       // System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

        NodeList nodeList = doc.getElementsByTagName("ROW");
       // System.out.println(nodeList.getLength());
// nodeList is not iterable, so we are using for loop
        ArrayList<String>names=new ArrayList<>();

        for (int itr = 0; itr < nodeList.getLength(); itr++)
        {
            Node node = nodeList.item(itr);
//System.out.println("\nNode Name :" + node.getNodeName());

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) node;
                if (!eElement.getElementsByTagName("id").item(0).getTextContent().equalsIgnoreCase("115")) {


                    if (!names.contains(eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent())) {
                        //System.out.println(names.size());
                        names.add(eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent());
                    }

                    // System.exit(0);
                }

//System.out.println("First Name: "+ eElement.getElementsByTagName("firstname").item(0).getTextContent());
//System.out.println("Last Name: "+ eElement.getElementsByTagName("lastname").item(0).getTextContent());
//System.out.println("Subject: "+ eElement.getElementsByTagName("subject").item(0).getTextContent());
//System.out.println("Marks: "+ eElement.getElementsByTagName("marks").item(0).getTextContent());

            }

        }


/*for (int i=0;i<names.size();i++){
    System.out.println("My loop"+names.get(i));
}
*/


listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(MainActivity.this, names.get(i), Toast.LENGTH_SHORT).show();
        String aya="";
        int ii=0;
        ArrayList<String>ayas=new ArrayList<>();
        String sname="";
        for (int itr = 0; itr < nodeList.getLength(); itr++)
        {
            Node node = nodeList.item(itr);
//System.out.println("\nNode Name :" + node.getNodeName());

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element eElement = (Element) node;

                if (eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent().equalsIgnoreCase(names.get(i))) {
                    ii++;
sname=(eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent());
                  //  System.out.println( eElement.getElementsByTagName("aya_text").item(0).getTextContent());
                    ayas.add(eElement.getElementsByTagName("aya_text").item(0).getTextContent()+" ");
                  //  aya+=eElement.getElementsByTagName("aya_text").item(0).getTextContent()+" ";
                    //System.out.println(aya);

                }

            }
        }

        System.out.println(ii);
        Intent intent=new Intent(getApplicationContext(),dis.class);
        intent.putExtra("aya",ayas);
        intent.putExtra("sname",sname);

        startActivity(intent);

    }
});






        //  txt.setText(builder.toString());
    }
}
