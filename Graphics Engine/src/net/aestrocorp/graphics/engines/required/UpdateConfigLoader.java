package net.aestrocorp.graphics.engines.required;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import net.aestrocorp.graphics.engines.input.URLFileFetcher;

public class UpdateConfigLoader {
	
	public UpdateConfigLoader(String URLStr, String downloadConfigurationFileName){
		
		try{
			
			new URLFileFetcher(URLStr, downloadConfigurationFileName);
			
			File downloadConfigurationFile = new File(downloadConfigurationFileName);
			BufferedReader configReader = new BufferedReader(new FileReader(downloadConfigurationFile));
			
			String curLine = "";
			
			while((curLine = configReader.readLine()) != null){
				
				curLine.trim();
				
				if(curLine.startsWith("Download{")){
					
					while(true){
						
						if(curLine.startsWith("}")){ break; }
						
						if(curLine.startsWith("URL:")){  }
						
					}
					
				}
				
			}
			
			configReader.close();
			
		}catch(Exception e){ e.printStackTrace(); }
		
	}
	
	class DownloadInfo{
		
		String URL, FileName, extension;
		boolean newDir = false;
		
		public DownloadInfo(String URL, String FileName, String extension, boolean newDir){
			
			
			
		}
		
	}
	
}
