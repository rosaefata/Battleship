package object;

import javax.swing.ImageIcon;

public class Water extends Tile{
	public Water() {
		this.id = 0;
		this.img = new ImageIcon("dark_blue_rgb.png");
		this.isClicked = false;
	}
	@Override
	public void isClicked() {
		// TODO Auto-generated method stub
		super.isClicked();
	}
}
