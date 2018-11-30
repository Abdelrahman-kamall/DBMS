package eg.edu.alexu.csd.oop.db.cs04;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ValidationMethod1 {

    private int table = 1;

    private String CreateTableRegex(String s) {

        String createT = "\\s*create\\s+table\\s+(\\w+)\\s+\\((\\s*(\\w+)\\s+(int|varchar)\\s*\\(?\\d*\\)?\\s?,?)?\\s+\\)?;?\\s*";
        Pattern pattern1 = Pattern.compile(createT,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern1.matcher(s);
        matcher.find();
        String regex = pattern1.toString();
        String s1 = matcher.group(0);
        while (!s1.equals(s) && matcher.find()) {
            regex += "(\\s*(\\w+)\\s+(int|varchar)\\s*\\(?\\d*\\)?\\s?,?)?\\s+\\)?;?\\s*";
            pattern1 = Pattern.compile(regex);
            matcher = pattern1.matcher(s);
            matcher.find();
            s1 = matcher.group(0);
            table++;
        }
        return regex;
    }

    public boolean CreateDB(String query) {
        String createDB = "\\s*CREATE\\s+DATABASE\\s+'?(.*[^;'\\s])'?;\\s?\\s*";
        Pattern pattern1 = Pattern.compile(createDB, Pattern.CASE_INSENSITIVE);
        Matcher matcher1 = pattern1.matcher(query);
        if (matcher1.find()) {
            return true;
        }
        return false;
    }

    public boolean CreateT(String query) {
        Pattern pattern2 = Pattern.compile(this.CreateTableRegex(query), Pattern.CASE_INSENSITIVE);
        Matcher matcher2 = pattern2.matcher(query);
        if (matcher2.find()) {
            String a[][] = new String[table + 1][2];
            for (int i = 0; i < 2; i++) {
                for (int ii = 0; ii < table; ii++) {
                    if (i == 0 && ii != 0) {
                        a[ii][i] = matcher2.group(ii + 2 * ii);
                    } else if (i == 1 && ii != 0) {
                        a[ii][i] = matcher2.group((ii + 2 * ii) + 1);
                    }
                }
            }
            a[table][0] = matcher2.group(1);
            // createTable(a[][]);
            return true;
        }
        return false;
    }

    public boolean DropDB(String query) {
        String dropDB = "\\s*DROP\\s+DATABASE\\s+'?(.*[^;'\\s])'?;\\s?\\s*";
        Pattern pattern3 = Pattern.compile(dropDB, Pattern.CASE_INSENSITIVE);
        Matcher matcher3 = pattern3.matcher(query);
        if (matcher3.find()) {
            // dropDataBase(matcher3.group(1));
            return true;
        }
        return false;
    }

    public boolean DropT(String query) {
        String dropT = "\\s*drop\\s+table\\s(\\w+);\\s?\\s*";
        Pattern pattern4 = Pattern.compile(dropT, Pattern.CASE_INSENSITIVE);
        Matcher matcher4 = pattern4.matcher(query);
        if (matcher4.find()) {
            // dropTable(matcher4.group(1));
            return true;
        }
        return false;
    }

}
