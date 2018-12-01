package eg.edu.alexu.csd.oop.db.cs04.XML;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import eg.edu.alexu.csd.oop.db.cs04.DeleteDir;

public class deleteDataBase {
    public static void deleteDirectoryStream(Path path) throws IOException {
    	Character sp = System.getProperty("file.separator").charAt(0);
    	File file = new File(path.toString());
    	

    	
    			  if(file.exists()) {
    			   DeleteDir dd = new DeleteDir();
    			   dd.deleteDirectory(file); 
    			  } 
    			  
    }
}
