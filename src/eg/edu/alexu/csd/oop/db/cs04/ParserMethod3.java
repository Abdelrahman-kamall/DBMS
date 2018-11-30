package eg.edu.alexu.csd.oop.db.cs04;

import eg.edu.alexu.csd.oop.db.cs04.XML.SQLOrder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserMethod3 {

    public int InsertD(String query) {


        Pattern pattern1 = Pattern.compile(this.InsertDRegex(query), Pattern.CASE_INSENSITIVE);
        Matcher matcher1 = pattern1.matcher(query);
        matcher1.find();
        String tableName = matcher1.group(1);

        String[] lines = query.split("\\)");
        int first = numofcom(lines[0])+1;
        int second =numofcom(lines[1])+1;
        String[][] elements = new String[2][second];
        int m = 0;
        for(int counter =second ; counter >= 1 ; counter--) {
            elements[1][counter-1]= matcher1.group(matcher1.groupCount()-m);
            m++;
        }
        for(int counter =1 ; counter <= first ; counter++) {
            elements[0][counter-1] = matcher1.group(counter+1).toLowerCase();
        }

        int num =
        SQLOrder.getInstance().insert(tableName.toLowerCase(),elements);

		/*System.out.println(tableName);
		for(int counter =0;counter<2;counter++) {
			for(int counter1 =0;counter1<second;counter1++) {
				System.out.print(elements[counter][counter1]+" ");
			}
			System.out.println();
		}
*/
        return num;
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



    public int UpdateD(String query) {


        Pattern pattern2 = Pattern.compile(this.UpdateDRegex(query), Pattern.CASE_INSENSITIVE);
        Matcher matcher2 = pattern2.matcher(query);
        matcher2.find();
        String tableName = matcher2.group(1);
        Object[][] conditions= new Object[3][2];
        int numofcom = numofcom(query)+1;
        Object[][] arr = new Object[2][numofcom];
        for(int counter=1;counter<=numofcom;counter++) {
            arr[0][counter-1]=matcher2.group(counter*2).toLowerCase();
            arr[1][counter-1]=matcher2.group(counter*2+1);
        }

        for(int counter=0;counter<3;counter++) {
            conditions[counter][0]=0;
            conditions[counter][1]=0;
        }
        if(matcher2.groupCount()>(numofcom+1)*2) {
            conditions=decideCond(matcher2.group((numofcom+1)*2+1),matcher2.group((numofcom+1)*2+2),matcher2.group((numofcom+1)*2+3));
        }
        int num =
        SQLOrder.getInstance().update(tableName.toLowerCase(),arr,conditions);
        // call update method with params

		/*System.out.println(tableName);
		for(int counter =0;counter<2;counter++) {
			for(int counter1 =0;counter1<numofcom;counter1++) {
				System.out.print(arr[counter][counter1]+" ");
			}
			System.out.println();
		}
		for(int counter =0;counter<3;counter++) {
			for(int counter1 =0;counter1<2;counter1++) {
				System.out.print(conditions[counter][counter1]+" ");
			}
			System.out.println();
		}*/
        return num;
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



    public int DeleteD(String query) {
    	
        String deleteD ="delete\\s+from\\s+(\\w+)\\s*;?";
        String s1 = query.toLowerCase();
        if(s1.contains("where")) {
        	deleteD+="(\\s+where\\s+(\\w+)\\s*([=<>]{1})\\s*'?\"?(.*[^'\";\\s])'?\"?)\\s*;?\\s*";
            }else {
            	
            }
        Pattern pattern3 = Pattern.compile(deleteD, Pattern.CASE_INSENSITIVE);
        Matcher matcher3 = pattern3.matcher(query);
        matcher3.find();
        String tableName = matcher3.group(1);
        Object[][] conditions =new Object[3][2];

        for(int counter=0;counter<3;counter++) {
            conditions[counter][0]=0;
            conditions[counter][1]=0;
        }

        if(matcher3.groupCount()>3) {
            conditions = decideCond(matcher3.group(3),matcher3.group(4),matcher3.group(5));
        }

		/*System.out.println(tableName);

		for(int counter =0;counter<3;counter++) {
			for(int counter1 =0;counter1<2;counter1++) {
				System.out.print(conditions[counter][counter1]+" ");
			}
			System.out.println();
		}
		*/
        int num =
        SQLOrder.getInstance().delete(tableName.toLowerCase(),conditions);
        //call delete method with params
        return num;
    }

    Object[][] decideCond(String colName , String operation , Object value){
        Object[][] conditions = new Object[3][2];

        if(operation.equals("=")) {
            conditions[0][0] =colName.toLowerCase();
            conditions[0][1] =value;
            conditions[1][0]=null;
            conditions[1][1]=null;
            conditions[2][0]=null;
            conditions[2][1]=null;
        }else if(operation.equals("<")) {
            conditions[0][0] =null;
            conditions[0][1] =null;
            conditions[1][0]=colName.toLowerCase();
            conditions[1][1]=value;
            conditions[2][0]=null;
            conditions[2][1]=null;
        }else if(operation.equals(">")) {
            conditions[0][0] =null;
            conditions[0][1] =null;
            conditions[1][0]=null;
            conditions[1][1]=null;
            conditions[2][0]=colName.toLowerCase();
            conditions[2][1]=value;
        }
        return conditions;
    }

}
