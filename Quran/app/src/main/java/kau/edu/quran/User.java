package kau.edu.quran;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.ArrayList;

public class User {



public ArrayList<String> Select_Sourah(NodeList nodeList){

    ArrayList<String> names=new ArrayList<>();
    for (int itr = 0; itr < nodeList.getLength(); itr++)
    {
        Node node = nodeList.item(itr);
        if (node.getNodeType() == Node.ELEMENT_NODE)
        {
            Element eElement = (Element) node;



                if (!names.contains(eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent())) {
                    //System.out.println(names.size());
                    names.add(eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent());

                }



        }

    }

    return names;

}



}
