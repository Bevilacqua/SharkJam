package me.bevilacqua.sharkjam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.bevilacqua.sharkjam.Fish.FishType;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import rajola.pipeline.BasicTile;
import rajola.pipeline.Tile;
import rajola.pipeline.level.TileLevel;
import rajola.pipeline.sprites.TileSprite;
import rajola.pipeline.sprites.TileSpriteSheet;
import rajola.pipeline.tools.ImageTools;

public class Play extends BasicGameState {
	private TileLevel testLevel;
	private TileSpriteSheet sheet;
	private Player player;
	private List<Fish> fish = new ArrayList<Fish>();
	
	private Random random;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		sheet = new TileSpriteSheet("res/sheet.png", 32);
		testLevel = new TileLevel(generateTiles(), "res/map.png", new BasicTile(0, new TileSprite(new Image("res/map.png")), 0xFFFFFF));
		player = new Player();
		random = new Random();
		
/*		fish.add(new Fish(new Vector2f(800, 400), Fish.FishType.regular, 300)); //TODO: replace
		fish.add(new Fish(new Vector2f(800, 300), Fish.FishType.dangerous, 300)); //TODO: replace
		fish.add(new Fish(new Vector2f(800, 200), Fish.FishType.regular, 300)); //TODO: replace
*/
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		testLevel.Render();
		
		for(int i = 0 ; i < this.fish.size() ; i++) {
			this.fish.get(i).render(g);
		}
		
		player.render(g);
		
		//GUI
		g.setColor(Color.black);
		g.drawString(("Fish eaten: " + this.player.getScore()), gc.getWidth() - 150, 20);
		g.drawString(("Health: " + this.player.getHealth()), gc.getWidth() - 150, 35);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		testLevel.Update(delta, 0, 0);
		player.update(delta, gc);
		
		if(this.player.getHealth() <= 0) {
			sbg.enterState(Main.States.Title.ordinal());
			this.player = new Player();
			this.fish = new ArrayList<Fish>();
		}
		
		if(this.fish.size() < 7) {
			Vector2f newFishPosition = new Vector2f(random.nextInt(50) + 800, random.nextInt(540 - 32) + 32);
			FishType newFishType = Fish.FishType.regular;
			if(random.nextInt(3) == 0) newFishType = FishType.regular;
			if(random.nextInt(3) == 1) newFishType = FishType.dangerous;
			if(random.nextInt(3) == 2) newFishType = FishType.special;
			this.fish.add(new Fish(newFishPosition, newFishType, random.nextInt(50) + 300));
		}
		
		for(int i = 0 ; i < this.fish.size() ; i++) {
			this.fish.get(i).update(delta);
			
			if(this.fish.get(i).getCollision().getCenterX() <= 0) {
				this.fish.remove(i);
				return;
			}
			
			if(this.player.getCollison().contains(this.fish.get(i).getCollision()) && this.player.getEating()) {				
				this.player.setScore(this.player.getScore() + 1);
				
				switch(this.fish.get(i).getType()) {
				case regular:
					this.player.setHealth(this.player.getHealth() + 1000);
				case dangerous:
					this.player.setHealth(this.player.getHealth() - 500);
				case special:
					this.player.setHealth(this.player.getHealth() + 1500);
				}
				
				this.fish.remove(i);

			}
		}
	}

	@Override
	public int getID() { 
		return Main.States.Play.ordinal();
	}
	
	private Tile[] generateTiles() throws SlickException {
		Tile[] tiles = new Tile[4];
		tiles[0] = new BasicTile(0, new TileSprite(400, ImageTools.imageListCreator(sheet.getTileImage(0, 0), sheet.getTileImage(0,1), sheet.getTileImage(0,2))), 0xFFFFFF);
		tiles[1] = new BasicTile(1, new TileSprite(650, ImageTools.imageListCreator(sheet.getTileImage(1, 0), sheet.getTileImage(1, 1), sheet.getTileImage(1, 2) )), 0x000000);
		tiles[2] = new BasicTile(2, new TileSprite(700, ImageTools.imageListCreator(sheet.getTileImage(2, 0), sheet.getTileImage(2, 1), sheet.getTileImage(2, 2) )), 0x7F3300);
		tiles[3] = new BasicTile(3, new TileSprite(sheet.getTileImage(3, 0)), 0x7F0000);
		return tiles;
	}

}
