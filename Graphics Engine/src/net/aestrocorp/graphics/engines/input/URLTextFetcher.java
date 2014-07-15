package net.aestrocorp.graphics.engines.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLTextFetcher {
	
	String line, savedLine;
	
	public URLTextFetcher(String URLF){
		
		try{
			
			URL connection = new URL(URLF);
			HttpURLConnection comTest = (HttpURLConnection)connection.openConnection();
			
			comTest.setDoOutput(true);
			if(comTest.getResponseCode() == HttpURLConnection.HTTP_OK){
				
				System.out.println("Successfully connected to server!");
				BufferedReader read = new BufferedReader(new InputStreamReader(connection.openStream()));
				
				while((line = read.readLine()) != null){
					
					System.err.println(line);
					savedLine += "\n" + line;
					
				}
								
			}else{
				
				System.err.println("Could not connect to server!");
				return;
				
			}
		
		}catch(IOException e){ System.err.println("Error please check your URL or Code!"); }
		
	}
	
	public String returnline(){
		
		return savedLine;
		
	}
	
}