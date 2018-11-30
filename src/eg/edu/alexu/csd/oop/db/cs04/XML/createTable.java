package eg.edu.alexu.csd.oop.db.cs04.XML;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class createTable {

    public static void createTable(String[][] input, String name, String path){
        writeXML.writeXML(path+"\\" + name +".xml",name);
        DTDGenerator.writeDTD(path+"\\" + name +".dtd",name,input);
    }

}
