package kau.edu.quran;

import junit.framework.TestCase;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class UserTest extends TestCase {

    public void testSelect_Sourah() {


        User user = new User();
        File is;
        is = new File("C:\\Users\\ahmed\\OneDrive\\Documents\\GitHub\\499\\Quran\\app\\src\\main\\assets\\hafs_v14.xml");
        if (is.exists()) {


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


        ArrayList arrayList = user.Select_Sourah(nodeList);
        System.out.println(arrayList.size());
        for (int i=0;i<arrayList.size();i++){
            System.out.print((i+1)+"-"+arrayList.get(i));
        }
        assertEquals(114, arrayList.size());

    }
}