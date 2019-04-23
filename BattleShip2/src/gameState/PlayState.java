package gameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import Main.Panel;
import object.Bomb;
import object.Player;
import object.Ship;
import object.Tile;
import object.Water;

public class PlayState extends GameState{
	private Player player1;
	private Player player2;
	
	public static final int TWIDTH = 10;//berapa banyak tile
	public static final int THEIGHT = 10;
	
	private final Rectangle BOUND = new Rectangle(Tile.xOffset, Tile.yOffset+40, TWIDTH*Tile.WIDTH, THEIGHT*Tile.HEIGHT);
	private final Rectangle BOUND2 = new Rectangle(Tile.xOffset + (Tile.WIDTH*12), Tile.yOffset+40, TWIDTH*Tile.WIDTH, THEIGHT*Tile.HEIGHT);
	
	private Point hover = null;
	private Point selected = null;
	
	private ImageIcon win = new ImageIcon("win1_rgb.png");
	private ImageIcon lose = new ImageIcon("lose1_rgb.png");
	private ImageIcon bg = new ImageIcon("playstate_rgb.png");
	

	

	public PlayState(GameStateManager gsm) {
		this.gsm = gsm;
	}

	@Override
	public void init() {
		player1 = Player1ArrangeState.player1;
		player2 = Player2ArrangeState.player2;

		player1.updatePosTile(Tile.xOffset + (Tile.WIDTH*12), Tile.yOffset+40);
		player2.updatePosTile(Tile.xOffset, Tile.yOffset+40);
		
		player1.initBomb();
		player2.initBomb();
		
		
	}

	@Override
	public void update() {
		//System.out.println("update");
		player1.isDead();
		player2.isDead();
		
	}

