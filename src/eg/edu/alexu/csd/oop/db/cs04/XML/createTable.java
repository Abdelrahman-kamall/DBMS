package eg.edu.alexu.csd.oop.db.cs04.XML;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class createTable {

	public static void createTable(String database, String[][] input, String name) {
		writeXML.writeXML("dbs\\" + database + "\\" + name + ".xml", name);
		DTDGenerator.writeDTD("dbs\\" + database + "\\" + name + ".dtd", name, input);
	}

}
