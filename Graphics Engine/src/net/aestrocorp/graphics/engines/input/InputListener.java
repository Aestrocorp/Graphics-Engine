package net.aestrocorp.graphics.engines.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputListener implements KeyListener, MouseListener{
	
	private static boolean[] keys = new boolean[70000];
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
	public void keyPressed(KeyEvent e) { keys[e.getKeyCode()] = true; }
	public void keyReleased(KeyEvent e) { keys[e.getKeyCode()] = false; }
	
	public void keyTyped(KeyEvent e) {}
	
	public static boolean getKeyDown(int key){ return keys[key]; }
	
}
