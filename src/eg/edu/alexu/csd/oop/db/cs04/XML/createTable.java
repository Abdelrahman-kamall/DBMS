package eg.edu.alexu.csd.oop.db.cs04.XML;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class createTable {
    private String path = "dbs\\db1\\";
    private StringBuilder sb;

    public createTable(String... input){
        sb = new StringBuilder();
        sb.append(path);
        sb.append(input[0]);
        sb.append(".txt");
        path = sb.toString();
        createCols(input);
    }

    private void createCols(String[] cols){
        File ff = new File(path);
        try {
            System.out.println(ff.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter(ff);
            for (int i = 1; i < cols.length; i++) {
                fw.write(cols[i]);
                fw.write("\n");
                System.out.println(cols[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //close resources
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
