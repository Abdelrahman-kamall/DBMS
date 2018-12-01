package eg.edu.alexu.csd.oop.db.cs04;

import eg.edu.alexu.csd.oop.db.Database;

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
                    System.out.println(Arrays.deepToString(database.executeQuery(s)));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if(s.contains("insert") || s.contains("delete") || s.contains("uodate")){
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
