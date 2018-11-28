package eg.edu.alexu.csd.oop.db.cs04.XML;

public class SQLOrder {

    private boolean firstInstance = true;
    private String database = null;
    private static final SQLOrder instance = new SQLOrder();

    private SQLOrder() {
    }

    private String getDatabase() {
        return database;
    }

    private void setDatabase(String database) {
        this.database = database;
    }

    public static SQLOrder getInstance(String database) {
        instance.setDatabase(database);
        return instance;
    }
}
