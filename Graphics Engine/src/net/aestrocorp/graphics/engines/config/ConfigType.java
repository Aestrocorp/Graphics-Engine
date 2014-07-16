package net.aestrocorp.graphics.engines.config;

/**This class is only for new Tile types.*/
public class ConfigType {
	
	public boolean default_, current;
	public String optionName = "Unknown";
	
	public ConfigType(boolean default_, String optionName){
		
		this.default_ = default_;
		current = default_;
		this.optionName = optionName;
		
	}
	
	public void addCurrentValue(boolean current){
		
		this.current = current;
		
	}
	
}
