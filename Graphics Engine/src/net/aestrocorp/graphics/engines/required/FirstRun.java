package net.aestrocorp.graphics.engines.required;

import java.io.File;

import net.aestrocorp.graphics.engines.input.URLFileFetcher;
import net.aestrocorp.graphics.engines.required.UpdateConfigLoader.DownloadInfo;

public class FirstRun {
	
	public FirstRun(){
		
		init();
		
	}
	
	public void init(){
		
		System.out.println("Setting up directories.");
		
		File downloadConfigDir = new File("downloadConfig/");
		downloadConfigDir.mkdir();
		
		new UpdateConfigLoader("", "", this);
		
	}
	
	public void downloadRequiredFiles(DownloadInfo info){
		
		if(info.newDir){
			
			File newDir = new File(info.DirectoryName);
			newDir.mkdir();
			
		}
		
		new URLFileFetcher(info.URL, info.FileName + info.extension);
		
	}
	
}
