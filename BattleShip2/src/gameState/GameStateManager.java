package gameState;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameStateManager {
	private ArrayList<GameState> gameStates;
	private int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int PLAYSTATE = 1;
	public static final int PLAYER1 = 2;
	public static final int PLAYER2 = 3;
	public static final int HELP = 4;
	public GameStateManager() {
		gameStates = new ArrayList<GameState>();
		
		currentState = MENUSTATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new PlayState(this));
		gameStates.add(new Player1ArrangeState(this));
		gameStates.add(new Player2ArrangeState(this));
		gameStates.add(new HelpState(this));
	}
	
	public void setState(int state) {
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	public void update() {
		gameStates.get(currentState).update();
	}
	
	public void draw(Graphics g) {
		gameStates.get(currentState).draw(g);
	}
	
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}
	
//	public void keyReleased(int k) {
//		gameStates.get(currentState).keyReleased(k);
//	}
	
	public void mouseClicked(MouseEvent e) {
		gameStates.get(currentState).mouseClicked(e);
	}
	
	public void mouseMoved(MouseEvent e) {
		gameStates.get(currentState).mouseMoved(e);
	}
	
	public void mouseDragged(MouseEvent e) {
		gameStates.get(currentState).mouseDragged(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		gameStates.get(currentState).mouseReleased(e);
	}
}
