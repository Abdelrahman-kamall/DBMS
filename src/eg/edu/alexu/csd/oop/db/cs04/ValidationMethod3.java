package eg.edu.alexu.csd.oop.db.cs04;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationMethod3 {

    public boolean InsertD(String query) {

        Pattern pattern1 = Pattern.compile(this.InsertDRegex(query), Pattern.CASE_INSENSITIVE);
        Matcher matcher1 = pattern1.matcher(query);
        if(matcher1.find()) {
            return true;
        }
        return false;
    }

    private String InsertDRegex(String s) {
        String[] lines = s.split("\\)");
        int first = numofcom(lines[0]);
        String insertD ="insert\\s+into\\s+(\\w+)\\s*";
        if(first !=0) {
            insertD+="\\(\\s*(\\w+)\\s*";
        }
        for(int counter =0;counter <first ;counter++) {
            insertD+=",\\s*(\\w+)\\s*";
        }
        if(first !=0) {
            insertD+="\\s*\\)\\s*";
        }
        insertD+="VALUES\\s*\\(\\s*'?\"?(.*[^'\";\\s])'?\"?";
        if(lines.length>1) {
        int second =numofcom(lines[1]);
        for(int counter =0;counter <second ;counter++) {
            insertD+="\\s*,\\s*'?\"?(.*[^'\";\\s])'?\"?";
        }
        insertD+="\\s*\\)\\s*;?\\s*";
    }
        return insertD;

		/*
		Pattern pattern1 = Pattern.compile(insertD, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern1.matcher(s);
		matcher.find();
		String regex = pattern1.toString();
		//
		String regex1="";
		String s1 = matcher.group(0);
		while (!s1.equals(s) && matcher.find()) {
			regex1=regex;
			regex += "\\s*(\\w+)\\s*,?\\s*\\)?";
			pattern1 = Pattern.compile(regex);
			matcher = pattern1.matcher(s);
			matcher.find();
			s1 = matcher.group(0);
		}
		regex1+="\\s*values\\s*\\(?(\\s*\'?\"?\\w+\\s*\\w*\\s*,?\\s*\'?\"?\\)?;?)";

		pattern1 = Pattern.compile(regex1, Pattern.CASE_INSENSITIVE);
		matcher = pattern1.matcher(s);
		s1 = matcher.group(0);
		while (!s1.equals(s) && matcher.find()) {
			regex1 += "(\\s*\'?\"?\\w+\\s*\\w*\\s*,?\\s*\'?\"?\\)?;?)";
			pattern1 = Pattern.compile(regex1);
			matcher = pattern1.matcher(s);
			matcher.find();
			s1 = matcher.group(0);
		}


		return regex1;*/

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

    public boolean UpdateD(String query) {

        Pattern pattern2 = Pattern.compile(this.UpdateDRegex(query), Pattern.CASE_INSENSITIVE);
        Matcher matcher2 = pattern2.matcher(query);

        if(matcher2.find() ) {
            return true;
        }
        return false;
    }

    private String UpdateDRegex(String s) {
        String updateD ="update\\s+(\\w+)\\s+set\\s+(\\w+)\\s*=\\s*'?\"?(.*[^'\";\\s])'?\"?";
        Pattern pattern1 = Pattern.compile(updateD, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern1.matcher(s);
        matcher.find();
        String regex = pattern1.toString();
        int num=numofcom(s);
        for(int counter =0 ; counter<num;counter++) {
            regex += ",\\s*(\\w+)\\s*=\\s*'?\"?(.*[^'\";\\s])'?\"?";
        }
		/*
		String regex1 = "";

		String s1 = matcher.group(0);
		while (!s1.equals(s) && matcher.find()) {
			//
			regex1=regex;
			regex += ",\\s*(\\w+)\\s*=\\s*(\"?\'?\\w+\\s*.?-?\\s*\\w*\"?\'?)\\s*";
			pattern1 = Pattern.compile(regex);
			matcher = pattern1.matcher(s);
			matcher.find();
			s1 = matcher.group(0);
		}
		*/
        String s1 = s.toLowerCase();
        if(s1.contains("where")) {
        regex+="(\\s+where\\s+(\\w+)\\s*([=<>]{1})\\s*'?\"?(.*[^'\";\\s])'?\"?)\\s*;?\\s*";
        }else {
        	
        }
        return regex;
    }

    public boolean DeleteD(String query) {
        String deleteD ="delete\\s+from\\s+(\\w+)\\s*;?\\s+(where\\s+(\\w+)\\s*([=<>]{1})\\s*'?\"?(.*[^'\";\\s])'?\"?)?;?\\s*";
        Pattern pattern3 = Pattern.compile(deleteD, Pattern.CASE_INSENSITIVE);
        Matcher matcher3 = pattern3.matcher(query);
        if(matcher3.find()) {
            return true;
        }
        return false;
    }

}
