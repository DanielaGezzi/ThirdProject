package it.uniroma3.agiw.ThirdProject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExtraction {
	
	private String email_regex = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}";
			//"[^\\s@<>]+@[^\\s<>]+\\.[^\\s@<>]+" vecchia regex
	private String telephone_regex = "[+(]?([0-9]{2,4})[-.) ]?([ .-]?)([0-9]{2,3})\\2([0-9]{3,4})";

	public RegexExtraction(){}
	
	public List<String> doEmailExtraction(String html){
		List<String> output = new ArrayList<String>();

		Pattern pattern = Pattern.compile(email_regex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
		Matcher m = pattern.matcher(html);
		
		while(m.find()){
			System.out.println(m.group());
			output.add(m.group());
		}
		
		return output;
		
	}
	
	public List<String> doTelephoneExtraction(String html){
		List<String> output = new ArrayList<String>();

		Pattern pattern = Pattern.compile(telephone_regex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
		Matcher m = pattern.matcher(html);
		
		while(m.find()){
			output.add(m.group());
		}
		
		return output;
		
	}
	
}
