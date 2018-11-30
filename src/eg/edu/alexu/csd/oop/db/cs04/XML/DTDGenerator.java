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
import java.io.*;
import java.util.ArrayList;

public class DTDGenerator {
    public static void writeDTD(String path, String name, String[][] cols) {
        try {
            File ff = new File(path);
            System.out.println(ff.createNewFile());
            FileWriter fw = null;
            fw = new FileWriter(ff);
            fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            fw.write("\t<!ELEMENT " + name + " (row)*>\n");
            fw.write("\t<!ELEMENT row (");
            for (int i = 0; i < cols[0].length; i++) {
                fw.write(cols[0][i]);
                if (i + 1 < cols[0].length)
                    fw.write(",");
            }
            fw.write(")>\n");
            for (int i = 0; i < cols[0].length; i++) {
                fw.write("\t<!ELEMENT ");
                fw.write(cols[0][i]);
                fw.write(" (#PCDATA)>\n");
                fw.write("\t<!ATTLIST ");
                fw.write(cols[0][i]);
                fw.write(" type CDATA #FIXED \""+cols[1][i]+"\">\n");
            }
            //close resources
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[] getDTDColumns(String path) {
        String[] x = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
            System.out.println(bufferedReader.readLine());
            System.out.println(bufferedReader.readLine());
            String regex = "[\\(,\\)]";
            x = bufferedReader.readLine().split(regex);
            String[] cols = new String[x.length-2];
            for (int i=1;i<x.length-1;i++) {
                cols[i-1]=x[i];
                System.out.println(x[i]);
            }
            return cols;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

}
