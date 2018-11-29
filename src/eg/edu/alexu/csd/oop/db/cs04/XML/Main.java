package eg.edu.alexu.csd.oop.db.cs04.XML;

import javax.xml.stream.events.DTD;

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
//        xx[2]=null;

        DTDGenerator dtdGenerator = new DTDGenerator();
        dtdGenerator.getDTDTables("dbs\\db1\\t2.dtd");
        for (String dtdTable : DTDGenerator.getDTDTables("dbs\\db1\\t2.dtd")) {
            System.out.println(dtdTable);
        }

    }
}
