package it.uniroma3.agiw.ThirdProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PrefissiCreaFile {
public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader("../ThirdProject/src/main/resources/italian_prefix.txt"));
    	String current_line;
    	String pre = "";
    	while((current_line = br.readLine()) != null){
			pre = pre.concat(current_line+")|(");
			

	}
    	pre = pre.substring(0, pre.length()-1);
    	System.out.println(pre);
    	FileWriter file = new FileWriter("../ThirdProject/src/main/resources/italian_prefix_regex.txt");
        file.write("("+pre+")");
        file.flush();
        file.close();
    	
    	}

}
