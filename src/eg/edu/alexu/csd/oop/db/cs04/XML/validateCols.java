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

    public static boolean validatetypes(String[][] given, String path) {
        DTDGenerator dtdGenerator = new DTDGenerator();
        path = path.replace(".xml",".dtd");
        String[][] standard = dtdGenerator.getDTDdatatypes(path);
        if (given[0].length <= standard[0].length) {
            List<String> temp1 = Arrays.asList(standard[0]);
            List<String> temp2 = Arrays.asList(standard[1]);
            for (int i = 0; i < given[0].length; i++) {
                if(temp2.get(temp1.indexOf(given[0][i])).equals("varchar") && !given[1][i].contains("\'")){
                    System.out.println("TYPE MISMATCH");
                    return false;
                }else if(temp2.get(temp1.indexOf(given[0][i])).equals("int") && given[1][i].contains("\'")){
                    System.out.println("TYPE MISMATCH");
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public static boolean validatetypesObject(Object[][] given, String path) {
        DTDGenerator dtdGenerator = new DTDGenerator();
        path = path.replace(".xml",".dtd");
        String[][] standard = dtdGenerator.getDTDdatatypes(path);
        if (given[0].length <= standard[0].length) {
            List<String> temp1 = Arrays.asList(standard[0]);
            List<String> temp2 = Arrays.asList(standard[1]);
            for (int i = 0; i < given[0].length; i++) {
                if(temp2.get(temp1.indexOf(given[0][i])).equals("varchar") && !(given[1][i].toString().contains("\'"))){
                    System.out.println("TYPE MISMATCH");
                    return false;
                }else if(temp2.get(temp1.indexOf(given[0][i])).equals("int") && ((given[1][i].toString().contains("\'")))){
                    System.out.println("TYPE MISMATCH");
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
