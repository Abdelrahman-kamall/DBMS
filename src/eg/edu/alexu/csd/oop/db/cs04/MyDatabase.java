package eg.edu.alexu.csd.oop.db.cs04;

import eg.edu.alexu.csd.oop.db.Database;

import java.io.File;
import java.sql.SQLException;

public class MyDatabase implements Database {
	
	private boolean DBflag = false;
    @Override
    public String createDatabase(String databaseName, boolean dropIfExists) {
    	DBflag = true;
        Character sp = System.getProperty("file.separator").charAt(0);
        File file = new File("dbs" + sp + databaseName);
        String path = file.getAbsolutePath();
        
        try {
            if (dropIfExists) {

                executeStructureQuery("DROP DATABASE " + databaseName);

            }

            executeStructureQuery("CREATE DATABASE " + databaseName);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return path;
    }

    @Override
    public boolean executeStructureQuery(String query) throws SQLException {
    	query=query.toLowerCase();
		ValidationMethod1 v = new ValidationMethod1();
		ParserMethod1 p =new ParserMethod1();
            boolean flag1 =false;
        boolean flag2 =false;
            if (v.CreateDB(query)) {
            	
                flag2= p.CreateDB(query);

                flag1= true;
            } if (v.CreateT(query)){
            	flag2 = p.CreateT(query);
                flag1= true;


            }if(v.DropDB(query)){
                flag2= p.DropDB(query);
            flag1= true;

            }if (v.DropT(query)){
                flag2= p.DropT(query);
            flag1= true;
            }

            if(!flag1 || !DBflag){
                throw new SQLException();
            }

            return flag2;

    }

    @Override
    public Object[][] executeQuery(String query) throws SQLException {
    	query=query.toLowerCase();
        ValidationMethod2 v= new ValidationMethod2();
        ParserMethod2 p = new ParserMethod2();
        if (v.validateSelect(query)){
            return p.parse1(query);
        }
        throw new SQLException();
    }

    @Override
    public int executeUpdateQuery(String query) throws SQLException {
    	query=query.toLowerCase();
        ValidationMethod3 val3= new ValidationMethod3();
        ParserMethod3 par3 = new ParserMethod3();
        int rowsnum =0;
        boolean flag = false;
        if(val3.InsertD(query)) {
            rowsnum=par3.InsertD(query);
            flag=true;
        }
        if(val3.UpdateD(query)) {
            rowsnum=par3.UpdateD(query);
            flag=true;
        }
        if(val3.DeleteD(query)) {
            rowsnum=par3.DeleteD(query);
            flag=true;
        }
        if(!flag || !DBflag) {
        	throw new SQLException();
        }
       
        return rowsnum;
    }
}
