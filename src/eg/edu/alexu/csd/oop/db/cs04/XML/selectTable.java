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
    public static void selectCols(String path, String name, String[] cols, Object[][] condition) {

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
            for (int i = 0; i < rows.getLength(); i++) {
                Node row = rows.item(i);
                Element r = ((Element) row);
                if (condition != null) {
                    selectWithCondition(row, r, condition, table);
                } else {
                    selectWithoutCondition(row, r, condition, table);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void selectWithoutCondition(Node nNode, Element col, Object[][] condition, ArrayList<ArrayList<String>> table) {
        // TODO Auto-generated method stub
        // select parent of col.
        ArrayList<String> temp = new ArrayList<>();
        for (String s : table.get(0)) {
            temp.add(col.getElementsByTagName(s).item(0).getTextContent());
        }
        table.add(temp);
    }

    private static void selectWithCondition(Node nNode, Element col, Object[][] condition, ArrayList<ArrayList<String>> table) {
        // TODO Auto-generated method stub
        // Check select's condition.
        if(condition[0][0] != null) {
            if (col.getElementsByTagName(condition[0][0].toString()).item(0).getTextContent()
                    .equals(condition[0][1].toString())) {
                // delete parent of col.
                selectWithoutCondition(nNode, col, condition, table);
            }
        }else if(condition[1][0] != null) {
            if (col.getElementsByTagName(condition[1][0].toString()).item(0).getTextContent()
                    .compareTo(condition[1][1].toString()) > 0) {
                selectWithoutCondition(nNode, col, condition, table);
            }
        }else if(condition[2][0] != null) {
            if (col.getElementsByTagName(condition[2][0].toString()).item(0).getTextContent()
                    .compareTo(condition[2][1].toString()) < 0) {
                selectWithoutCondition(nNode, col, condition, table);
            }
        }
    }

    private void selectedCols() {

    }
}
