package net.aestrocorp.graphics.engines.input;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class URLFileFetcher {
	
	@SuppressWarnings("resource")
	public URLFileFetcher(String URLF, String newFileName){
		
		try{
			
			URL connection = new URL(URLF);
			HttpURLConnection comTest = (HttpURLConnection)connection.openConnection();
			
			comTest.setDoOutput(true);
			if(comTest.getResponseCode() == HttpURLConnection.HTTP_OK){
				
				System.out.println("Successfully connected to server!");
				ReadableByteChannel RBC = Channels.newChannel(connection.openStream());
				FileOutputStream FOS = new FileOutputStream(newFileName);
				FOS.getChannel().transferFrom(RBC, 0, Long.MAX_VALUE);
				
			}else{
				
				System.err.println("Could not connect to server!");
				return;
				
			}
			
		}catch(IOException e){ e.printStackTrace(); System.err.println("Could not find File/Server please check URL or Code"); }
		
	}
	
}
