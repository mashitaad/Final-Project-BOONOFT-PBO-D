package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity{
//	public class OBJ_Chest extends SuperObject{

	public OBJ_Chest(GamePanel gp) {
		
		super(gp);
		
		name = "Chest";
		down1 = setup("/objects/chest");
//		down1 = setup("/object/chest", gp.tileSize, gp.tileSize);

	}
}