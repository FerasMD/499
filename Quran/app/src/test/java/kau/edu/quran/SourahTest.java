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

public class SourahTest extends TestCase {

    public void testDisplayDetails() {

        File is;
        is = new File("C:\\Users\\ahmed\\OneDrive\\Documents\\GitHub\\499\\Quran\\app\\src\\main\\assets\\sourah_Info.xml");
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

        Sourah sourah=new Sourah();
        String s=sourah.displayDetails(nodeList,"الفَاتِحة ").get(1);
        System.out.println(s);

        assertEquals("سورةُ الفاتحةِ هي أعظَمُ سُورةٍ في القرآن الكريم، بل هي أعظم سورة أنزلها الله في جميع كتبه التي أنزلها على أنبيائه ورسله، ولهذه السورة من الفضائل ما ليس لغيرها من سُوَرِ القرآن، وقد اشتمَلَت على مُجمَل معاني القرآنِ الكريم، وأصولِه التي فصَّلَها القرآنُ تفصيلًا، وهي أكثر سورة يحتاج إليها المسلم في اليوم والليلة؛ ففي الفرائض نقرؤها سبع عشرة مرة، وفي السُّنَن أكثرَ من ذلك، وفي النوافل أكثرَ وأكثر. ",s);
    }
}