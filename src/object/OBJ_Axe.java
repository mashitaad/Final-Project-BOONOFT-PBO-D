package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity{
	
	public OBJ_Axe(GamePanel gp) {
		super(gp);
		
		type = type_axe;
		name = "Old Axe";
		down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
		attackValue = 2;
//		attackArea.width = 30;
//		attackArea.height = 30;
		description = "[Old Axe]\nUseful for cutting trees \nto make weapons";
	}
}
