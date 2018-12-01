package eg.edu.alexu.csd.oop.db.cs04.XML;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class validateCols {
    public static boolean validate(String[] given, String path) {
        DTDGenerator dtdGenerator = new DTDGenerator();
        path = path.replace(".xml",".dtd");
        String[] standard = dtdGenerator.getDTDColumns(path);
        if (given.length <= standard.length) {
            List<String> temp = Arrays.asList(standard);
            for (int i = 0; i < given.length; i++) {
                if (!(temp.indexOf(given[i])>=0)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
