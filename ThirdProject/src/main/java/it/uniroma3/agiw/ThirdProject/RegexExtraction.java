package it.uniroma3.agiw.ThirdProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExtraction {
	
	private String email_regex = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}";
			//"[^\\s@<>]+@[^\\s<>]+\\.[^\\s@<>]+" vecchia regex
	private String telephone_regex = "";
	//[^0-9]((\\+39)|(0[1-9]))([0-9().\\- ]{5,9})
	private String name_regex = "";
	
	
	public RegexExtraction(){}
	
	public List<String> doEmailExtraction(String html){
		List<String> output = new ArrayList<String>();

		Pattern pattern = Pattern.compile(email_regex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
		Matcher m = pattern.matcher(html);
		
		while(m.find()){
			//System.out.println(m.group());
			if(!output.contains(m.group()))
			output.add(m.group());
		}
		
		return output;
		
	}
	
	public List<String> doTelephoneExtraction(String html) throws IOException{
		List<String> output = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader("../ThirdProject/src/main/resources/italian_prefix_regex.txt"));
    	String prefissi = br.readLine();
		telephone_regex = "(?![^0-9])((\\+39)|" + prefissi + ")([0-9().\\- ]{5,10})|(\\+39)?((38[{8,9}|0])|(34[{7-9}|0])|(36[6|8|0])|(33[{3-9}|0])|(32[{8,9}]))([\\d]{7})";
		Pattern pattern = Pattern.compile(telephone_regex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
		Matcher m = pattern.matcher(html);
		
		while(m.find()){
			if(!output.contains(m.group()))
			output.add(m.group());
		}
		
		return output;
		
	}
	
	public List<String> doNamesExtraction(String html) throws IOException{
		List<String> output = new ArrayList<String>();
		NameSurname ns = new NameSurname();
		List<String> names = ns.getNames();
		BufferedReader br = new BufferedReader(new FileReader("../ThirdProject/src/main/resources/italian_name_regex.txt"));
    	String nomi = br.readLine();
    	
				//I casi sono: Nome Cognome, NOME Cognome, Nome COGNOME, NOME COGNOME e nome cognome
				name_regex =  nomi+" ([A-Z][a-z']+ )*([A-Z][a-z']+)|"+nomi.toUpperCase()+" ([A-Z][a-z']+ )*([A-Z][a-z']+)|"
							  + nomi+" ([A-Z][a-z']+ )*([A-Z][a-z']+)|"+nomi.toUpperCase()+" ([A-Z][a-z']+ )*([A-Z][a-z']+)";
				execute(name_regex,html,output);
				//I casi sono: Cognome Nome, COGNOME Nome,Cognome NOME, COGNOME NOME e cognome nome
				//name_regex =  nomi+"\\s([A-Z' ]+\\w+){1,2}|"+nomi.toUpperCase()+"\\s([A-Z' ]+\\w+){1,2}|"
				//		  + nomi+"\\s([A-Z' ]+\\w+){1,2}|"+nomi.toUpperCase()+"\\s([A-Z' ]+\\w+){1,2}";
				//execute(name_regex,html,output);
		
		return output;		
	}
	
	public void execute(String regex,String html,List<String>output){
		Pattern pattern = Pattern.compile(name_regex, Pattern.MULTILINE);
		Matcher m = pattern.matcher(html);
		
		while(m.find()){
			if(!output.contains(m.group()))
			output.add(m.group());
		}		

	}
	
	
}
