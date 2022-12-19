package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Normal extends Entity{
	
	public OBJ_Shield_Normal(GamePanel gp) {
		super(gp);
		
		type = type_shield;
		name = "Wood Shield";
		down1 = setup("/objects/shield_wood", gp.tileSize, gp.tileSize);
		defenseValue = 4;
		description = "[" + name + "]\nalthough of wood this \nshield is very strong";
		price = 15;
	}
}