package eg.edu.alexu.csd.oop.db.cs04.XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.events.DTD;
import java.util.ArrayList;
import java.util.Arrays;

public class InsertTable {
    public static int insertRows(String path, String name, String[][] cols) {
int no=0;
        if (!validateCols.validate(cols[0], path)) {
            return 0;
        }
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(path);
            String p = path.replace(".xml", ".dtd");
            String[] columns = DTDGenerator.getDTDColumns(p);
            Element root = doc.getDocumentElement();
            Element row = doc.createElement("row");
            root.appendChild(row);
            if (cols[0].length < columns.length) {
                for (int i = 0; i < columns.length; i++) {
                    if (Arrays.binarySearch(cols[0], columns[i]) >= 0) {
                        Element temp = doc.createElement(columns[i]);
                        temp.setTextContent(cols[1][i]);
                        row.appendChild(temp);
                    } else {
                        Element temp = doc.createElement(columns[i]);
                        temp.setTextContent(null);
                        row.appendChild(temp);
                    }
                }
            } else {
                for (int i = 0; i < columns.length; i++) {
                    Element temp = doc.createElement(columns[i]);
                    temp.setTextContent(cols[1][i]);
                    row.appendChild(temp);
                }
            }
            no=root.getElementsByTagName("row").getLength();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return no;

    }
}
