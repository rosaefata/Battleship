package object;

import javax.swing.ImageIcon;

public class Bomb extends Tile{

	public Bomb() {
		
	}
	
	@Override
	public void updateImg() {
		img = new ImageIcon("bomb2.png");
	}
	
	@Override
	public void isClicked() {
		updateImg();
		status = LOCKED;
	}

}
