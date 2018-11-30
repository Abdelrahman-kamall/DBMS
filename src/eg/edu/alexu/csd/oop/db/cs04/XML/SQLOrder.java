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
		createTable.createTable(database, input, tablename);
	}

	public void dropTable(String tablename) {
		dropTable dropTable = new dropTable(database, tablename);
	}

	public int update(String tableName, Object[][] update_value, Object[][] condition) {
		Update update = new Update(database, tableName, update_value, condition);
		return update.getCount();
	}

	public int insert(String name, String[][] cols) {
		return InsertTable.insertRows(database, name, cols);
	}

	public int delete(String tableName, Object[][] condition) {
		Delete delete = new Delete(database, tableName, condition);
		return delete.getcount();
	}

	public void select(String name, String[] cols, Object[][] condition) {
		selectTable.selectCols(database, name, cols, condition);
	}
}
