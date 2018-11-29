package eg.edu.alexu.csd.oop.db.cs04.XML;

import java.io.*;

public class deleteDataBase {

	private File f;
	private String dbName;

	public deleteDataBase(String dbName) {
		this.dbName = dbName;
		f = new File(dbName);
		deleteDirectory(f);
	}

	private boolean deleteDirectory(File directoryToBeDeleted) {
		File[] allContents = directoryToBeDeleted.listFiles();
		if (allContents != null) {
			for (File file : allContents) {
				deleteDirectory(file);
			}
		}
		return directoryToBeDeleted.delete();
	}
}
