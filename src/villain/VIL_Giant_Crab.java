package villain;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Heart;
import object.OBJ_Pile_Golds;
import object.OBJ_Fire;

public class VIL_Giant_Crab extends Entity {

	GamePanel gp;
	
	public VIL_Giant_Crab(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_monsters;
		name = "Giant";
		speed = 1;
		maxLife = 15;
		life = maxLife;
		
		attack = 5;
		defense = 0;
		exp = 2;
		projectile = new OBJ_Fire(gp);
		
		int size = gp.tileSize*9;
		solidArea.x = 48;
		solidArea.y = 48;
		solidArea.width = size - 48 * 2;
		solidArea.height = size - 48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}		
	
	public void getImage() {
		
		int i = 7;
		
		up1 = setup("/monster/giant_left", gp.tileSize*i, gp.tileSize*i);
		up2 = setup("/monster/giant_left", gp.tileSize*i, gp.tileSize*i);
		down1 = setup("/monster/giant_right", gp.tileSize*i, gp.tileSize*i);
		down2 = setup("/monster/giant_right", gp.tileSize*i, gp.tileSize*i);
		left1 = setup("/monster/giant_left", gp.tileSize*i, gp.tileSize*i);
		left2 = setup("/monster/giant_left", gp.tileSize*i, gp.tileSize*i);
		right1 = setup("/monster/giant_right", gp.tileSize*i, gp.tileSize*i);
		right2 = setup("/monster/giant_right",gp.tileSize*i, gp.tileSize*i);
	}
	
	public void setAction() {
		
		actionLockCounter ++;
		if(actionLockCounter == 100) {
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
		int i = new Random().nextInt(100)+1;
		if(i > 99 && projectile.alive == false && shotAvaibleCounter == 30) {
			
			projectile.set(worldX, worldY, direction, true, this);
			gp.projectileList.add(projectile);
			shotAvaibleCounter = 0;
		}
	}
	
	public void damageReaction() {
		actionLockCounter = 0;
		direction = gp.player.direction;
	}
	public void checkDrop() {
		// DIE
		int i = new Random().nextInt(100)+1;
		
		// WHICH BECOMES
		if(i < 50) {
			dropItem(new OBJ_Pile_Golds(gp));
		}
		if(i >= 50 && i < 75) {
			dropItem(new OBJ_Pile_Golds(gp));
		}
		if(i >= 75 && i < 100) {
			dropItem(new OBJ_Pile_Golds(gp));
		}
	}
}
