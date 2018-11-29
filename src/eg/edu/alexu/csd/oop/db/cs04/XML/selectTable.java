package eg.edu.alexu.csd.oop.db.cs04.XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;

public class selectTable {
    public static void selectCols(String path,String name,String[] cols,Object[][] condition){

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(path);

            //Content to be shown
            ArrayList<ArrayList<String>> table = new ArrayList<>();
            ArrayList<String> temp = new ArrayList<>();
            for (String col : cols) {
                temp.add(col);
            }
            table.add(temp);
            // Get the root element
            NodeList rows = doc.getElementsByTagName("row");
            if(((int) condition[0][0])!=0){
                NodeList c = doc.getElementsByTagName(((String) condition[0][0]));
                for (int i = 0; i < c.getLength(); i++) {
                    Node n = rows.item(i);
                    if(((Element) n).getTextContent().equals((condition[0][1]))){
                        NodeList x = n.getParentNode().getChildNodes();
                        temp = new ArrayList<>();
                        for(int ii=0;ii<x.getLength();ii++){
                            Element e = ((Element) x.item(ii));
                            if(table.get(0).indexOf(e.getTagName())>=0){
                                temp.add(e.getTextContent());
                            }
                        }
                        table.add(temp);
                    }
                }
            }else if(((int) condition[1][0])!=0){
                NodeList c = doc.getElementsByTagName(((String) condition[0][0]));
                for (int i = 0; i < c.getLength(); i++) {
                    Node n = rows.item(i);
                    if(((Element) n).getTextContent().equals((condition[0][1]))){
                        NodeList x = n.getParentNode().getChildNodes();
                        temp = new ArrayList<>();
                        for(int ii=0;ii<x.getLength();ii++){
                            Element e = ((Element) x.item(ii));
                            if(table.get(0).indexOf(e.getTagName())>=0){
                                temp.add(e.getTextContent());
                            }
                        }
                        table.add(temp);
                    }
                }
            }else if(((int) condition[2][0])!=0){
                NodeList c = doc.getElementsByTagName(((String) condition[0][0]));
                for (int i = 0; i < c.getLength(); i++) {
                    Node n = rows.item(i);
                    if(((Element) n).getTextContent().equals((condition[0][1]))){
                        NodeList x = n.getParentNode().getChildNodes();
                        temp = new ArrayList<>();
                        for(int ii=0;ii<x.getLength();ii++){
                            Element e = ((Element) x.item(ii));
                            if(table.get(0).indexOf(e.getTagName())>=0){
                                temp.add(e.getTextContent());
                            }
                        }
                        table.add(temp);
                    }
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
