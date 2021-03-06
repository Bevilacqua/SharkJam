package me.bevilacqua.sharkjam;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Fish {
	private List<Image> animation = new ArrayList<Image>();
	private byte currentAnimation;
	private int elapsedTime;
	private int delay;
	
	private Color colorFilter;
	
	private Rectangle collision;
	private Vector2f position;
	private FishType type;
	
	public enum FishType {
		regular, //Not sure what different fish types will do yet... maybe speed
		dangerous,
		special
	}
	
	public Fish(Vector2f position, FishType type, int animationDelay) throws SlickException {
		this.position = position;
		this.type = type;
		this.delay = animationDelay;
		
		this.animation.add(new Image("res/fish/regular.png"));
		this.animation.add(new Image("res/fish/regular1.png"));
		this.animation.add(new Image("res/fish/regular1.png"));
		this.animation.add(new Image("res/fish/regular.png"));
		this.collision = new Rectangle((int)position.getX(), (int)position.getY(), this.animation.get(0).getWidth(), this.animation.get(0).getHeight());
		
		switch(this.type) {
		case regular:
			this.colorFilter = null;
			break;
		case dangerous:
			this.colorFilter = Color.red;
			break;
		case special:
			this.colorFilter = Color.green;
			break;
		}
	}
	
	public void render(Graphics g) {
		if(this.colorFilter != null)
			this.animation.get(this.currentAnimation).draw(position.getX(), position.getY(), this.colorFilter);
		else { 
			this.animation.get(this.currentAnimation).draw(position.getX(), position.getY());
		}

	}
	
	public void update(int delta) {
		if(this.elapsedTime >= this.delay) {
			if(this.currentAnimation < this.animation.size() -1) this.currentAnimation++;
			else this.currentAnimation = 0;
			this.elapsedTime = 0;
		} else {
			this.elapsedTime += delta;
		}
		
		//Testing:
		this.position.add(new Vector2f(-2,0));
		
		this.collision.setLocation((int)this.position.getX(), (int)this.position.getY());
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public FishType getType() {
		return type;
	}

	public void setType(FishType type) {
		this.type = type;
	}

	public Rectangle getCollision() {
		return collision;
	}

	public void setCollision(Rectangle collision) {
		this.collision = collision;
	}	
	
}
