package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pile_Golds extends Entity {

	public OBJ_Pile_Golds(GamePanel gp) {
		super(gp);
		
		type = type_consumable;
		name = "Golds";
		down1 = setup("/objects/pilegold", gp.tileSize, gp.tileSize);
		defenseValue = 4;
		description = "[" + name + "]\nThe pile of gold that\nall pirates coveted!";
		stock = true;
	}

}
