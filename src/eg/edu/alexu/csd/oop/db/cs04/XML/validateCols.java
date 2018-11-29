package eg.edu.alexu.csd.oop.db.cs04.XML;

import java.util.ArrayList;
import java.util.Arrays;

public class validateCols {
    public static boolean validate(String[] given, String path) {
        DTDGenerator dtdGenerator = new DTDGenerator();
        String[] standard = dtdGenerator.getDTDTables(path);
        if (given.length <= standard.length) {
            ArrayList<String> temp = (ArrayList<String>) Arrays.asList(given);
            for (int i = 0; i < given.length; i++) {
                if (temp.remove(given[i])) {

                } else {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
