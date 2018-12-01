package eg.edu.alexu.csd.oop.db.cs04.XML;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class createTable {

    public static boolean createTable(String[][] input, String name, String path){
        if(!new File(path+"\\" + name +".xml").exists()) {
            writeXML.writeXML(path + "\\" + name + ".xml", name);
            DTDGenerator.writeDTD(path + "\\" + name + ".dtd", name, input);
            return true;
        }else{
            return false;
        }
    }

}
