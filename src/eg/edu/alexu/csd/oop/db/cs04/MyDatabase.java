package eg.edu.alexu.csd.oop.db.cs04;

import eg.edu.alexu.csd.oop.db.Database;

import java.io.File;
import java.sql.SQLException;

public class MyDatabase implements Database {
    @Override
    public String createDatabase(String databaseName, boolean dropIfExists) {

        Character sp = System.getProperty("file.separator").charAt(0);
        File file = new File("DataBases" + sp + databaseName);
        String path = file.getAbsolutePath();
        String[] lines = databaseName.split(sp.toString());
        String dbName = lines[lines.length-1];
        try {
            if (dropIfExists) {

                executeStructureQuery("DROP DATABASE " + dbName);

            }

            executeStructureQuery("CREATE DATABASE " + dbName);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return path;
    }

    @Override
    public boolean executeStructureQuery(String query) throws SQLException {
		ValidationMethod1 v = new ValidationMethod1();
		ParserMethod1 p =new ParserMethod1();
		if (v.CreateDB(query)) {
			return p.CreateDB(query);

		} if (v.CreateT(query)){
			return p.CreateT(query);

		}if(v.DropDB(query)){
		    return p.DropDB(query);

        }if (v.DropT(query)){
		    return p.DropT(query);

        }else {
		    return false;
        }
    }

    @Override
    public Object[][] executeQuery(String query) throws SQLException {
        ValidationMethod2 v= new ValidationMethod2();
        ParserMethod2 p = new ParserMethod2();
        if (v.validateSelect(query)){
            return p.parse1(query);
        }
        return new Object[0][];
    }

    @Override
    public int executeUpdateQuery(String query) throws SQLException {

        ValidationMethod3 val3= new ValidationMethod3();
        ParserMethod3 par3 = new ParserMethod3();
        int rowsnum =0;
        if(val3.InsertD(query)) {
            rowsnum=par3.InsertD(query);
        }
        if(val3.UpdateD(query)) {
            rowsnum=par3.UpdateD(query);
        }
        if(val3.DeleteD(query)) {
            rowsnum=par3.DeleteD(query);
        }
        //return that int variable
        return rowsnum;
    }
}
