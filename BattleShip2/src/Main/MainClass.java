package Main;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

 
public class MainClass {

	public static void main(String[] args) {
		JFrame window = new JFrame("BATTLESHIP GAME");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(Panel.WIDTH, Panel.HEIGHT);
		window.add(new Panel());
		window.setResizable(true);
		window.setVisible(true);
	}

}
