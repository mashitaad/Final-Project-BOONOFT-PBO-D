package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Gold_Coin extends Entity {

	public OBJ_Gold_Coin(GamePanel gp) {
		super(gp);
		
		type = type_consumable;
		name = "Gold Coin";
		down1 = setup("/objects/gold_coin", gp.tileSize, gp.tileSize);
		defenseValue = 4;
		description = "[" + name + "]\nSparkling gold that all\npirates seek";
		stock = true;
	}

}
