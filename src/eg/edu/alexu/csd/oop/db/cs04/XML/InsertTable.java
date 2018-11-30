package eg.edu.alexu.csd.oop.db.cs04.XML;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.events.DTD;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class InsertTable {
    public static int insertRows(String path, String name, String[][] cols) {
        //path = path.concat("\\" + name + ".xml");
    	
        int no = 0;
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
                        temp.setTextContent(((String) cols[1][i]));
                        row.appendChild(temp);
                    } else {
                        Element temp = doc.createElement(columns[i]);
                        temp.setTextContent(null);
                        row.appendChild(temp);
                    }
                }
                no++;
            } else {
                for (int i = 0; i < columns.length; i++) {
                    Element temp = doc.createElement(columns[i]);
                    temp.setTextContent(((String) cols[1][i]));
                    row.appendChild(temp);
                }
                no++;
            }
            //no = root.getElementsByTagName("row").getLength();


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();


            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            DOMImplementation domImpl = doc.getImplementation();
            DocumentType doctype = domImpl.createDocumentType("doctype", "",
                    name + ".dtd");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, doctype.getPublicId());
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());


            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(path));
            transformer.transform(source, result);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return no;

    }
}
