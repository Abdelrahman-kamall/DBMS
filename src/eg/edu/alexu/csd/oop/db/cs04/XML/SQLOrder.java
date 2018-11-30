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

    public boolean createDatabase(String database) {
        this.database = database;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("dbs\\");
        stringBuilder.append(database);
        this.database = stringBuilder.toString();
        createDataBase createDataBase = new createDataBase(this.database);
        return createDataBase.isSuccess();
    }

    public boolean dropDatabase(String database) {
        try {
        	StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("dbs\\");
            stringBuilder.append(database);
            database=stringBuilder.toString();
            deleteDataBase.deleteDirectoryStream(Paths.get(database));
            this.database = null;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createTable(String[][] input, String tablename) {
        return createTable.createTable(input, tablename,database);
    }

    public boolean dropTable(String tablename) {
        dropTable dropTable = new dropTable(tablename,database);
        return dropTable.isSuccess();
    }

    public int update(String tableName, Object[][] update_value, Object[][] condition) {
        Update update = new Update(tableName, update_value, condition,database);
        return update.getCount();
    }

    public int insert(String name, String[][] cols) {
        return InsertTable.insertRows(database+"\\"+name+".xml", name, cols);
    }

    public int delete(String tableName, Object[][] condition) {
        Delete delete = new Delete(tableName, condition,database);
        return delete.getcount();
    }

    public Object[][] select(String name, String[] cols, Object[][] condition) {
        return selectTable.selectCols(database+"\\"+name+".xml", name, cols, condition);
    }
}
