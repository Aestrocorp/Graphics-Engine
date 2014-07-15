package net.aestrocorp.graphics.engines.tilemanagment;

import java.awt.Image;
import java.util.LinkedList;

import net.aestrocorp.graphics.engines.main.Engine;

public class TileManager {
	
	public static LinkedList<Tile> tileList = new LinkedList<Tile>();
	public static LinkedList<Tile> cloneTiles = new LinkedList<Tile>();
	
	public TileManager(){
		
		init();
		
	}
	
	//**For loading the tiles*/
	public void init(){
		
		
		
	}
	
	public static void registerTile(Tile newTile){ tileList.add(newTile); }
	public static LinkedList<Tile> getTileRegistry(){ return tileList; }
	
	public void spawnTile(int id, int x, int y){
		
		for(int i = 0; i < tileList.size(); i++){
			
			if(tileList.get(i).getID() == id){
				
				Tile tile = tileList.get(i);
				Image tImg = tile.getTileImage();
				String tNme = tile.getTileName();
				
				Tile newTile = tile.cloneTile(tImg, tNme, x, y);
				cloneTiles.add(newTile);
				
			}
			
		}
		
	}
	
	public static LinkedList<Tile> getImportantTiles(){
		
		LinkedList<Tile> impTiles = new LinkedList<Tile>();
		int w = Engine.WIDTH, h = Engine.HEIGHT;
		
		for(int i = 0; i < cloneTiles.size(); i++){
			
			Tile tile = cloneTiles.get(i);
			boolean x = false, y = false;
			
			if(tile.tileX >= 0){ if(tile.tileX <= w){ x = true; } }
			if(tile.tileY >= 0){ if(tile.tileY <= h){ y = true; } }
			
			if(x && y){ impTiles.add(cloneTiles.get(i)); }
			
		}
		
		return impTiles;
		
	}
	
}
