package eg.edu.alexu.csd.oop.db.cs04;

import eg.edu.alexu.csd.oop.db.cs04.XML.SQLOrder;
import eg.edu.alexu.csd.oop.db.cs04.XML.selectTable;

import java.lang.reflect.Array;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

public class ParserMethod2 {
    private int n=1;
    private boolean firstCase=false;

    public ParserMethod2(){

    }

    //TODO make return Array[][]
    public Object[][] parse1(String query){
        createComRegex(query);
        String [] a=new String[n];
        Object[][] b=new Object[3][2];
        makeNull(b);
        makeNull(a);
        String simpleRegex;
        simpleRegex="^\\s*SELECT\\s+\\*\\s+FROM\\s*(\\w+)\\s*;?\\s*";
        Pattern pattern1 = Pattern.compile(simpleRegex,CASE_INSENSITIVE);
        Matcher matcher = pattern1.matcher(query);
        if(matcher.find()&&compeleteRegex(query,simpleRegex)){
            makeNull(b);
            makeNull(a);

            return SQLOrder.getInstance().select(matcher.group(1).toLowerCase(),a,b);
        }if (!compeleteRegex(query,simpleRegex)){
            pattern1 = Pattern.compile(createComRegex(query),CASE_INSENSITIVE);
            matcher = pattern1.matcher(query);
            if(matcher.find()&&compeleteRegex(query,createComRegex(query))){

                return SQLOrder.getInstance().select(fillArray(matcher,a).toLowerCase(),a,b);
            }
        }if(!compeleteRegex(query,createComRegex(query))){
            pattern1 = Pattern.compile(createVeryComRegex(query),CASE_INSENSITIVE);
            matcher = pattern1.matcher(query);
            if(matcher.find()&&compeleteRegex(query,createVeryComRegex(query))){
                if(!firstCase)
                    fillArray(matcher,a);

                afterWhere(matcher);

                return SQLOrder.getInstance().select(fillArray(matcher,a).toLowerCase(),a,afterWhere(matcher));

            }
        }

        return SQLOrder.getInstance().select(null,a,b);
    }

    private String fillArray(Matcher matcher, Object[] a) {
        int i;
        for (i=0;i<n;i++){
            a[i]=matcher.group(i+1).toLowerCase();
        }
        return matcher.group(i+1);
    }

    private void makeNull(String[] b) {
        for (int i = 0; i < b.length; i++) {
            b[i] = null;
        }
    }

    private void makeNull(Object[][] b) {
        for (int i=0;i<b.length;i++){
            for (int ii=0;ii<b[0].length;ii++){
                b[i][ii] = null;
            }
        }
    }

    private boolean compeleteRegex(String query, String regex){
        if(regex==null){
            return false;
        }
        Pattern pattern = Pattern.compile(regex,CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(query);

        if(matcher.find()&&query.equals(matcher.group(0))){
            return true;
        }
        return false;
    }


    private String createComRegex(String query){
        String half,end,s,regex;
        half ="\\s+(\\w+)\\s*,?";
        end="\\s+FROM\\s+(\\w+)\\s*;?\\s*";
        s="\\s*(\\w+)\\s*,?";
        n=1;
        for (int i=0;i<query.length();i++){
            if(query.charAt(i)==','){
                half+=s;
                n++;
            }
        }

        regex="^\\s*select"+half+end;
        return regex;
    }

    private String createVeryComRegex(String query){
        String sf="^\\s*SELECT\\s+\\*\\s+FROM\\s(\\w+)\\s*;?\\s*";
        String ss=createComRegex(query);
        String where = "\\s*WHERE\\s+(\\w+)\\s*([=<>])\\s*('.*[^'\";\\s]'|-?\\d+)\\s*;?\\s*";
        Pattern pattern = Pattern.compile(sf+where,CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(query);
        if(matcher.find()&&compeleteRegex(query,sf+where)){
            firstCase=true;
            return sf+where;
        }else{
            pattern = Pattern.compile(ss+where,CASE_INSENSITIVE);
            matcher = pattern.matcher(query);
            if(matcher.find() &&compeleteRegex(query,ss+where) ){
                return ss+where;
            }
        }
        return ss+where;
    }

    private Object[][] afterWhere(Matcher matcher){
        if(firstCase){
            n--;
            firstCase=false;
        }

        Object[][] a=new Object[3][2];
        String s=  matcher.group(n + 3);
        if(s.equals("=")){
            a[0][0]=matcher.group(n+2).toLowerCase();
            a[0][1]=matcher.group(n+4);
            a[1][0]=null;
            a[1][1]=null;
            a[2][0]=null;
            a[2][1]=null;
        }else if(s.equals(">")){
            a[1][0]=matcher.group(n+2).toLowerCase();
            a[1][1]=matcher.group(n+4);
            a[0][0]=null;
            a[0][1]=null;
            a[2][0]=null;
            a[2][1]=null;
        }else if(s.equals("<")){
            a[2][0]=matcher.group(n+2).toLowerCase();
            a[2][1]=matcher.group(n+4);
            a[0][1]=null;
            a[0][0]=null;
            a[1][1]=null;
            a[1][0]=null;
        }
        return a;
    }
}
