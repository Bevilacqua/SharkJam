package me.bevilacqua.sharkjam;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Player {
	private Image idleImage;
	private List<Image> animation = new ArrayList<Image>();
	private int currentAnimation;
	private int health;
	private int score;
	
	private boolean eating;
	private List<Image> eatingAnimation = new ArrayList<Image>();
	private int eatingAnimationIndex;
	
	private int elapsedTime;
	
	private Vector2f position;
	
	public Player() {
		try {
			this.idleImage = new Image("res/shark.png");
			
			this.animation.add(idleImage);
			this.animation.add(new Image("res/shark1.png"));
			this.animation.add(new Image("res/shark2.png"));
			this.animation.add(new Image("res/shark3.png"));
			this.animation.add(new Image("res/shark4.png"));
			this.animation.add(new Image("res/shark3.png"));
			this.animation.add(new Image("res/shark2.png"));
			this.animation.add(new Image("res/shark1.png"));
			this.animation.add(new Image("res/shark1.png"));

			this.position = new Vector2f(36 * 2 , 36 * 2);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g) {
//		this.idleImage.draw(position.getX(), position.getY());
		if(!this.eating) {
			this.animation.get(this.currentAnimation).draw(position.x, position.y);
		} else {
			//Play animation loop
		}
	} 
	
	public void update(int delta, GameContainer gc) {
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_UP)) this.position.add(new Vector2f(0,-3));
		if(input.isKeyDown(Input.KEY_DOWN)) this.position.add(new Vector2f(0,3));

		if(input.isKeyDown(Input.KEY_LEFT)) this.position.add(new Vector2f(-1,0));
		if(input.isKeyDown(Input.KEY_RIGHT)) this.position.add(new Vector2f(3,0));
		
		if(input.isKeyPressed(Input.KEY_SPACE)) {
			this.eating = true;
		}
		
		//Animation
		if(!eating) {
			if(this.elapsedTime > 250) {
				if(this.currentAnimation < this.animation.size() - 1) this.currentAnimation++;
				else this.currentAnimation = 0;
				this.elapsedTime = 0;
			} else {
				this.elapsedTime += delta;
			}
		} else {
			//TODO: Finish eating animation logic!
		}
	}
	
	private void playEatingAnimation() { //Rendering
		if(this.eatingAnimationIndex >= this.eatingAnimation.size()) {
			this.eating = false;
			this.eatingAnimationIndex = 0;
		} else {
			this.eatingAnimation.get(this.eatingAnimationIndex).draw(position.x, position.y);
		}
	}
}
