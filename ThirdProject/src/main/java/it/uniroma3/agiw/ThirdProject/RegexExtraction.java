package it.uniroma3.agiw.ThirdProject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExtraction {
	
	private String email_regex = "[^\\s@<>]+@[^\\s<>]+\\.[^\\s@<>]+";

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
	
}
