package eg.edu.alexu.csd.oop.db.cs04;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs04.XML.DTDGenerator;
import eg.edu.alexu.csd.oop.db.cs04.XML.SQLOrder;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class taskSplitter {
    public static void parse(String input, Database database) throws SQLException {
        input = input.toLowerCase();
        String[] split = input.split(";");
        for (String s : split) {
            if(s.contains("create") || s.contains("drop")){
                try {
                    database.executeStructureQuery(s);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if(s.contains("select")){
                try {
                    Object[][] o = database.executeQuery(s);
                    String x = SQLOrder.getInstance().getTable_head();
                    x = x.replace(".xml",".dtd");
                    String[] strings = DTDGenerator.getDTDColumns(x);
                    for (int j = 0; j < strings.length; j++) {
                            System.out.printf("%-15s",strings[j]);
                    }
                    System.out.println();
                    for(int i=0;i<o.length;i++) {
                        for (int j = 0; j < o[0].length; j++) {
                            if(o[i][j] instanceof Integer){
                                System.out.printf("%-15d",o[i][j]);
                            }else{
                                System.out.printf("%-15s",o[i][j]);
                            }
                        }
                        System.out.println();
                    }
                    //printSelect.print(database.executeQuery(s));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if(s.contains("insert") || s.contains("delete") || s.contains("update")){
                try {
                    System.out.println(database.executeUpdateQuery(s));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{
                throw new SQLException("SYNTAX ERROR");
            }
        }
    }
}
