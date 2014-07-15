package net.aestrocorp.graphics.engines.tilemanagment;

import java.awt.Image;

public class Tile {
	
	String tileName;
	Image tileImage;
	int tileID;
	
	public int tileX, tileY;
	
	public int getID(){ return tileID; }
	public void setID(int id){ tileID = id; } 
	
	public Image getTileImage(){ return tileImage; }
	public void setImage(Image image){ tileImage = image; }
	
	public String getTileName(){ return tileName; }
	public void setTileName(String name){ tileName = name; }
	
	public Tile cloneTile(Image image, String name, int x, int y){
		
		Tile newTile = new Tile();
		
		newTile.tileImage = image;
		newTile.tileName = name;
		
		newTile.tileX = x;
		newTile.tileY = y;
		
		return newTile;
		
	}
	
}
