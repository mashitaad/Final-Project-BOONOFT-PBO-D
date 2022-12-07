package object;

import java.io.IOException;

import javax.imageio.ImageIO;
import main.GamePanel;

//public class OBJ_Boots extends Entity{
public class OBJ_Boots extends SuperObject{

	GamePanel gp;
	public OBJ_Boots(GamePanel gp) {
		
		this.gp = gp;
		
//		super(gp);
		
		name = "Boots";
//		down1 = setup("/object/hat", gp.tileSize, gp.tileSize);
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/hat.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
