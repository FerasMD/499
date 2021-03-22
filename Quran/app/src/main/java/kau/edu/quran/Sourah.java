package kau.edu.quran;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Sourah {
    public String sname,id,place,ver,wrd,let;

    public String displayDetails(NodeList nodeList, String text){

        for (int itr = 0; itr < nodeList.getLength(); itr++) {
            Node node = nodeList.item(itr);
//System.out.println("\nNode Name :" + node.getNodeName());

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                if (eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent().equalsIgnoreCase(text)) {
                    sname=(eElement.getElementsByTagName("sora_name_ar").item(0).getTextContent());
                    //  System.out.println( eElement.getElementsByTagName("aya_text").item(0).getTextContent());
                    place =eElement.getElementsByTagName("place").item(0).getTextContent()+" ";
                    ver =eElement.getElementsByTagName("ayas").item(0).getTextContent()+" ";
                    wrd =eElement.getElementsByTagName("words").item(0).getTextContent()+" ";
                    let =eElement.getElementsByTagName("letters").item(0).getTextContent()+" ";
                    //ayas.add(eElement.getElementsByTagName("place").item(0).getTextContent()+" ");
                    //  aya+=eElement.getElementsByTagName("aya_text").item(0).getTextContent()+" ";
                    //System.out.println(aya);

                }
            }
        }

        String About="عدد الآيات:"+ver+"\n"+"عدد الكلمات:"+wrd+"\n"+"عدد الحروف:"+let+"\n"+"مكان النزول:"+place;

        return About; }

}