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

public class Player2ArrangeState extends GameState{
	public static Player player2 = new Player();
	
	public static final int TWIDTH = 10;//berapa banyak tile
	public static final int THEIGHT = 10;
	
	private final Rectangle BOUND2 = new Rectangle(Tile.WIDTH*11, Tile.yOffset, TWIDTH*Tile.WIDTH, THEIGHT*Tile.HEIGHT);
	
	private Point hover = null;
	private Point selected = null;
	private Vector<Point> drags = new Vector<Point>();
	
	private Font font;
	private ImageIcon bg = new ImageIcon("playerdua.png");
	
	public Player2ArrangeState(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	@Override
	public void init() {
		font = new Font("Berlin Sans FB Demi", Font.PLAIN, 30);
		player2.init(Tile.WIDTH*11, Tile.yOffset);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void draw(Graphics g) {

		g.drawImage(bg.getImage(), 0, 0, Panel.WIDTH, Panel.HEIGHT, null);
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("Place your 15 ships!", 85, 235);
		
		for (int i = 0; i < player2.getTile().length; i++) {
			for (int j = 0; j < player2.getTile().length; j++) {
				player2.getTile()[j][i].draw(g);
				if(hover != null && hover.x == i && hover.y == j) {
					g.setColor(Color.GREEN);
					g.fillRect(player2.getTile()[j][i].xPos,player2.getTile()[j][i].yPos,Tile.WIDTH, Tile.HEIGHT);
				}
				if(selected != null && selected.equals(new Point(i, j)) ) {
					selected = null;
				}
				
				if(drags.contains(new Point(i, j))){
					
					g.setColor(Color.GREEN);
					g.fillRect(player2.getTile()[j][i].xPos, player2.getTile()[j][i].yPos, Tile.WIDTH, Tile.HEIGHT);
				}
			}
		}
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
		
	
	@Override
	public void mouseMoved(MouseEvent e) {
		Point p = e.getPoint();
		if(BOUND2.contains(p)) {
			p.x = ((p.x-(Tile.WIDTH*11)) / Tile.WIDTH);
			p.y = ((p.y-(Tile.HEIGHT+10)) / Tile.HEIGHT)-1; 
			hover = (Point)p.clone();
		//	System.out.println(p);
		}
		else {
			hover = null;
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Point p = e.getPoint();
		if(BOUND2.contains(p) && player2.getTotalShip() < 15) {
			Point select = new Point(((p.x-(Tile.WIDTH*11)) / Tile.WIDTH),((p.y-(Tile.HEIGHT+10)) / Tile.HEIGHT)-1);
		
	
					Tile ship = new Ship();
					
					ship.xPos = player2.getTile()[select.y][select.x].xPos;
					ship.yPos = player2.getTile()[select.y][select.x].yPos;
					player2.getTile()[select.y][select.x] = ship;	
					
					if(!drags.contains(select)) {
						drags.add(select);
						int totalShip = player2.getTotalShip() + 1;
						player2.setTotalShip(totalShip);
					}
					
					
		
		}
	}
	
	
	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER && player2.getTotalShip() == 15 ) {
			gsm.setState(GameStateManager.PLAYSTATE);
		}
	}
	

	
	@Override
	public void mouseReleased(MouseEvent e) {
		drags.clear();
	}

}
