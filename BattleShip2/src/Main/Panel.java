package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gameState.GameStateManager;
import object.Ship;
import object.Tile;
import object.Water;

public class Panel extends JPanel implements KeyListener{
	public static final int WIDTH = 1040;
	public static final int HEIGHT = 610;
	
	private GameStateManager gsm;
	
	public Panel() {
		init();
		update();
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseMotionListener);
		addKeyListener(this);
		setFocusable(true);
		requestFocus();
	}
	private void init() {
		gsm = new GameStateManager();
		
		 
	}
	
	private void update() {
		gsm.update();
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		gsm.draw(g);
	}
	MouseListener mouseListener = new MouseListener()
	{
		
		@Override
		public void mouseReleased(MouseEvent e) {
			gsm.mouseReleased(e);
			repaint();
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			gsm.mouseClicked(e);
			repaint();
		}
	};
	
	MouseMotionListener mouseMotionListener = new MouseMotionListener() {
		
		@Override
		public void mouseMoved(MouseEvent e) {
			gsm.mouseMoved(e);
			repaint();
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			gsm.mouseDragged(e);
			repaint();
		}
	};
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("tes");
		gsm.keyPressed(e.getKeyCode());
		repaint();
	}
	
	
}
