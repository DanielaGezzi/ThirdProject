package it.uniroma3.agiw.ThirdProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NameSurname {
	private List<String> names;
//	private List<String> surnames;
	
	public NameSurname() throws IOException{
		this.names = new ArrayList<String>();
//		this.surnames = new ArrayList<String>();		
	}
	
	public List<String> getNames() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("../ThirdProject/src/main/resources/italian_names.txt"));
    	String current_line;
    	while((current_line = br.readLine()) != null){
    		this.names.add(current_line);
    	}
		return this.names;
	}
	public void setNames(List<String> names) {
		this.names = names;
	}
//	public List<String> getSurnames() throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader("../ThirdProject/src/main/resources/cognomi2.txt"));
//    	String current_line;
//    	while((current_line = br.readLine()) != null){
//    		this.surnames.add(current_line);
//    	}
//		return surnames;
//	}
//	public void setSurnames(List<String> surnames) {
//		this.surnames = surnames;
//	}
}
