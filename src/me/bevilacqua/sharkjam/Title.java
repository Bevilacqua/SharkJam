package me.bevilacqua.sharkjam;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Title extends BasicGameState {
	private Image image;
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		image = new Image("res/title.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		image.draw();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if(input.isKeyPressed(Input.KEY_ENTER)) {
			sbg.enterState(Main.States.Play.ordinal());
		}
	}

	@Override
	public int getID() {
		return Main.States.Title.ordinal();
	}

}
