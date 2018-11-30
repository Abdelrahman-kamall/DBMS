package eg.edu.alexu.csd.oop.db.cs04.XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.Collections;

public class selectTable {
    public static Object[][] selectCols(String path, String name, String[] cols, Object[][] condition) {
        int no = 0;
        ArrayList<ArrayList<String>> table = new ArrayList<>();
        String[][] array = new String[0][0];
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(path);
            doc.normalize();
            String p = path;
            p = path.replace(".xml",".dtd");
            if(validateCols.validate(cols,p)) {
                //Content to be shown
                table = new ArrayList<>();
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
                        if(selectWithCondition(row, r, condition, table)){
                            no++;
                        }
                    } else {
                        selectWithoutCondition(row, r, condition, table);
                        no++;
                    }
                }
            }
            System.out.println();
            array = new String[table.size()][];
            for (int i = 0; i < table.size(); i++) {
                ArrayList<String> row = table.get(i);
                array[i] = row.toArray(new String[row.size()]);
            }
            return array;
        } catch (Exception e) {
            e.printStackTrace();
            return array;
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

    private static boolean selectWithCondition(Node nNode, Element col, Object[][] condition, ArrayList<ArrayList<String>> table) {
        // TODO Auto-generated method stub
        // Check select's condition.
        if (condition[0][0] != null) {
            String value = col.getElementsByTagName(condition[0][0].toString()).item(0).getTextContent();
            String v2 = condition[0][1].toString();
            System.out.println(value.compareTo(v2));
            if (value.equals(v2)) {
                selectWithoutCondition(nNode, col, condition, table);
                return true;
            }
            return false;
        } else if (condition[1][0] != null) {
            String value = col.getElementsByTagName(condition[1][0].toString()).item(0).getTextContent();
            String v2 = condition[1][1].toString();
            System.out.println(value.compareTo(v2));
            if (value.compareTo(v2) > 0 || value.length()>v2.length()) {
                selectWithoutCondition(nNode, col, condition, table);
                return true;
            }
            return false;
        } else if (condition[2][0] != null) {
            String value = col.getElementsByTagName(condition[2][0].toString()).item(0).getTextContent();
            String v2 = condition[2][1].toString();
            System.out.println(value.compareTo(v2));
            if (value.compareTo(v2) < 0 || value.length()<v2.length()) {
                selectWithoutCondition(nNode, col, condition, table);
                return true;
            }
            return false;
        }
        return false;
    }

    private void selectedCols() {

    }
}
