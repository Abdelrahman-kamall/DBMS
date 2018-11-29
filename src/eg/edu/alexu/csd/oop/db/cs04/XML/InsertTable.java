package eg.edu.alexu.csd.oop.db.cs04.XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.events.DTD;
import java.util.ArrayList;

public class InsertTable {
    public static void insertRows(String path, String name, String[][] cols) {

        if(!validateCols.validate(cols[0],path)){
            
        }
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(path);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
