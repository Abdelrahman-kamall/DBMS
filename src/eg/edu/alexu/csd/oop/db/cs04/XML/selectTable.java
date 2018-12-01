package eg.edu.alexu.csd.oop.db.cs04.XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;

public class selectTable {
    public static Object[][] selectCols(String path, String name, String[] cols, Object[][] condition) {
        int no = 0;
        ArrayList<ArrayList<Object>> table = new ArrayList<>();
        Object[][] array = new String[0][0];
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(path);
            doc.normalize();
            String p = path;
            p = path.replace(".xml",".dtd");
            if(cols[0]==null){
                String pp = path.replace(".xml",".dtd");
                cols = DTDGenerator.getDTDColumns(pp);
            }
            if(validateCols.validate(cols,p)) {
                //Content to be shown
                table = new ArrayList<>();
                ArrayList<Object> temp = new ArrayList<>();
                for (String col : cols) {
                    temp.add(col);
                }
                table.add(temp);
                // Get the root element
                NodeList rows = doc.getElementsByTagName("row");
                for (int i = 0; i < rows.getLength(); i++) {
                    Node row = rows.item(i);
                    Element r = ((Element) row);
                    if (condition != null ) {
                        if(selectWithCondition(row, r, condition, table)){
                            no++;
                        }

                    }else {
                        selectWithoutCondition(row, r, condition, table);
                        no++;
                    }
                }
            }
            System.out.println();
            table.remove(0);
            array = new Object[table.size()][];
            for (int i = 0; i < table.size(); i++) {
                ArrayList<Object> row = table.get(i);
                array[i] = row.toArray(new Object[row.size()]);
            }
            return array;
        } catch (Exception e) {
            e.printStackTrace();
            return array;
        }
    }

    private static void selectWithoutCondition(Node nNode, Element col, Object[][] condition, ArrayList<ArrayList<Object>> table) {
        // TODO Auto-generated method stub
        // select parent of col.
        ArrayList<Object> temp = new ArrayList<>();
        for (Object s : table.get(0)) {
            Element x = ((Element) col.getElementsByTagName(((String) s)).item(0));
            if(x.getAttribute("type").equals("int")){
                temp.add(Integer.parseInt(col.getElementsByTagName(((String) s)).item(0).getTextContent()));
            }else 
            temp.add(col.getElementsByTagName(((String) s)).item(0).getTextContent());
        }
        table.add(temp);
    }

    private static boolean selectWithCondition(Node nNode, Element col, Object[][] condition, ArrayList<ArrayList<Object>> table) {
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
