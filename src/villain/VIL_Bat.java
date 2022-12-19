package villain;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
public class VIL_Bat extends Entity {

	GamePanel gp;
	
	public VIL_Bat(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		type = type_monsters;
		name = "Bat";
		speed = 1;
		maxLife = 3;
		life = maxLife;
		
		attack = 5;
		defense = 0;
		exp = 2;
		
		solidArea.x = 3;
		solidArea.y = 15;
		solidArea.width = 42;
		solidArea.height = 21;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/bat_down_2",gp.tileSize, gp.tileSize);
	}
	
	public void setAction() {
		
		actionLockCounter ++;
		if(actionLockCounter == 10) {
			Random random = new Random();
			int i = random.nextInt(100)+1;
			
			if(i <= 25 ) {
				direction = "up";
			}
			if(i > 25 && i <= 50) {
				direction = "down";
			}
			if(i > 50 && i <= 75) {
				direction = "left";
			}
			if(i > 75 && i <= 100) {
				direction = "right";
			}
			actionLockCounter = 0;
		}
	}
	
	public void damageReaction() {
		actionLockCounter = 0;
		direction = gp.player.direction;
	}
}
