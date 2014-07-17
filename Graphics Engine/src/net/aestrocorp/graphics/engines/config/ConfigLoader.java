package net.aestrocorp.graphics.engines.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import net.aestrocorp.graphics.engines.input.URLFileFetcher;
import net.aestrocorp.graphics.engines.main.Engine;
import net.aestrocorp.graphics.engines.tilemanagment.Tile;
import net.aestrocorp.graphics.engines.tilemanagment.TileManager;

public class ConfigLoader {
	
	public static LinkedList<ConfigType> configOptions = new LinkedList<ConfigType>();
	
	@SuppressWarnings({ "resource" })
	public ConfigLoader(boolean existed, Engine engine){
		
		if(!existed){
			
			try{
				
				File configFile = new File("resources/engine.cfg");
				BufferedWriter configWriter = new BufferedWriter(new FileWriter(configFile, true));
				
				configWriter.close();
				
				new URLFileFetcher("https://raw.githubusercontent.com/Aestrocorp/Graphics-Engine/master/Graphics%20Engine/resources/engine.cfg", "resources/engine.cfg");
				
			}catch(Exception e){ e.printStackTrace(); }
			
		}else{
			
			try{
				
				configOptions.add(new ConfigType(true, "licenseConsole"));
				configOptions.add(new ConfigType(true, "readMeConsole"));
				configOptions.add(new ConfigType(false, "autoVersionSearch"));
				configOptions.add(new ConfigType(true, "firstRun"));
				
				File configFile = new File("resources/engine.cfg");
				BufferedReader configReader = new BufferedReader(new FileReader(configFile));
				
				String curLine = "";
				
				while((curLine = configReader.readLine()) != null){
					
					if(curLine.startsWith("#")){
						
						curLine = "";
						continue;
						
					}
					
					for(int i = 0; i < configOptions.size(); i++){
						
						ConfigType curCfg = configOptions.get(i);
						
						if(curLine.startsWith("Tile{")){
							
							Tile newTileReg = new Tile();
							
							while(true){
								
								if(curLine.endsWith("}")){ break; }
								
								curLine = configReader.readLine().trim();
								if(curLine.startsWith("Name:")){ newTileReg.setTileName(curLine.substring(5).trim()); }else
								if(curLine.startsWith("Image:")){ newTileReg.setImage(new ImageIcon(curLine.substring(6).trim()).getImage()); }else
								if(curLine.startsWith("ID:")){ newTileReg.setID(Integer.parseInt(curLine.substring(3).trim())); }
								
							}
							
							TileManager.registerTile(newTileReg);
							
							continue;
							
						}else if(curLine.startsWith(curCfg.optionName)){
							
							if(curCfg.optionName.equals("firstRun")){ curCfg.current = false; }else
							if(curLine.endsWith("true")){ curCfg.current = true; }else{ curCfg.current = false; }
							
						}
						
					}
					
				}
				
			}catch(Exception e){ e.printStackTrace(); }
			
		}
		
	}
	
	public void addConfigOption(boolean default_, String optionName){
		
		configOptions.add(new ConfigType(default_, optionName));
		
	}
	
	public void addConfigOption(ConfigType option){
		
		configOptions.add(option);
		
	}
	
}
