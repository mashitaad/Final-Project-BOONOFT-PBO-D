package tile_interactive;

import java.awt.Color;

import entity.Entity;
import main.GamePanel;

public class Tree extends InteractiveTile {

	GamePanel gp;
	
	public Tree(GamePanel gp, int col, int row) {
		super(gp,col,row);
		this.gp = gp;
		
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		
		down1 = setup("/tiles/newtree", gp.tileSize, gp.tileSize);
		destructible = true;
		life = 4;
	}
	
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		if(entity.currentWeapon.type == type_axe) {
			isCorrectItem = true;
		}
		return isCorrectItem;
	}
	
	public void playSE() {
		gp.playSE(10);
	}
	
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = new Trunk(gp, worldX / gp.tileSize, worldY / gp.tileSize);
		return tile;
	}
	
	public Color getParticleColor() {
		Color color = new Color(65, 50, 30);
		return color;
	}
	public int getParticleSize() {
		int size = 6;
		return size;
	}
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}
	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
	}
}
