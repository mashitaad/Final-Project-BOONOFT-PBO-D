package moves_tile;

import java.awt.Color;
import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Gold;

public class MT_Stone_Quarry extends MovesTile {

	GamePanel gp;
	
	public MT_Stone_Quarry(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		
		down1 = setup("/tiles/stone", gp.tileSize, gp.tileSize);
		destructible = true;
		life = 3;
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
		
		public MovesTile getDestroyedForm() {
			MovesTile tile = null;
			return tile;
		}
		
		public Color getParticleColor() {
			Color color = new Color(65, 65, 65);
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
		public void checkDrop() {
			// IF DIE
			int i = new Random().nextInt(100)+1;
			
			// WHAT BECOMES
			if(i < 50) {
				dropItem(new OBJ_Gold(gp));
			}
			if(i >= 50 && i < 75) {
				dropItem(new OBJ_Gold(gp));
			}
			if(i >= 75 && i < 100) {
				dropItem(new OBJ_Gold(gp));
			}
		}
}

