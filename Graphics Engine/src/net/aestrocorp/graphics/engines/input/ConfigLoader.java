package net.aestrocorp.graphics.engines.input;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import net.aestrocorp.graphics.engines.main.Engine;

public class ConfigLoader {
		
	@SuppressWarnings("resource")
	public ConfigLoader(boolean existed, Engine engine){
		
		if(!existed){
			
			try{
				
				File configFile = new File("resources/engine.cfg");
				BufferedWriter configWriter = new BufferedWriter(new FileWriter(configFile, true));
				
				configWriter.write("autoVersionSearch=false");
				configWriter.newLine();
				
				configWriter.write("licenseConsole=true");
				configWriter.newLine();
				
				configWriter.write("readMeConsole=true");
				configWriter.newLine();
				
				configWriter.close();
				
			}catch(Exception e){ e.printStackTrace(); }
			
		}else{
			
			try{
				
				File configFile = new File("resources/engine.cfg");
				BufferedReader configReader = new BufferedReader(new FileReader(configFile));
				
				String curLine = "";
				
				while((curLine = configReader.readLine()) != null){
					
					if(curLine.startsWith("autoVersionSearch=")){
						
						curLine = curLine.substring(18);
						engine.autoVersionSearch = Boolean.parseBoolean(curLine);
						System.err.println("autoVersionSearch=" + engine.autoVersionSearch);
						
					}
					
					if(curLine.startsWith("licenseConsole=")){
						
						curLine = curLine.substring(15);
						engine.licenseConsole = Boolean.parseBoolean(curLine);
						System.err.println("licenseConsole=" + engine.licenseConsole);
						
					}
					
					if(curLine.startsWith("readMeConsole=")){
						
						curLine = curLine.substring(14);
						engine.readMeConsole = Boolean.parseBoolean(curLine);
						System.err.println("readMeConsole=" + engine.readMeConsole);
						
					}
					
				}
				
			}catch(Exception e){ e.printStackTrace(); }
			
		}
		
	}
	
}
