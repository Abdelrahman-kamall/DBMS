package eg.edu.alexu.csd.oop.db.cs04.XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class writeXML {
    public void writeXML(String path, String name, String[][] cols) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            //Table tag element
            Element root = document.createElement(name);
            document.appendChild(root);

            //fill rows
            Element[] rows = new Element[cols.length-1];
            int ii=1;
            for (Element row : rows) {
                row = document.createElement("row");
                for (int i = 0; i < cols[0].length; i++) {
                    Element temp = document.createElement(cols[0][i]);
                    temp.setTextContent(cols[ii][i]);
                    row.appendChild(temp);
                }
                root.appendChild(row);
                ii++;
            }

            //saving the file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
//            document.getDocumentElement().normalize();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(path));
            transformer.transform(domSource, streamResult);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
