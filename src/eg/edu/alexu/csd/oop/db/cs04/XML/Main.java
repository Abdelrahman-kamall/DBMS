
package eg.edu.alexu.csd.oop.db.cs04.XML;

import javax.xml.stream.events.DTD;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
        // createTable ct = new createTable("table1","name","mark");


//        String[][] x = {{"column_name1", "column_name2", "column_name3"}, {"11111111", "22222222", "33333333"}};
//        createTable createTable = new createTable(x[0]);
//		writeXML xml = new writeXML();
//
//		DTDGenerator dtdGenerator = new DTDGenerator();
//		dtdGenerator.writeDTD("dbs\\db1\\t2.dtd","t2",x[0]);


//		xml.writeXML("dbs\\db1\\t2.xml","t2",x);


//		Object[] cond = { "column_name3", "value_3" };
//		Delete up = new Delete("table1", cond);

//        int[][] xx = new int[3][2];
//        xx[1]=null;
////        xx[2]=null;
//        String[][] x = {{"column_name1", "column_name2", "column_name3"}, {"Integer", "String", "String"}};
//        DTDGenerator.writeDTD("dbs\\db1\\t2.dtd","t2",x);
//        System.out.println("113".compareTo("112"));

//        String[] c ={"column_name2","column_name1"};
//        Object[][] o = {{null,null},{"column_name1","5"},{null,null}};
//        System.out.println(Arrays.deepToString(selectTable.selectCols("dbs\\db1\\t2.xml", "t2", c, o)));
//        String[][] o1 = {{"column_name1","column_name2"},{"what","dafuq"}};
//        String[][] o2 = {{null,null,null},{"what","the","fuck"}};
////        InsertTable.insertRows("dbs\\db1\\t2.xml","t2",o1);
//        String[][] input = {{"Name","Age"},{"String","int"}};
////        createTable.createTable(input,"test");
//        String[][] o3 = {{"Name","Age"},{"what","dafuq"}};
//        InsertTable.insertRows("dbs\\db1\\test.xml","test",o3);
//
//        //dropTable d = new dropTable("t2");
//        createDataBase db = new createDataBase("t5");
        File f = new File("test1\\test2\\test3\\");
        f.mkdirs();
        File ff = new File(f.getPath()+"\\tst.txt");
        try {
            ff.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            DeleteDataBase.deleteDirectoryStream(Paths.get(f.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
