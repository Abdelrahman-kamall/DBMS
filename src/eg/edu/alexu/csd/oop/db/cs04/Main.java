package eg.edu.alexu.csd.oop.db.cs04;

import java.util.Scanner;

import eg.edu.alexu.csd.oop.db.Database;
import eg.edu.alexu.csd.oop.db.cs04.MyDatabase;
import eg.edu.alexu.csd.oop.db.cs04.taskSplitter;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Database db = new MyDatabase();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String query = "";
            Scanner lineScanner = new Scanner(scanner.nextLine());

            while (lineScanner.hasNext()) {
                String str = lineScanner.next();
                if (str.toLowerCase().equals("exit")) {
                    System.exit(0);
                }
                query += str + " ";
            }
            lineScanner.close();
            try {
                taskSplitter.parse(query, db);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        scanner.close();

    }
}
