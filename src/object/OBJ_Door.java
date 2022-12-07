package object;

import java.io.IOException;

import javax.imageio.ImageIO;


//public class OBJ_Door extends Entity {
	public class OBJ_Door extends SuperObject {	
//	public OBJ_Door(GamePanel gp) {
	public OBJ_Door() {
		
//		super(gp);
		
		name = "Door";
//		down1 = setup("/object/door", gp.tileSize, gp.tileSize);
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
		
//		solidArea.x = 0;
//		solidArea.y = 16;
//		solidArea.width = 48;
//		solidArea.height = 32;
//		solidAreaDefaultX = solidArea.x;
//		solidAreaDefaultY = solidArea.y;
	}
}
