package kau.edu.quran;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class trying extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trying);
        TextView textView=findViewById(R.id.textView4);
        InputStream is = null;
        try {
            is = getAssets().open("hafs_v14.xml");
            System.out.println();
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
        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

        NodeList nodeList = doc.getElementsByTagName("ROW");
        System.out.println(nodeList.getLength());
// nodeList is not iterable, so we are using for loop
        ArrayList<String> names=new ArrayList<>();
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
        String sn="";
        for (int j = 0; j < names.size(); j++) {
            sn+="\n"+names.get(j);

     //  textView.setText(j+" "+ names.get(j));

        }

        ListView listView=(ListView)findViewById(R.id.lis) ;
        listView.setAdapter(new list(this, R.layout.list, names));


      //  textView.setText(sn);
    }
}