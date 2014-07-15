package net.aestrocorp.graphics.engines.main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import net.aestrocorp.graphics.engines.input.ConfigLoader;
import net.aestrocorp.graphics.engines.input.InputListener;
import net.aestrocorp.graphics.engines.input.URLFileFetcher;
import net.aestrocorp.graphics.engines.input.URLTextFetcher;
import net.aestrocorp.graphics.engines.tilemanagment.Tile;
import net.aestrocorp.graphics.engines.tilemanagment.TileManager;

public class Engine extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	public static String TITLE;
	
	public static int WIDTH, HEIGHT;
	public static JFrame frame = new JFrame();
	
	public Thread engine = new Thread(this);
	private boolean running = false;
	
	public boolean autoClose = false;
	public boolean licenseConsole = true;
	public boolean readMeConsole = true;
	
	//**This is for the engine! Don't confuse it for your game's version control!*/
	public boolean autoVersionSearch = false;
	public String version = "1.0.0.0.0";
	
	public Engine(int width, int height, String title, boolean fullScreen){
		
		WIDTH = width;
		HEIGHT = height;
		TITLE = title;
		
		File config = new File("resources/engine.cfg");
		if(config.exists()){
			
			new ConfigLoader(true, this);
			
		}else{
			
			try {
				
				config.createNewFile();
				new ConfigLoader(false, this);
				
			} catch (IOException e) { e.printStackTrace(); }
			
		}
		
		if(licenseConsole){
			
			//**Please read this license or know what it means BEFORE you make games with it. I don't want any trouble.*/
			new URLTextFetcher("https://raw.githubusercontent.com/Aestrocorp/Graphics-Engine/master/Graphics%20Engine/resources/LICENSE.txt");
			
		}
		
		if(readMeConsole){
			
			//**Read me file fetch*/
			new URLTextFetcher("https://raw.githubusercontent.com/Aestrocorp/Graphics-Engine/master/Graphics%20Engine/resources/readme.txt");
			
		}
		
		if(autoVersionSearch){
			
			String getversion = new URLTextFetcher("https://raw.githubusercontent.com/Aestrocorp/Graphics-Engine/master/Graphics%20Engine/resources/version%5Bmaster%5D").returnline();
			if(!version.equals(getversion)){
				JOptionPane.showMessageDialog(null, "New engine update available!", "Updater", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
		File readMeFile = new File("resources/readme.txt");
		File licenseFile = new File("resources/LICENSE.txt");
		
		if(!readMeFile.exists()){ new URLFileFetcher("https://raw.githubusercontent.com/Aestrocorp/Graphics-Engine/master/Graphics%20Engine/resources/readme.txt", "resources/readme.txt"); }
		if(!licenseFile.exists()){ new URLFileFetcher("https://raw.githubusercontent.com/Aestrocorp/Graphics-Engine/master/Graphics%20Engine/resources/LICENSE.txt", "resources/LICENSE.txt"); }
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(TITLE);
		
		if(fullScreen){
			frame.setUndecorated(true);
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			
			WIDTH = frame.getWidth();
			HEIGHT = frame.getHeight();
			autoClose = true;
		}else{ frame.setSize(WIDTH, HEIGHT); frame.setResizable(false); }
		
		frame.add(this);
		
		InputListener iL = new InputListener();
		this.addKeyListener(iL);
		this.addMouseListener(iL);
		
		frame.setVisible(true);
		
	}
	
	public void start(){
		
		if(running){ return; }
		
		//**To initialize and register all of the tiles*/
		new TileManager();
		
		running = true;
		engine.start();
		
	}
	
	public void run(){
		
		int forAutoClose = 0;
		
		while(running){
			
			if(autoClose){ forAutoClose++; }
			if(forAutoClose >= 250 && autoClose){ System.out.println("Nothing to do!\nAutoclosed the program!"); break; }
			
			render();
			
		}
		
		System.exit(0);
		
	}
	
	public void render(){
		
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){ createBufferStrategy(2); return; }
		
		Graphics g = bs.getDrawGraphics();
		{
			
			LinkedList<Tile> renderTiles = TileManager.getImportantTiles();
			for(int i = 0; i < renderTiles.size(); i++){
				
				Tile tile = renderTiles.get(i);
				g.drawImage(tile.getTileImage(), tile.tileX, tile.tileY, 32, 32, null, null);
				
			}
			
		}
		
		g.dispose();
		bs.show();
		
	}
	
	public static void main(String[] args){
		
		Engine engine = new Engine(800, 700, "Graphics engine testing", false);
		engine.start();
		
	}
	
}
