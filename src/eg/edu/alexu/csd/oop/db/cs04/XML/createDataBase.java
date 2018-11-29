package eg.edu.alexu.csd.oop.db.cs04.XML;

import java.io.*;
public class createDataBase {

    String dbName ;
    public createDataBase(String dbName){
        this.dbName = dbName;
        create();
    }

    private void create(){
        File theDir = new File("dbs\\dbName");

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            boolean result = false;

            try{
                theDir.mkdir();
                result = true;
            }
            catch(SecurityException se){
                //handle it
            }
            if(result) {
                System.out.println("DIR created");
            }
        }
    }
}
