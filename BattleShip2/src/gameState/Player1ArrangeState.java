package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;

import Main.Panel;
import object.Player;
import object.Ship;
import object.Tile;

public class Player1ArrangeState extends GameState{

	public static Player player1 = new Player();
	
	public static final int TWIDTH = 10;//berapa banyak tile
	public static final int THEIGHT = 10;
	
	
	private final Rectangle BOUND = new Rectangle(Tile.WIDTH*11, Tile.yOffset, TWIDTH*Tile.WIDTH, THEIGHT*Tile.HEIGHT);
	
	private Point hover = null;
	private Point selected = null;
	private Vector<Point> drags = new Vector<Point>();
	
	private Font font;
	private ImageIcon bg = new ImageIcon("playersatu_rgb.png");
	
	public Player1ArrangeState(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	@Override
	public void init() {
		font = new Font("Berlin Sans FB Demi", Font.PLAIN, 30);
		player1.init(Tile.WIDTH*11, Tile.yOffset);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void draw(Graphics g) {
//		g.setColor(Color.WHITE);
//		g.fillRect(0, 0, Panel.WIDTH, Panel.HEIGHT);
		
		g.drawImage(bg.getImage(), 0, 0, Panel.WIDTH, Panel.HEIGHT, null);
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("Place your 15 ships!", 85, 235);
		
		for (int i = 0; i < player1.getTile().length; i++) {
			for (int j = 0; j < player1.getTile().length; j++) {
				player1.getTile()[j][i].draw(g);
				
				if(hover != null && hover.x == i && hover.y == j)  {
					g.setColor(Color.GREEN);
					g.fillRect(player1.getTile()[j][i].xPos, player1.getTile()[j][i].yPos, Tile.WIDTH, Tile.HEIGHT);
				}

				if(drags.contains(new Point(i, j))){
				
					g.setColor(Color.GREEN);
					g.fillRect(player1.getTile()[j][i].xPos, player1.getTile()[j][i].yPos, Tile.WIDTH, Tile.HEIGHT);
				}

			}
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Point p = e.getPoint();
		if(BOUND.contains(p) && player1.getTotalShip() < 15) {
			Point select = new Point(((p.x-(Tile.WIDTH*11)) / Tile.WIDTH),((p.y-(Tile.HEIGHT+10)) / Tile.HEIGHT)-1);
			
			
		//	System.out.println("drags size" + "" + drags.size());
		//	if(selected == null) {
				
			//	System.out.println("ke drag");
				Tile ship = new Ship();
				ship.xPos = player1.getTile()[select.y][select.x].xPos;
				ship.yPos = player1.getTile()[select.y][select.x].yPos;
				player1.getTile()[select.y][select.x] = ship;	
				
				if(!drags.contains(select)) {
					drags.add(select);
				
						int totalShip = player1.getTotalShip() + 1;
						player1.setTotalShip(totalShip);
					
				}
				
				System.out.println("total ship: " + player1.getTotalShip());
			//	selected = select;
		//	}
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
//		Point p = e.getPoint();
//		if(BOUND.contains(p)) {
//			Point select = new Point(((p.x-(Tile.WIDTH*11)) / Tile.WIDTH),((p.y-(Tile.HEIGHT+10)) / Tile.HEIGHT)-1);
//			if(selected == null) {
//				if(BOUND.contains(p) && player1.getTotalShip() <= 15) {
//					Tile ship = new Ship();
//					ship.xPos = player1.getTile()[select.y][select.x].xPos;
//					ship.yPos = player1.getTile()[select.y][select.x].yPos;
//					
//					player1.getTile()[select.y][select.x] = ship;	
//					int totalShip = player1.getTotalShip() + 1;
//					player1.setTotalShip(totalShip);
//
//					selected = select;
//				}
//				
//				
//			}
//			else {
//				if(selected.equals(select)) {
//					selected = null;
//				}
//				else {
//					
//				}
//			}
//		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		Point p = e.getPoint();
		if(BOUND.contains(p)) {
			p.x = ((p.x-(Tile.WIDTH*11)) / Tile.WIDTH);
			p.y = ((p.y-(Tile.HEIGHT+10)) / Tile.HEIGHT)-1; 
			hover = (Point)p.clone();
	//		System.out.println(p);
		}
		else {
			hover = null;
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		drags.clear();
	}

	
	
	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER && player1.getTotalShip() == 15) {
			gsm.setState(GameStateManager.PLAYER2);
		}
	}
	
	

}
