package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.KeyboardListener;
import object.OBJ_Axe;
import object.OBJ_Key;
import object.OBJ_Lantern;
import object.OBJ_Fire;
import object.OBJ_Shield_Normal;
import object.OBJ_Sword_Normal;

public class Player extends Entity {

	KeyboardListener keyL;
	
	public final int screenX;
	public final int screenY;
	int standCounter = 0;
	public boolean attackCanceled = false;
	public boolean lightUpdated = false;
	
	public Player(GamePanel gp, KeyboardListener keyL) {
		
		super(gp);
		this.keyL = keyL;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		// SOLID AREA
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		// ATTACK AREA
		attackArea.width = 36;
		attackArea.height = 36;
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
		setItems();
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		gp.currentMap = 0;
		speed = 4;
		direction = "down";
		
		// PLAYER STATUS
		level = 1;
		maxLife = 6;
		life = maxLife;
		strength = 1; // The more strength h has, the more damage he gives
		dexterity = 1; // The more dexterity h has, the less damage he receives
		exp = 0;
		nextLevelExp = 5;
		coin = 95;
//		currentWeapon = new OBJ_Sword_Normal(gp);
		currentWeapon = new OBJ_Axe(gp);
		currentShield = new OBJ_Shield_Normal(gp);
		projectile = new OBJ_Fire(gp);
	}
	public void setItems() {
		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);
		inventory.add(new OBJ_Sword_Normal(gp));
		inventory.add(new OBJ_Key(gp));
		inventory.add(new OBJ_Lantern(gp));
	}
	public int getAttack() {
		attackArea = currentWeapon.attackArea;
		return attack = strength * currentWeapon.attackValue;
	}
	public int getDefense() {
		return defense = dexterity * currentShield.defenseValue;
	}
	public void getPlayerImage() {
			
		up1 = setup("/player/depankiri", gp.tileSize, gp.tileSize);
		up2 = setup("/player/depankanan", gp.tileSize, gp.tileSize);
		down1 = setup("/player/depankiri", gp.tileSize, gp.tileSize);
		down2 = setup("/player/depankanan", gp.tileSize, gp.tileSize);
		left1 = setup("/player/sampingkiri", gp.tileSize, gp.tileSize);
		left2 = setup("/player/sampingkirijalan", gp.tileSize, gp.tileSize);
		right1 = setup("/player/sampingkanan", gp.tileSize, gp.tileSize);
		right2 = setup("/player/sampingkananjalan", gp.tileSize, gp.tileSize);	
	}
	public void getSleepingImage(BufferedImage image) {
		
		up1 = image;
		up2 = image;
		down1 = image;
		down2 = image;
		left1 = image;
		left2 = image;
		right1 = image;
		right2 = image;
	}
	public void getPlayerAttackImage() {
		
		if(currentWeapon.type == type_sword) {
			attackUp1 = setup("/player/attack_up_1", gp.tileSize, gp.tileSize*2);
			attackUp2 = setup("/player/attack_up_2", gp.tileSize, gp.tileSize*2);
			attackDown1 = setup("/player/attack_down_1", gp.tileSize, gp.tileSize*2);
			attackDown2 = setup("/player/attack_down_2", gp.tileSize, gp.tileSize*2);
			attackLeft1 = setup("/player/attack_left_1", gp.tileSize*2, gp.tileSize);
			attackLeft2 = setup("/player/attack_left_2", gp.tileSize*2, gp.tileSize);
			attackRight1 = setup("/player/attack_right_1", gp.tileSize*2, gp.tileSize);
			attackRight2 = setup("/player/attack_right_2", gp.tileSize*2, gp.tileSize);
		}
		if(currentWeapon.type == type_axe) {
			attackUp1 = setup("/player/axe_up_1", gp.tileSize, gp.tileSize*2);
			attackUp2 = setup("/player/axe_up_2", gp.tileSize, gp.tileSize*2);
			attackDown1 = setup("/player/axe_down_1", gp.tileSize, gp.tileSize*2);
			attackDown2 = setup("/player/axe_down_2", gp.tileSize, gp.tileSize*2);
			attackLeft1 = setup("/player/axe_left_1", gp.tileSize*2, gp.tileSize);
			attackLeft2 = setup("/player/axe_left_2", gp.tileSize*2, gp.tileSize);
			attackRight1 = setup("/player/axe_right_1", gp.tileSize*2, gp.tileSize);
			attackRight2 = setup("/player/axe_right_2", gp.tileSize*2, gp.tileSize);
		}
	}
	public void update() {
		
		if(attacking == true) {
			attacking();
		}
		else if(keyL.upPressed == true || keyL.downPressed == true || keyL.leftPressed == true 
				|| keyL.rightPressed == true || keyL.enterPressed == true) {	
			
			if (keyL.upPressed == true) {
				direction = "up";
			}
			else if (keyL.downPressed == true) {
				direction = "down";		
			}
			else if (keyL.leftPressed == true) {
				direction = "left";
			}
			else if (keyL.rightPressed == true) {
				direction = "right";
			}
			
			
			// CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			
			// CHECK NPC COLLISION
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			// CHECK MONSTER COLLISION
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			
			// CHECK INTERACTIVE TILE COLLISION
			int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
			
			// CHECK EVENT
			gp.eHandler.checkEvent();
//			gp.keyL.enterPressed = false;
			
			// IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false && keyL.enterPressed == false) {
				
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			
			if(keyL.enterPressed == true && attackCanceled == false) {
//				gp.playSE(7);
				attacking = true;
				spriteCounter = 0;
			}
			attackCanceled = false;
			gp.keyL.enterPressed = false;
			
			spriteCounter++;
			if(spriteCounter > 12) { 
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		else {
			standCounter++;
			if(standCounter == 20) {
				spriteNum = 1;
				standCounter = 0;
			}
		}
		
		// THIS NEEDS TO BE OUTSIDE OF KEY ID STATEMENT
		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		if(shotAvaibleCounter < 30) {
			shotAvaibleCounter++;
		}
		if(life > maxLife) {
			life = maxLife;
		}
	}
	public void attacking() {
		spriteCounter++;
		
		if(spriteCounter <= 5) {
			spriteNum = 1;
		}
		if(spriteCounter > 5 && spriteCounter <= 25) {
			spriteNum = 2;
		}
			// Save the current worldX, worldY, solidArea
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			// Adjust player's worldX/Y for the attackArea
			switch(direction) {
			case "up": worldY -= attackArea.height; break;
			case "down": worldY += attackArea.height; break;
			case "left": worldX -= attackArea.width; break;
			case "right": worldX += attackArea.width; break;
			}
			
			// attackArea becomes solidArea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			
			// Check monster collision with the updated worldX/Y and solidArea
			int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex, attack);
//			damageMonster(monsterIndex);
			
			int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
			damageInteractiveTile(iTileIndex);
			
			// After checking collision, restore the original data
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
//		}
		
		if(spriteCounter > 25) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	
	public void pickUpObject(int i) {
		
		if( i != 999) {
			
			//PICKUP ONLY ITEMS
			if(gp.obj[gp.currentMap][i].type == type_pickupOnly) {
				
				gp.obj[gp.currentMap][i].use(this);
				gp.obj[gp.currentMap][i] = null;
			}
			// OBSTACLE
			else if(gp.obj[gp.currentMap][i].type == type_obstacle) {
				if(keyL.enterPressed == true) {
					attackCanceled = true;
					gp.obj[gp.currentMap][i].interact();
				}
			}
			// INVENTORY
			else {
				String text;
				
				if(canObtainItem(gp.obj[gp.currentMap][i]) == true) {

					gp.playSE(1);
					text = "Got a " + gp.obj[gp.currentMap][i].name + "!";
				}
				else {
					text = "You cannot carry any more!";
				}
				gp.ui.addMessage(text);
				gp.obj[gp.currentMap][i] = null;
			}
		}
	}
	
	public void interactNPC(int i) {
		
		if(gp.keyL.enterPressed == true) {
			if(i != 999) {
				attackCanceled = true;
				gp.gameState = gp.dialogueState;
				gp.npc[gp.currentMap][i].speak();
			}
		}
	}
	
	public void contactMonster(int i) {
		
		if(i != 999) {
			if(invincible == false && gp.monster[gp.currentMap][i].dying == false) {
				gp.playSE(6);
				life -= 1;
				invincible = true;

				int damage = gp.monster[gp.currentMap][i].attack - defense;
				if(damage < 0) {
					damage = 0;
				}
				life -= damage;
				invincible = true;
			}
		}
	}
	
	public void damageMonster(int i, int attack) {
//	public void damageMonster(int i) {	
		
		if(i != 999) {
			if(gp.monster[gp.currentMap][i].invincible == false) {
//				
				gp.playSE(5);
				gp.monster[gp.currentMap][i].life -= 1;
//				gp.monster[i].invincible = true;
//				gp.playSE(5);
				
				int damage = attack - gp.monster[gp.currentMap][i].defense;
				if(damage < 0) {
					damage = 0;
				}
				gp.monster[gp.currentMap][i].life -= damage;
				gp.ui.addMessage("damage!");
				
				gp.monster[gp.currentMap][i].invincible = true;
				gp.monster[gp.currentMap][i].damageReaction();
				
				if(gp.monster[gp.currentMap][i].life <= 0) {
					gp.monster[gp.currentMap][i].dying = true;
					gp.ui.addMessage("killed the " + gp.monster[gp.currentMap][i].name + "!");
					gp.ui.addMessage("Exp + " + gp.monster[gp.currentMap][i].exp);
					exp += gp.monster[gp.currentMap][i].exp;
					checkLevelUp();
				}
			}
		}
	}
	
	public void damageInteractiveTile(int i) {
		
		if(i != 999 && gp.iTile[gp.currentMap][i].destructible == true 
				&& gp.iTile[gp.currentMap][i].isCorrectItem(this) == true && gp.iTile[gp.currentMap][i].invincible == false) {
			
			gp.iTile[gp.currentMap][i].playSE();
			gp.iTile[gp.currentMap][i].life--;
			gp.iTile[gp.currentMap][i].invincible = true;
			
			//Generate particle
			generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);
			
			if(gp.iTile[gp.currentMap][i].life == 0) {
				gp.iTile[gp.currentMap][i].checkDrop();
				gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();
			}
		}
	}
	public void checkLevelUp() {
		if(exp >= nextLevelExp) {
			level++;
			nextLevelExp = nextLevelExp*2;
			maxLife += 2;
			strength++;
			dexterity++;
			attack = getAttack();
			defense = getDefense();
			
			gp.playSE(7);
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialogue = "You are level " + level + " now!\n"
					+ "You feel stronger!";
		}
	}
	
	public void selectItem() {
		
		int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
		if(itemIndex < inventory.size()) {
			Entity selectedItem = inventory.get(itemIndex);
			
			if(selectedItem.type == type_sword || selectedItem.type == type_axe) {
				currentWeapon = selectedItem;
				attack = getAttack();
				getPlayerAttackImage();
			}
			if(selectedItem.type == type_shield) {
				currentShield = selectedItem;
				defense = getDefense();
			} 
			if(selectedItem.type == type_light) {
				if(currentLight == selectedItem) {
					currentLight = null;
				}
				else {
					currentLight = selectedItem;
				}
				lightUpdated = true;
			}
			if(selectedItem.type == type_consumable) {
				if(selectedItem.use(this) == true) {
					if(selectedItem.amount > 1) {
						selectedItem.amount--;
					}
					else {
						inventory.remove(itemIndex);
					}
				}
			}
		}
	}
	public int searchItemInIventory(String itemName) {
		
		int itemIndex = 999;
		
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).name.equals(itemName)) {
				itemIndex = i;
				break;
			}
		}
		return itemIndex;
	}
	public boolean canObtainItem(Entity item) {
		boolean canObtain = false;
		
		// CHECK 
		if(item.stock == true) {
			int index = searchItemInIventory(item.name);
			
			if(index != 999) {
				inventory.get(index).amount++;
				canObtain = true;
			}
			else {
				if(inventory.size() != maxInventorySize) {
					inventory.add(item);
					canObtain = true;
				}
			}
		}
		else {
			if(inventory.size() != maxInventorySize) {
				inventory.add(item);
				canObtain = true;
			}
		}
		return canObtain;
	}
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		
		switch(direction) {
		case "up":
			if(attacking == false) {
				if(spriteNum == 1) {image= up1;}
				if(spriteNum == 2) {image= up2;}
			}
			if(attacking == true) {
				tempScreenY = screenY - gp.tileSize;
				if(spriteNum == 1) {image= attackUp1;}
				if(spriteNum == 2) {image= attackUp2;}	
			}
			break;
		case "down":
			if(attacking == false) {
				if(spriteNum == 1) {image= down1;}
				if(spriteNum == 2) {image= down2;}
			}
			if(attacking == true) {
				if(spriteNum == 1) {image= attackDown1;}
				if(spriteNum == 2) {image= attackDown2;}	
			}
			break;
		case "left":
			if(attacking == false) {
				if(spriteNum == 1) {image= left1;}
				if(spriteNum == 2) {image= left2;}
			}
			if(attacking == true) {
				tempScreenX = screenX - gp.tileSize;
				if(spriteNum == 1) {image= attackLeft1;}
				if(spriteNum == 2) {image= attackLeft2;}	
			}
			break;
		case "right":
			if(attacking == false) {
				if(spriteNum == 1) {image= right1;}
				if(spriteNum == 2) {image= right2;}
			}
			if(attacking == true) {
				if(spriteNum == 1) {image= attackRight1;}
				if(spriteNum == 2) {image= attackRight2;}	
			}
			break;
		}
		
		if(invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		}		
		g2.drawImage(image, tempScreenX, tempScreenY, null);
		
		// RESET ALPHA
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
////		// DEBUG
//		g2.setFont(new Font("Arial", Font.PLAIN, 26));
//		g2.setColor(Color.white);
//		g2.drawString("Invincible:" + invincibleCounter, 10, 400);
	}
}

