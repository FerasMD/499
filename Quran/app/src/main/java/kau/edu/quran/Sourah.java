package kau.edu.quran;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

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

public class Sourah {
    public String sname,id,place,ver,wrd,let,resN;



    public NodeList retriving_file(Activity assetsProvider){
        InputStream is = null;
        try {
            is = assetsProvider.getAssets().open("hafs_v14.xml");
            // System.out.println();
        } catch (IOException e) {
            Toast.makeText(assetsProvider.getApplicationContext(), "عذرا حدث خطا في التحميل ", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

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


        return nodeList;


    }

    public ArrayList<String> displayDetails(NodeList nodeList, String Sourah){
ArrayList<String> About=new ArrayList<>();

        for (int itr = 0; itr < nodeList.getLength(); itr++) {
            Node node = nodeList.item(itr);


            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                if (eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent().equalsIgnoreCase(Sourah)) {
                    sname=(eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent());
                    //  System.out.println( eElement.getElementsByTagName("aya_text").item(0).getTextContent());
                    place =eElement.getElementsByTagName("place").item(0).getTextContent()+" ";
                    ver =eElement.getElementsByTagName("ayas").item(0).getTextContent()+" ";
                    wrd =eElement.getElementsByTagName("words").item(0).getTextContent()+" ";
                    let =eElement.getElementsByTagName("letters").item(0).getTextContent()+" ";
                    resN=eElement.getElementsByTagName("reason_n").item(0).getTextContent()+" ";


                }
            }
        }

         About.add("عدد الآيات:"+ver+"\n"+"عدد الكلمات:"+wrd+"\n"+"عدد الحروف:"+let+"\n"+"مكان النزول:"+place);
        About.add(resN);

        return About; }




    public Intent getSourah(NodeList nodeList, String SourahName, Context context,String s){

        ArrayList<String> ayas=new ArrayList<>();
        ArrayList<String>ayaID=new ArrayList<>();
        String sname="";
       // ArrayList<String> names=new ArrayList<>();
        for (int itr = 0; itr < nodeList.getLength(); itr++)
        {
            Node node = nodeList.item(itr);
//System.out.println("\nNode Name :" + node.getNodeName());

            if (node.getNodeType() == Node.ELEMENT_NODE)
            {

                Element eElement = (Element) node;

                if (eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent().equalsIgnoreCase(SourahName)) {

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


                    ayas.add(q+" ");
                    ayaID.add(eElement.getElementsByTagName("id").item(0).getTextContent());

                    nummm++;
                }
            }

        }





        Intent intent=new Intent(context, DisplaySourah.class);
        intent.putExtra("aya",ayas);
        intent.putExtra("sname",sname);
        intent.putExtra("id",ayaID);
        intent.putExtra("curA",s);

      return intent;


    }



}