package net.aestrocorp.graphics.engines.required;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import net.aestrocorp.graphics.engines.input.URLFileFetcher;

public class UpdateConfigLoader {
	
	public UpdateConfigLoader(String URLStr, String downloadConfigurationFileName, FirstRun returnTo){
		
		try{
			
			new URLFileFetcher(URLStr, downloadConfigurationFileName);
			
			File downloadConfigurationFile = new File(downloadConfigurationFileName);
			BufferedReader configReader = new BufferedReader(new FileReader(downloadConfigurationFile));
			
			String curLine = "";
			
			while((curLine = configReader.readLine()) != null){
				
				curLine.trim();
				
				if(curLine.startsWith("Download{")){
					
					String URL = null, FileName = null, Extension = null, DirectoryName = null;
					boolean newDir = false;
					
					while(true){
						
						curLine = configReader.readLine();
						
						if(curLine.startsWith("}")){ break; }
						
						if(curLine.startsWith("URL:")){ URL = curLine.substring(4); }else
						if(curLine.startsWith("FileName:")){ FileName = curLine.substring(9).trim(); }else
						if(curLine.startsWith("Extension:")){ Extension = curLine.substring(10).trim().toLowerCase(); }else
						if(curLine.startsWith("DirectoryName:")){ DirectoryName = curLine.substring(15); }else
						if(curLine.startsWith("NewDirectory:")){ newDir = Boolean.parseBoolean(curLine.substring(13)); }
						
					}
					
					returnTo.downloadRequiredFiles(new DownloadInfo(URL, FileName, Extension, DirectoryName, newDir));
					
				}
				
			}
			
			configReader.close();
			
		}catch(Exception e){ e.printStackTrace(); }
		
	}
	
	class DownloadInfo{
		
		String URL, FileName, extension, DirectoryName;
		boolean newDir = false;
		
		public DownloadInfo(String URL, String FileName, String extension, String DirectoryName, boolean newDir){
			
			this.URL = URL;
			this.FileName = FileName;
			this.extension = extension;
			this.DirectoryName = DirectoryName;
			this.newDir = newDir;
			
		}
		
	}
	
}
