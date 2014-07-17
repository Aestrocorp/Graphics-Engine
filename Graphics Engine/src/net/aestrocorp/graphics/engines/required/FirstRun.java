package net.aestrocorp.graphics.engines.required;

import java.io.File;

public class FirstRun {
	
	public FirstRun(){
		
		init();
		downloadRequiredFiles();
		
	}
	
	public void init(){
		
		System.out.println("Setting up directories.");
		
		File imageDir = new File("resources/default_res");
		imageDir.mkdirs();
		
		
		
	}
	
	public void downloadRequiredFiles(){
		
		
		
	}
	
}
