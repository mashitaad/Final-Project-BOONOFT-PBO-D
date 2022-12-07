package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {
	
	public OBJ_Heart(GamePanel gp) {
		super(gp);
		
		name = "Heart";
		image = setup("/objects/heart_full");
		image2 = setup("/objects/heart_half");
		image3 = setup("/objects/heart_blank");
	}

}
//public class OBJ_Heart extends Entity {
//
//	public OBJ_Heart(GamePanel gp) {
//		
//		super(gp);
//		
//		name = "Heart";
//		image = setup("/object/heart_full", gp.tileSize, gp.tileSize);
//		image2 = setup("/object/heart_half", gp.tileSize, gp.tileSize);
//		image3 = setup("/object/heart_blank", gp.tileSize, gp.tileSize);
//
//
//	}
//
//}
