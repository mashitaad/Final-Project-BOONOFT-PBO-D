package entity;

import java.awt.Rectangle;

import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Key;
import object.OBJ_Shield_Normal;
import object.OBJ_Sword_Normal;

public class NPC_Merchant extends Entity {

	public NPC_Merchant(GamePanel gp) {
		super(gp);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		getImage();
		setDialogue();
		setItems();
	}
	
	// ASSETS VILLAGENYA JUGA BELUM SEMPURNA
	public void getImage() {
		
		up1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/merchant_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/merchant_down_2", gp.tileSize, gp.tileSize);
	}
	
	public void setDialogue() {
		
		dialogues[0] = "Hello, Pirate!. I have some good stuff.\n Do you want to trade?";
	}
	
	public void setItems() {
		inventory.add(new OBJ_Key(gp));
		inventory.add(new OBJ_Axe(gp));
		inventory.add(new OBJ_Sword_Normal(gp));
		inventory.add(new OBJ_Shield_Normal(gp));
	}
	
	public void speak() {
		
		super.speak();
		gp.gameState = gp.tradeState;
		gp.ui.npc = this;
	}
}
