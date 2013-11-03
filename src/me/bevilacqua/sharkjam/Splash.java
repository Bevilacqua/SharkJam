package me.bevilacqua.sharkjam;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Splash extends BasicGameState{
	Image[] rajolaSplash = new Image[5];
	private int trackSplash = 0;
	private int elapsedTime;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		rajolaSplash[0] = new Image("res/splash/rajola0.png");
		rajolaSplash[1] = new Image("res/splash/rajola1.png");
	    rajolaSplash[2] = new Image("res/splash/rajola2.png");
	    rajolaSplash[3] = new Image("res/splash/rajola3.png");
	    rajolaSplash[4] = new Image("res/splash/rajola4.png");
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame sbg, Graphics g) throws SlickException {
		if(trackSplash >= rajolaSplash.length - 1) sbg.enterState(1); //TODO: make it 1 
		rajolaSplash[trackSplash].draw();		
	}
	
	public void leave(GameContainer gc , StateBasedGame sbg) {
		trackSplash = 0;
	}

	@Override 
	public void update(GameContainer arg0, StateBasedGame arg1, int DELTA) throws SlickException {
		if(elapsedTime >= 300){
			trackSplash++;
			elapsedTime = 0;
		} else {
			elapsedTime+=DELTA;
		}
	}

	@Override
	public int getID() {
		return Main.States.Splash.ordinal();
	}

}
