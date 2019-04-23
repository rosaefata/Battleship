package object;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

public class Ship extends Tile{

	
	public Ship() {
		this.id = 1;
		this.img = new ImageIcon("download_rgb.png");
	}
	
	public void updateImg() {
		img = new ImageIcon("red_square_rgb.png");//nanti diisi pake merah tua
	}
	 @Override
	public void isClicked() {
		updateImg();
		status = LOCKED;
	}

	
}
