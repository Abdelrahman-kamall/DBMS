package eg.edu.alexu.csd.oop.db.cs04;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;


public class ValidationMethod2 {

    public boolean validateSelect(String query){
        String simpleRegex;
        simpleRegex="^\\s*SELECT\\s*\\*\\s*FROM\\s*(\\w+)\\s*;?";
        Pattern pattern1 = Pattern.compile(simpleRegex,CASE_INSENSITIVE);
        Matcher matcher = pattern1.matcher(query);
        if(matcher.find()&&compeleteRegex(query,simpleRegex)){
            return true;
        }if (!compeleteRegex(query,simpleRegex)){
            pattern1 = Pattern.compile(createComRegex(query),CASE_INSENSITIVE);
            matcher = pattern1.matcher(query);
            if(matcher.find()&&compeleteRegex(query,createComRegex(query))){
                return true;
            }
        }if(!compeleteRegex(query,createComRegex(query))){
            pattern1 = Pattern.compile(createVeryComRegex(query),CASE_INSENSITIVE);
            matcher = pattern1.matcher(query);
            if(matcher.find()&&compeleteRegex(query,createVeryComRegex(query))){
                return true;
            }
        }
        return false;
    }

//    public String getRegexSelect(String query){
//        String simpleRegex;
//        simpleRegex="SELECT\\s+\\*\\s+FROM\\s(\\w+)\\s*;?";
//        Pattern pattern1 = Pattern.compile(simpleRegex,CASE_INSENSITIVE);
//        Matcher matcher = pattern1.matcher(query);
//        if(matcher.find()){
//            return simpleRegex;
//        }else{
//            pattern1 = Pattern.compile(createComRegex(query),CASE_INSENSITIVE);
//            matcher = pattern1.matcher(query);
//            if(matcher.find()){
//                return createComRegex(query);
//            }
//        }
//        return null;
//    }

    private String createVeryComRegex(String query){
        String sf="^\\s*SELECT\\s*\\*\\s*FROM\\s+(\\w+)\\s*;?\\s*";
        String ss=createComRegex(query);
        String where = "WHERE\\s+(\\w+)\\s*([=<>])\\s*('.*[^'\";\\s]'|-?\\d+)\\s*;?\\s*";
        Pattern pattern = Pattern.compile(sf+where,CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(query);
        if(matcher.find()&&compeleteRegex(query,sf+where)){
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

        for (int i=0;i<query.length();i++){
            if(query.charAt(i)==','){
                half+=s;
            }
        }

        regex="^\\s*select"+half+end;
        return regex;
    }

}
