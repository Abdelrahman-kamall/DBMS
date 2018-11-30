package eg.edu.alexu.csd.oop.db.cs04.XML;

import java.io.*;
public class createDataBase {

    private String dbName ;
    private boolean success = false;
    public createDataBase(String dbName){
        this.dbName = dbName;
        create();
    }

    public boolean isSuccess() {
        return success;
    }

    private boolean create(){
        File theDir = new File(dbName);

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            success = false;

            try{
                theDir.mkdirs();
                success = true;
                return success;
            }
            catch(SecurityException se){
                //handle it
                return success;
            }
        }
        return success;
    }
}
