package object;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Tile {
	protected int status = UNLOCKED;
	protected int id;
	protected ImageIcon img;
	protected ImageIcon coverImg = new ImageIcon("light_blue_rgb.png");
	
	public int xPos = 0;
	public int yPos = 0;
	
	public static final int WIDTH = 40;
	public static final int HEIGHT = 40;
	
	public static int xOffset = Tile.WIDTH*2;
	public static int yOffset = Tile.HEIGHT*2+10;
	
	public static final int LOCKED = 0;//UDAH KEBUKA
	public static final int UNLOCKED = 1;//BELUM KEBUKA
	
	public boolean isClicked = false;
	
	public void draw(Graphics g) {
		g.drawImage(img.getImage(), xPos, yPos, WIDTH, HEIGHT, null);
		
	}
	
	public void drawCover(Graphics g) {
		g.drawImage(coverImg.getImage(), xPos, yPos, WIDTH, HEIGHT, null);
	}

	
	public void updateImg() {
		img = new ImageIcon("dark_blue_rgb.png");
	}
	
	public void isClicked() {
		updateImg();
		status = LOCKED; 
	}
	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;

	}



	public ImageIcon getImg() {
		return img;
	}



	public void setImg(ImageIcon img) {
		this.img = img;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}


	

	
	
}
