package me.bevilacqua.sharkjam;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		sheet = new TileSpriteSheet("res/sheet.png", 32);
		testLevel = new TileLevel(generateTiles(), "res/map.png", new BasicTile(0, new TileSprite(new Image("res/map.png")), 0xFFFFFF));
		player = new Player();
		fish.add(new Fish(new Vector2f(800, 400), Fish.FishType.regular, 300));
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		testLevel.Render();
		
		for(int i = 0 ; i < this.fish.size() ; i++) {
			this.fish.get(i).render(g);
		}
		
		player.render(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		testLevel.Update(delta, 0, 0);
		player.update(delta, gc);
		
		for(int i = 0 ; i < this.fish.size() ; i++) {
			this.fish.get(i).update(delta);
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
