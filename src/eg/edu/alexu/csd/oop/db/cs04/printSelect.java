package eg.edu.alexu.csd.oop.db.cs04;

import eg.edu.alexu.csd.oop.db.cs04.XML.DTDGenerator;
import eg.edu.alexu.csd.oop.db.cs04.XML.SQLOrder;

import java.util.ArrayList;
import java.util.List;

public class printSelect {
    public static void print(Object[][] selected){
        String th = SQLOrder.getInstance().getTable_head();
        th = th.replace(".xml",".dtd");
        String[] tablehead = DTDGenerator.getDTDColumns(th);
        Object[] tablehead2 = new Object[tablehead.length];
        int j=0;
        for (String s : tablehead) {
            tablehead2[j] = tablehead[j];
            j++;
        }
        j=0;
        List<Object[]> printable = new ArrayList<>();
        printable.add(tablehead2);
        for(int ii=0;ii<selected.length;ii++){
            printable.add(selected[ii]);
        }

        for (Object[] objects : printable) {
            int i=0;
            for (Object object : objects) {
                if(object instanceof String){
                    object = ((String) object).replaceAll("\'","");
                }
                if(i==0)
                    System.out.println(object);
                System.out.println("\t\t" + object);
                i++;
            }
            System.out.println();
        }
    }
}
