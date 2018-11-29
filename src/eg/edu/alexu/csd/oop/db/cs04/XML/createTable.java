package eg.edu.alexu.csd.oop.db.cs04.XML;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class createTable {

    public static void createTable(String[] input, String path){
        StringBuilder sb = new StringBuilder();
        sb.append(path);
        sb.append(input[0]);
        sb.append(".txt");
        path = sb.toString();
        writeXML.writeXML("dbs\\db1\\t2.dtd","t2");
        DTDGenerator.writeDTD("dbs\\db1\\t2.dtd","t2",input);
    }

}
