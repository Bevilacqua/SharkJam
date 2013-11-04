package me.bevilacqua.sharkjam;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame {
	
	public static Main main;
	
	public enum States {
		Splash,
		Play,
		Title
	}
	
	public Main() {
		super("Shark Bite");
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(new Splash());
		addState(new Play());
		addState(new Title());
		enterState(States.Splash.ordinal());
	}
	
	public static void main(String args[]) throws SlickException {
		main = new Main();
		AppGameContainer agc = new AppGameContainer(main);
		agc.setDisplayMode(800,640, false);
		agc.setTargetFrameRate(60);
		agc.setShowFPS(false);
		agc.start();
	}

}
