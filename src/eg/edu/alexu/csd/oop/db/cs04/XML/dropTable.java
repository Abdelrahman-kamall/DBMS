package eg.edu.alexu.csd.oop.db.cs04.XML;

import java.io.*;
import java.io.IOException;
import java.nio.file.*;

public class dropTable {

	private String database;
	private String tableName;

	public dropTable(String database, String tableName) {
		this.tableName = tableName;
		this.database = database;
		drop();
	}

	public void drop() {
		try {
			Files.deleteIfExists(Paths.get("dbs\\" + database + "\\" + tableName + ".xml"));
			Files.deleteIfExists(Paths.get("dbs\\" + database + "\\" + tableName + ".dtd"));
		} catch (NoSuchFileException e) {
			System.out.println("No such file/directory exists");
		} catch (DirectoryNotEmptyException e) {
			System.out.println("Directory is not empty.");
		} catch (IOException e) {
			System.out.println("Invalid permissions.");
		}
	}
}
