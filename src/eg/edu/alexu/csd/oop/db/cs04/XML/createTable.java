package eg.edu.alexu.csd.oop.db.cs04.XML;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class createTable {

    public static void createTable(String[][] input, String name){
        writeXML.writeXML("dbs\\db1\\" + name +".xml",name);
        DTDGenerator.writeDTD("dbs\\db1\\" + name +".dtd",name,input);
    }

}
