package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Gold extends Entity {

	public OBJ_Gold(GamePanel gp) {
		super(gp);
		
		type = type_consumable;
		name = "Gold";
		down1 = setup("/objects/gold", gp.tileSize, gp.tileSize);
		defenseValue = 4;
		description = "[" + name + "]\nSparkling gold that all\npirates seek";
		stock = true;
	}

}
