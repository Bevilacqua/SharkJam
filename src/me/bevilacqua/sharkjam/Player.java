package me.bevilacqua.sharkjam;

import java.awt.Rectangle;
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
	private int health = 5000;
	private int score;
	
	private Rectangle collison;
	
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
			
			this.eatingAnimation.add(new Image("res/sharkEating.png"));
			this.eatingAnimation.add(new Image("res/sharkEating1.png"));
			this.eatingAnimation.add(new Image("res/sharkEating.png"));

			this.position = new Vector2f(36 * 2 , 36 * 2);
			this.collison = new Rectangle((int)this.position.getX(), (int)this.position.getY(), this.animation.get(0).getWidth(), this.animation.get(0).getHeight());
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g) {
//		this.idleImage.draw(position.getX(), position.getY());
		if(!this.eating) {
			this.animation.get(this.currentAnimation).draw(position.x, position.y);
		} else {
			playEatingAnimation();
		}
	} 
	
	public void update(int delta, GameContainer gc) {
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_UP) && position.getY() > 32) this.position.add(new Vector2f(0,-3));
		if(input.isKeyDown(Input.KEY_DOWN) && position.getY() < 540) this.position.add(new Vector2f(0,3));

		if(input.isKeyDown(Input.KEY_LEFT) && position.getX() > 10) this.position.add(new Vector2f(-1,0));
		if(input.isKeyDown(Input.KEY_RIGHT) && position.getX() < (800 - 64)) this.position.add(new Vector2f(3,0));
		
		if(input.isKeyPressed(Input.KEY_SPACE)) {
			this.eating = true;
			this.elapsedTime = 0;
			this.eatingAnimationIndex = 0;
		}
		
		this.collison.setLocation((int)this.position.getX(), (int)this.position.getY());
		this.health -= delta; //Lose health unless eating

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
			if(this.elapsedTime > 250) {
				if(this.eatingAnimationIndex < this.eatingAnimation.size() - 1) this.eatingAnimationIndex++;
				else {
					this.eating = false;
				}
				this.elapsedTime = 0;
			} else {
				this.elapsedTime += delta;
			}
		}
	}
	
	private void playEatingAnimation() { //Rendering
			this.eatingAnimation.get(this.eatingAnimationIndex).draw(position.x, position.y);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Rectangle getCollison() {
		return collison;
	}

	public void setCollison(Rectangle collison) {
		this.collison = collison;
	}
	
	public boolean getEating() {
		return this.eating;
	}
	
	
}
