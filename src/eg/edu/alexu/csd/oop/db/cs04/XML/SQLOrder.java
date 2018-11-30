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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String[][] input, String tablename) {
        createTable.createTable(input, tablename);
    }

    public void dropTable(String tablename) {
        dropTable dropTable = new dropTable(tablename);
    }

    public void update(String tableName, Object[][] update_value, Object[] condition) {
        Update update = new Update(tableName, update_value, condition);
    }

    public void insert(String path, String name, String[][] cols) {
        InsertTable.insertRows(path, name, cols);
    }

    public void delete(String tableName, Object[] condition) {
        Delete delete = new Delete(tableName,condition);
    }

    public void select(String path,String name,String [] cols,Object[][] condition) {
        selectTable.selectCols(path,name,cols,condition);
    }
}
