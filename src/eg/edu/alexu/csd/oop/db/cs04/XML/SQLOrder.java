package eg.edu.alexu.csd.oop.db.cs04.XML;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;

public class SQLOrder {

    private boolean firstInstance = true;
    private String database = null;
    private String table_head = null;
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

    public boolean createTable(String[][] input, String tablename){
        if (database != null) {
            return createTable.createTable(input, tablename,database);
        }else {
            return false;
        }

    }

    public boolean dropTable(String tablename) {
        if (database != null) {
            dropTable dropTable = new dropTable(tablename, database);
            return dropTable.isSuccess();
        }else {
            return false;
        }
    }

    public int update(String tableName, Object[][] update_value, Object[][] condition) {
        if(database!=null) {
            Update update = new Update(tableName, update_value, condition, database);
            return update.getCount();
        }else {
            return 0;
        }
    }

    public int insert(String name, String[][] cols) {
        if(database!=null)
        return InsertTable.insertRows(database+"\\"+name+".xml", name, cols);
        else return 0;
    }

    public int delete(String tableName, Object[][] condition) {
        if(database!=null) {
            Delete delete = new Delete(tableName, condition, database);
            return delete.getcount();
        }else {
            return 0;
        }
    }

    public String getTable_head() {
        return table_head;
    }

    public Object[][] select(String name, String[] cols, Object[][] condition) {
        if(database!=null) {
            table_head = database + "\\" + name + ".xml";
            return selectTable.selectCols(database + "\\" + name + ".xml", name, cols, condition);

        }
        else return null;
    }
}