	@Override
	public void draw(Graphics g) {

		g.drawImage(bg.getImage(), 0, 0, Panel.WIDTH, Panel.HEIGHT, null);
		if(player1.isLive() == true && player2.isLive() == true) {
			
			for (int i = 0; i < player2.getTile().length; i++) {
				for (int j = 0; j < player2.getTile().length; j++) {
					if(player2.getTile()[j][i].getStatus() == Tile.LOCKED) {
						player2.getTile()[j][i].draw(g);
					}
					else if( player2.getTile()[j][i].getStatus() == Tile.UNLOCKED) {
						player2.getTile()[j][i].drawCover(g);
					}
					if(hover != null && hover.x == i && hover.y == j) {
						g.setColor(Color.GREEN);
						g.fillRect(player2.getTile()[j][i].xPos,player2.getTile()[j][i].yPos,Tile.WIDTH, Tile.HEIGHT);
					}
					if(selected != null && selected.equals(new Point(i, j))) {
						selected = null;
					}
				}
			}
			
			for (int i = 0; i < player1.getTile().length; i++) {
				for (int j = 0; j < player1.getTile().length; j++) {
					if(player1.getTile()[j][i].getStatus() == Tile.LOCKED) {
						player1.getTile()[j][i].draw(g);
					}
					else if( player1.getTile()[j][i].getStatus() == Tile.UNLOCKED) {
						player1.getTile()[j][i].drawCover(g);
					}
					
					if(hover != null && (hover.x-12) == i && hover.y == j) {
						g.setColor(Color.GREEN);
						g.fillRect(player1.getTile()[j][i].xPos,player1.getTile()[j][i].yPos,Tile.WIDTH, Tile.HEIGHT);
					}
					if(selected != null && selected.equals(new Point(i+12, j)) ) {
						selected = null;
					}
				}
			}
		}
		else {
			System.out.println("masuk kalah menang");
			if(player2.isLive() == false) {
				g.drawImage(win.getImage(), Tile.xOffset, Tile.yOffset + 40, 400, 400, null);
				g.drawImage(lose.getImage(),Tile.xOffset + (Tile.WIDTH*12), Tile.yOffset + 40, 400, 400, null);
				player2.setTotalShip(0);
				player1.setTotalShip(0);
				player2.setScore(15);
				player1.setScore(15);
			}
			else if(player1.isLive() == false) {
				g.drawImage(win.getImage(), Tile.xOffset + (Tile.WIDTH*12), Tile.yOffset, 400, 400, null);
				g.drawImage(lose.getImage(), Tile.xOffset, Tile.yOffset, 400, 400, null);
				player1.setTotalShip(0);
				player2.setTotalShip(0);
				player2.setScore(15);
				player1.setScore(15);
			}
		}
	
	}

	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER && (player1.isLive() == false || player2.isLive() == false)) {
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}



	@Override
	public void mouseMoved(MouseEvent e) {
		Point p = e.getPoint();
		if(BOUND.contains(p) || BOUND2.contains(p)) {
			p.x = ((p.x-Tile.WIDTH) / Tile.WIDTH)-1;
			p.y = ((p.y-(Tile.yOffset+40)) / Tile.HEIGHT); 
			hover = (Point)p.clone();
		
		}
		else {
			hover = null;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		Point select = new Point(((p.x-Tile.WIDTH) / Tile.WIDTH)-1,((p.y-(Tile.yOffset+40)) / Tile.HEIGHT));
			if(BOUND.contains(p) || BOUND2.contains(p)) {
					if(selected == null) {
						if(BOUND.contains(p) && player2.getTile()[select.y][select.x].getStatus() == Tile.UNLOCKED) {
							player2.getTile()[select.y][select.x].isClicked();
							if(player2.getTile()[select.y][select.x] instanceof Ship) {
								int score = player2.getScore() - 1;
								player2.setScore(score);
								
								int totalShip = player2.getTotalShip() - 1;
								player2.setTotalShip(totalShip);
							}
							else if(player2.getTile()[select.y][select.x] instanceof Bomb) {
								for (int i = -1; i < 2; i++) {
									for (int j = -1; j < 2; j++) {
										if(select.y+i >= 0 && select.x+j >= 0 && select.y+i <= 9 && select.x+j <= 9) {
											if(player2.getTile()[select.y+i][select.x+j].getStatus() == Tile.UNLOCKED) {
												player2.getTile()[select.y+i][select.x+j].isClicked();
												if(player2.getTile()[select.y+i][select.x+j] instanceof Ship) {
													int score = player2.getScore() - 1;
													player2.setScore(score);
													
													int totalShip = player2.getTotalShip() - 1;
													player2.setTotalShip(totalShip);
												}
												
											}
											
										}
										
									}
								}
							}
							selected = select;
						}
				
				else if(BOUND2.contains(p) && player1.getTile()[select.y][select.x-12].getStatus() == Tile.UNLOCKED) {
						player1.getTile()[select.y][select.x-12].isClicked();
						if(player1.getTile()[select.y][select.x-12] instanceof Ship) {
							int score = player1.getScore() - 1;
							player1.setScore(score);
							
							int totalShip = player1.getTotalShip() - 1;
							player1.setTotalShip(totalShip);
						}
						else if(player1.getTile()[select.y][select.x-12] instanceof Bomb) {
							for (int i = -1; i < 2; i++) {
								for (int j = -1; j < 2; j++) {
									if(select.y+i >= 0 && select.x-12+j >= 0 && select.y+i <= 9 && select.x-12+j <= 9) {
										if(player1.getTile()[select.y+i][select.x-12+j].getStatus() == Tile.UNLOCKED) {
											player1.getTile()[select.y+i][select.x-12+j].isClicked();
											if(player1.getTile()[select.y+i][select.x-12+j] instanceof Ship) {
												int score = player1.getScore() - 1;
												player1.setScore(score);
												
												int totalShip = player1.getTotalShip() - 1;
												player1.setTotalShip(totalShip);
											}
										}
										
									}
								}
							}
						}
						selected = select;
					}
					
			}
			}
		update();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
