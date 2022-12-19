package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tent extends Entity {

	GamePanel gp;
	
	public OBJ_Tent(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_consumable;
		name = "Tent";
		down1 = setup("/objects/tent", gp.tileSize, gp.tileSize);
		description = "[Tent]\nUse this to sleep\novernight";
		price = 80;
		stock = true;
	}
	public boolean use(Entity entity) {
		
		gp.gameState = gp.sleepState;
		gp.playSE(12);
		gp.player.life = gp.player.maxLife;
		gp.player.getSleepingImage(down1);
		return true;
	}
}
