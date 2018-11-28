package eg.edu.alexu.csd.oop.db.cs04.XML;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DTDGenerator {
    public void writeDTD(String path, String name, String[] cols) {
        try {
            File ff = new File(path);
            System.out.println(ff.createNewFile());
            FileWriter fw = null;
            fw = new FileWriter(ff);
            fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            fw.write("\t<!ELEMENT " + name + " (row)*>\n");
            fw.write("\t<!ELEMENT row (");
            for (int i = 0; i < cols.length; i++) {
                fw.write(cols[i]);
                if(i+1<cols.length)
                    fw.write(",");
            }
            fw.write(")>\n");
            for (int i = 0; i < cols.length; i++) {
                fw.write("\t<!ELEMENT ");
                fw.write(cols[i]);
                fw.write(" (#PCDATA)>\n");
            }
            //close resources
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
