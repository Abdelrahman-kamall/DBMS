package eg.edu.alexu.csd.oop.db.cs04.XML;

import java.io.IOException;
import java.nio.file.Paths;

public class SQLOrder {

    private boolean firstInstance = true;
    private String database = null;
    private static final SQLOrder instance = new SQLOrder();

    private SQLOrder() {
    }

    public static SQLOrder getInstance() {
        return instance;
    }

    public void createDatabase(String database) {
        this.database = database;
    }

    public void dropDatabase(String database) {
        try {
            DeleteDataBase.deleteDirectoryStream(Paths.get(database));
            database = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String[][] input, String tablename) {
        createTable.createTable(input, tablename,database);
    }

    public void dropTable(String tablename) {
        dropTable dropTable = new dropTable(tablename,database);
    }

    public void update(String tableName, Object[][] update_value, Object[][] condition) {
        Update update = new Update(tableName, update_value, condition,database);
    }

    public void insert(String name, String[][] cols) {
        InsertTable.insertRows(database+"\\"+name+".xml", name, cols);
    }

    public void delete(String tableName, Object[][] condition) {
        Delete delete = new Delete(tableName, condition,database);
    }

    public void select(String name, String[] cols, Object[][] condition) {
        selectTable.selectCols(database+"\\"+name+".xml", name, cols, condition);
    }
}
