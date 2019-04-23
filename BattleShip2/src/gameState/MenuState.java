package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

import Main.Panel;



public class MenuState extends GameState{

	private int currentChoice = 0;
	private String[] options = {
			"Start",
			"Help",
			"Quit"
	};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	private ImageIcon bg = new ImageIcon("main_menu.png");
	private ImageIcon soundOn = new ImageIcon("sounds_on.png");
	private ImageIcon soundOff = new ImageIcon("sounds_off.png");
	
	private Rectangle BOUND = new Rectangle(975, 0, 40, 40);
	private boolean sound = true;
			;
	private Clip clip;
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		try {
		
			font = new Font("Berlin Sans FB Demi", Font.PLAIN, 30);
		}
		catch(Exception e) {
			e.printStackTrace();  
		}
		try {
			   clip = AudioSystem.getClip();
			   AudioInputStream stream = AudioSystem.getAudioInputStream(new File("bs/backsound.wav"));
			   clip.open(stream);
			   clip.loop(Clip.LOOP_CONTINUOUSLY);
			   clip.start();
			  } 
		catch (LineUnavailableException | UnsupportedAudioFileException |IOException e1) {
				  e1.printStackTrace();
			  }
	}
	
	@Override
	public void init() {
	
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		if(BOUND.contains(p)) {
			if(sound == true) {
				 System.out.println(clip);
				clip.stop();
				sound = false;
			}
			else if(sound == false) {
				 System.out.println(clip);
				clip.start();
				sound = true;
			}
		}

	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		Point p = e.getPoint();
		if(BOUND.contains(p)) {
			
		}
		else if(!BOUND.contains(p)) {
			
		}
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawRect(0, 0, Panel.WIDTH, Panel.HEIGHT);
		g.drawImage(bg.getImage(), 0, 0, Panel.WIDTH, Panel.HEIGHT, null);
		g.setColor(titleColor);
		g.setFont(titleFont);
	
		
		g.setFont(font);
		
		for (int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(new Color(26, 73, 86));
			}
			else {
				g.setColor(Color.WHITE);
			}
			g.drawString(options[i], 300, 320 + i * 40);
		}
		
		
		
		if(sound == false) {
			g.drawImage(soundOff.getImage(), 975, 0, 40, 40, null);
		}
		else if(sound == true) {
			g.drawImage(soundOn.getImage(), 975, 0, 40, 40, null);
		}
	
	}
	
	
	private void select() {
		if(currentChoice == 0 ) {
			gsm.setState(GameStateManager.PLAYER1);
		}
		if(currentChoice == 1) {
			gsm.setState(GameStateManager.HELP);
		}
		if(currentChoice == 2) {
			System.exit(0);
		}

	}
	
	@Override
	public void keyPressed(int k) {
		
		if(k == KeyEvent.VK_ENTER ) {
			select();
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			System.out.println(currentChoice);
			if(currentChoice == -1) {
				currentChoice = options.length -1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			System.out.println("tes");
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}	
	}
	
	

	
	@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	
}
