package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Villager extends Entity {

	public NPC_Villager(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		getImage();
		setDialogue();
	}
	
	// ASSETS VILLAGENYA JUGA BELUM SEMPURNA
	public void getImage() {
		
		up1 = setup("/npc/8", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/7", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/uji", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/5", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/4", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/6", gp.tileSize, gp.tileSize);
	}
	
	public void setDialogue() {
		
		dialogues[0] = "Hello, Pirate!.";
		dialogues[1] = "So you've come to this island\n to find the treasure?";
		dialogues[2] = "introduce, I am one of the residents\n on this island";
		dialogues[3] = "Well, good luck on you.";
	}
	
	public void setAction() {
		
		actionLockCounter ++;
		if(actionLockCounter == 120) {
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
	
	public void speak() {
		
		super.speak();
	}
	
}
