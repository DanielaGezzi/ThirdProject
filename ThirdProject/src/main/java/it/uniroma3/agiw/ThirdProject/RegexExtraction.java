package it.uniroma3.agiw.ThirdProject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExtraction {
	
	private String email_regex = "[^\\s@<>]+@[^\\s<>]+\\.[^\\s@<>]+";
	private String telephone_regex = "[+(]?([0-9]{2,})[-.) ]?([ .-]?)([0-9]{2,})\\2([0-9]{3,})";

	public RegexExtraction(){}
	
	public List<String> doEmailExtraction(String html){
		List<String> output = new ArrayList<String>();

		Pattern pattern = Pattern.compile(email_regex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
		Matcher m = pattern.matcher(html);
		
		while(m.find()){
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
