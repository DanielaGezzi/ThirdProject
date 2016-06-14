package it.uniroma3.agiw.ThirdProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NomiCreaFile {
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader("../ThirdProject/src/main/resources/italian_names.txt"));
    	String current_line;
    	String nomi = "";
    	while((current_line = br.readLine()) != null){
			nomi = nomi.concat(current_line+"|");
			

	}
    	
	nomi = nomi.substring(0, nomi.length()-1);
	System.out.println(nomi);
	FileWriter file = new FileWriter("../ThirdProject/src/main/resources/italian_name_regex.txt");
    file.write("("+nomi+")");
    file.flush();
    file.close();
	
	}
}
