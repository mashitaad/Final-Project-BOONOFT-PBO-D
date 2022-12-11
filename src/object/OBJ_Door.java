package object;

import entity.Entity;
import main.GamePanel;

//public class OBJ_Door extends Entity {
	public class OBJ_Door extends Entity {	
		public OBJ_Door(GamePanel gp) {
	//	public OBJ_Door() {
			
			super(gp);
			
			name = "Door";
			down1 = setup("/objects/door", gp.tileSize, gp.tileSize);
	//		down1 = setup("/object/door", gp.tileSize, gp.tileSize);
	
			collision = true;
			   
			solidArea.x = 0;
			solidArea.y = 16;
			solidArea.width = 48;
			solidArea.height = 32;
			solidAreaDefaultX = solidArea.x;
			solidAreaDefaultY = solidArea.y;
		}
}
