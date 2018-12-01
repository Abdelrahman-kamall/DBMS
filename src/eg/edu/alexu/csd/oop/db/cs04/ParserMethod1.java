package eg.edu.alexu.csd.oop.db.cs04;

import eg.edu.alexu.csd.oop.db.cs04.XML.SQLOrder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserMethod1 {
    private int table = 1;

    public boolean CreateDB(String query) {
        String createDB = "^\\s*CREATE\\s+DATABASE\\s+'?(.*[^;'\\s])'?\\s*;?\\s*";
        Pattern pattern1 = Pattern.compile(createDB, Pattern.CASE_INSENSITIVE);
        Matcher matcher1 = pattern1.matcher(query);
        if (matcher1.find()) {

            SQLOrder.getInstance().createDatabase(matcher1.group(1).toLowerCase());
            return true;
        }
        return false;
    }

    public boolean CreateT(String query) {
        Pattern pattern2 = Pattern.compile(this.CreateTableRegex(query), Pattern.CASE_INSENSITIVE);
        Matcher matcher2 = pattern2.matcher(query);
        if (matcher2.find()) {
            String a[][] = new String[2][table];
            for (int ii = 1; ii <= table; ii++) {
                a[0][ii-1] = matcher2.group(ii + 2 * ii).toLowerCase();
                a[1][ii-1] = matcher2.group((ii + 2 * ii) + 1);
            }


            return SQLOrder.getInstance().createTable(a,matcher2.group(1).toLowerCase());
        }
        return false;
    }

    public boolean DropDB(String query) {
        String dropDB = "^\\s*DROP\\s+DATABASE\\s+'?(.*[^;'\\s])'?\\s*;?\\s*";
        Pattern pattern3 = Pattern.compile(dropDB, Pattern.CASE_INSENSITIVE);
        Matcher matcher3 = pattern3.matcher(query);
        if (matcher3.find()) {

            SQLOrder.getInstance().dropDatabase(matcher3.group(1).toLowerCase());
            return true;
        }
        return false;
    }

    public boolean DropT(String query) {
        String dropT = "^\\s*drop\\s+table\\s(\\w+)\\s*;?\\s*";
        Pattern pattern4 = Pattern.compile(dropT, Pattern.CASE_INSENSITIVE);
        Matcher matcher4 = pattern4.matcher(query);
        if (matcher4.find()) {

            SQLOrder.getInstance().dropTable(matcher4.group(1).toLowerCase());
            return true;
        }
        return false;
    }

    private String CreateTableRegex(String s) {
    	int numofcom = numofcom(s);
    	String createT = "^\\s*create\\s+table\\s+(\\w+)\\s*\\(\\s*((\\w+)\\s+(int|varchar)\\s*,?)?\\s*\\)?\\s*;?\\s*";
        Pattern pattern1 = Pattern.compile(createT,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern1.matcher(s);
        matcher.find();
        String regex = pattern1.toString();
        String s1 = matcher.group(0);
        
        for(int counter=0;counter<numofcom;counter++) {
        	regex += "\\s*((\\w+)\\s+(int|varchar)\\s*,?)?\\s*\\)?\\s*;?\\s*";
        	table++;
        }
        return regex;
    }
    
    private int numofcom(String s) {
        int num =0;
        for(int counter =0 ; counter <s.length();counter++) {
            if(s.charAt(counter) == ',') {
                num++;
            }
        }
        return num;
    }


}
