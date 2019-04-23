package object;

import java.util.Random;
import java.util.Vector;

public class Player {
	private String nama;
	private int score;
	private int highScore;
	private Tile[][] tile = new Tile[TWIDTH][THEIGHT];
	private boolean live = true;
	private int totalShip = 0;
	private Bomb bomb;
	
	private static final int TWIDTH = 10;
	private static final int THEIGHT = 10;
	
	
	public void init(int x, int y) {
		int xTemp = x;
		int yTemp = y;
		for (int i = 0; i < tile.length; i++) {
			for (int j = 0; j < tile.length; j++) {
				Tile water = new Water();
				water.xPos = x;
				water.yPos = y;
				tile[i][j] = water;
				
				x += Tile.WIDTH;
			}
			x = xTemp;
			y += Tile.HEIGHT;
		}
		
		score = 15;
		live = true;
	}
	
	public void updatePosTile(int x, int y) {
		
		int xTemp = x;
		int yTemp = y;
		for (int i = 0; i < tile.length; i++) {
			for (int j = 0; j < tile.length; j++) {
				tile[i][j].setxPos(x);
				tile[i][j].setyPos(y);
				x += Tile.WIDTH;
			}
			x = xTemp;
			y += Tile.HEIGHT;
		}
	}
	
	public void initBomb() {
		
		Random rand = new Random();
		System.out.println("initBomb");
		int totalBomb = 3;
		int x = 0;
		int y = 0;
		do {
			x = rand.nextInt(9-0+1)+0;
			y = rand.nextInt(9-0+1)+0;
			if(tile[x][y] instanceof Ship == false) {
				Bomb bom = new Bomb();
				bom.setxPos(tile[x][y].xPos);
				bom.setyPos(tile[x][y].yPos);
				tile[x][y] = bom;
				System.out.println("x: " + x + " " + "y: " + y);
				totalBomb--;
			}
		}while(totalBomb > 0);
	
	}
	
	public void isDead() {

		if(score <= 0) {
			live = false;
		}
		System.out.println(score);
	}
	


	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

	public Tile[][] getTile() {
		return tile;
	}

	public void setTile(Tile[][] tile) {
		this.tile = tile;
	}

	public int getTotalShip() {
		return totalShip;
	}

	public void setTotalShip(int totalShip) {
		this.totalShip = totalShip;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
	
	
	
	
	
}
