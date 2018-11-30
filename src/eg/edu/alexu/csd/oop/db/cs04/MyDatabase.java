package eg.edu.alexu.csd.oop.db.cs04;

import eg.edu.alexu.csd.oop.db.Database;

import java.sql.SQLException;

public class MyDatabase implements Database {
    @Override
    public String createDatabase(String databaseName, boolean dropIfExists) {
        return null;
    }

    @Override
    public boolean executeStructureQuery(String query) throws SQLException {
        return false;
    }

    @Override
    public Object[][] executeQuery(String query) throws SQLException {
        ValidationMethod2 v= new ValidationMethod2();
        ParserMethode2 p= new ParserMethode2();
        if(v.validateSelect(query)){
            
        }
        return new Object[0][];
    }

    @Override
    public int executeUpdateQuery(String query) throws SQLException {
        return 0;
    }
}
