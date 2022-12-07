package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.KeyboardListener;

public class Player extends Entity {

	KeyboardListener keyL;
	
	public final int screenX;
	public final int screenY;
	int standCounter = 0;
//	public boolean attackCanceled = false;
//	public ArrayList<Entity> inventory = new ArrayList<>();
//	public final int maxInventorySize = 20;
	
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
//		setItems();
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
//		worldX = gp.tileSize * 10;
//		worldY = gp.tileSize * 13;
		speed = 4;
		direction = "down";
		
		// PLAYER STATUS
//		level = 1;
		maxLife = 6;
		life = maxLife;
//		maxMana = 4;
//		mana = maxMana;
//		ammo = 10;
//		strength = 1; // The more strength h has, the more damage he gives
//		dexterity = 1; // The more dexterity h has, the less damage he receives
//		exp = 0;
//		nextLevelExp = 5;
//		coin = 0;
//		currentWeapon = new OBJ_Sword_Normal(gp);
//		currentShield = new OBJ_Shield_Normal(gp);
//		projectile = new OBJ_Fireball(gp);
////		projectile = new OBJ_Rock(gp);
//		attack = getAttack(); // The total attack value is decided by strength and weapon
//		defense = getDefense(); // The total defense value is decided by dexterity and shield
	}
//	public void setItems() {
//		inventory.add(currentWeapon);
//		inventory.add(currentShield);
//		inventory.add(new OBJ_Key(gp));
//	}
//	public int getAttack() {
//		attackArea = currentWeapon.attackArea;
//		return attack = strength * currentWeapon.attackValue;
//	}
//	public int getDefense() {
//		return defense = dexterity * currentShield.defenseValue;
//	}
	
	public void getPlayerImage() {
		
//		up1 = setup("depankiri");
//		up2 = setup("depankanan");
//		down1 = setup("depankiri");
//		down2 = setup("depankanan");
//		left1 = setup("sampingkiri");
//		left2 = setup("sampingkirijalan");
//		right1 = setup("sampingkanan");
//		right2 = setup("sampingkananjalan");	
		up1 = setup("/player/depankiri", gp.tileSize, gp.tileSize);
		up2 = setup("/player/depankanan", gp.tileSize, gp.tileSize);
		down1 = setup("/player/depankiri", gp.tileSize, gp.tileSize);
		down2 = setup("/player/depankanan", gp.tileSize, gp.tileSize);
		left1 = setup("/player/sampingkiri", gp.tileSize, gp.tileSize);
		left2 = setup("/player/sampingkirijalan", gp.tileSize, gp.tileSize);
		right1 = setup("/player/sampingkanan", gp.tileSize, gp.tileSize);
		right2 = setup("/player/sampingkananjalan", gp.tileSize, gp.tileSize);	
	}
	

	public void getPlayerAttackImage() {
		
		up1 = setup("/player/depankiri", gp.tileSize, gp.tileSize*2);
		up2 = setup("/player/depankanan", gp.tileSize, gp.tileSize*2);
		down1 = setup("/player/depankiri", gp.tileSize, gp.tileSize*2);
		down2 = setup("/player/attack_down_2", gp.tileSize, gp.tileSize*2);
		left1 = setup("/player/sampingkiri", gp.tileSize*2, gp.tileSize);
		left2 = setup("/player/attack_left_2", gp.tileSize*2, gp.tileSize);
		right1 = setup("/player/sampingkanan", gp.tileSize*2, gp.tileSize);
		right2 = setup("/player/attack_right_2", gp.tileSize*2, gp.tileSize);	
//		// PENGATURAN ASSETS BERPEDANGNYA BELUM SEMPURNA
//		if(currentWeapon.type == type_sword) {
//			attackUp1 = setup("/player/depankiri", gp.tileSize, gp.tileSize*2);
//			attackUp2 = setup("/player/depankanan", gp.tileSize, gp.tileSize*2);
//			attackDown1 = setup("/player/depankiri", gp.tileSize, gp.tileSize*2);
//			attackDown2 = setup("/player/attack_down_2", gp.tileSize, gp.tileSize*2);
//			attackLeft1 = setup("/player/sampingkiri", gp.tileSize*2, gp.tileSize);
//			attackLeft2 = setup("/player/attack_left_2", gp.tileSize*2, gp.tileSize);
//			attackRight1 = setup("/player/sampingkanan", gp.tileSize*2, gp.tileSize);
//			attackRight2 = setup("/player/attack_right_2", gp.tileSize*2, gp.tileSize);	
//		}
//		if(currentWeapon.type == type_axe) {
//			attackUp1 = setup("/player/depankiri", gp.tileSize, gp.tileSize*2);
//			attackUp2 = setup("/player/depankanan", gp.tileSize, gp.tileSize*2);
//			attackDown1 = setup("/player/depankiri", gp.tileSize, gp.tileSize*2);
//			attackDown2 = setup("/player/attack_down_2", gp.tileSize, gp.tileSize*2);
//			attackLeft1 = setup("/player/sampingkiri", gp.tileSize*2, gp.tileSize);
//			attackLeft2 = setup("/player/attack_left_2", gp.tileSize*2, gp.tileSize);
//			attackRight1 = setup("/player/sampingkanan", gp.tileSize*2, gp.tileSize);
//			attackRight2 = setup("/player/attack_right_2", gp.tileSize*2, gp.tileSize);
//		}
	}
	
	public void update() {
		
		if(attacking == true) {
			attacking();
		}
		
//		else if(keyL.upPressed == true || keyL.downPressed == true || 
//				keyL.leftPressed == true || keyL.rightPressed == true || keyL.enterPressed == true) {
		else if(keyL.upPressed == true || keyL.downPressed == true || keyL.leftPressed == true 
				|| keyL.rightPressed == true || keyL.enterPressed == true) {	
			
//			if (keyL.upPressed == true) {
//				direction = "up";
//				worldY -= speed;
//			}
//			else if (keyL.downPressed == true) {
//				direction = "down";
//				worldY += speed;
//			}
//			else if (keyL.leftPressed == true) {
//				direction = "left";
//				worldX -= speed;
//			}
//			else if (keyL.rightPressed == true) {
//				direction = "right";
//				worldX += speed;
//			}
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
			
			// CHECK EVENT
			gp.eHandler.checkEvent();
			gp.keyL.enterPressed = false;
			
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
			
//			if(keyL.enterPressed == true && attackCanceled == false) {
//				attacking = true;
//				spriteCounter = 0;
//			}
//			attackCanceled = false;
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
		
//		if(gp.keyL.shotKeyPressed == true && projectile.alive == false 
//				&& shotAvaibleCounter == 30 && projectile.haveResource(this) == true) {
//			
//			// SET DEFAULT COORDINATES, DIRECTION, AND USER
//			projectile.set(worldX, worldY, direction, true, this);
//			
//			// SUBTRACT THE COST (MANA, AMMO ETC).
//			projectile.subtractResource(this);
//			
//			// ADD IT TO THE LIST
//			gp.projectileList.add(projectile);
//			shotAvaibleCounter = 0;
//			gp.playSE(9);
//		}
		
		// THIS NEEDS TO BE OUTSIDE OF KEY ID STATEMENT
		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 60) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
//		if(shotAvaibleCounter < 30) {
//			shotAvaibleCounter++;
//		}
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
//			damageMonster(monsterIndex, attack);
			damageMonster(monsterIndex);
			
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
			

		}
			
//			String text;
//			
//			if(inventory.size() != maxInventorySize) {
//				inventory.add(gp.obj[i]);
//				gp.playSE(1);
//				text = "Got a " + gp.obj[i].name + "!";
//			}
//			else {
//				text = "You cannot carry any more!";
//			}
//			gp.ui.addMessage(text);
//			gp.obj[i] = null;
		
	}
	
	public void interactNPC(int i) {
		
		if(gp.keyL.enterPressed == true) {
			if(i != 999) {
	//			attackCanceled = true;
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
			else {
				attacking = true;   
			}
		}
//		gp.keyL.enterPressed = false;
	}
	
	public void contactMonster(int i) {
		
		if(i != 999) {
			if(invincible == false) {
				life -= 1;
				invincible = true;
			}
			
//			if(invincible == false && gp.monster[i].dying == false) {
//				gp.playSE(6);
//				
//				int damage = gp.monster[i].attack - defense;
//				if(damage < 0) {
//					damage = 0;
//				}
//				life -= damage;
//				invincible = true;
//			}
		}
	}
	
//	public void damageMonster(int i, int attack) {
	public void damageMonster(int i) {	
		
		if(i != 999) {
			if(gp.monster[i].invincible == false) {
//				
				gp.monster[i].life -= 1;
				gp.monster[i].invincible = true;
//				gp.playSE(5);
//				
//				int damage = attack - gp.monster[i].defense;
//				if(damage < 0) {
//					damage = 0;
//				}
//				gp.monster[i].life -= damage;
//				gp.ui.addMessage(damage + " damage!");
//				
//				gp.monster[i].invincible = true;
//				gp.monster[i].damageReaction();
//				
				if(gp.monster[i].life <= 0) {
					gp.monster[i] = null;
//					gp.monster[i].dying = true;
//					gp.ui.addMessage("killed the " + gp.monster[i].name + "!");
//					gp.ui.addMessage("Exp + " + gp.monster[i].exp);
//					exp += gp.monster[i].exp;
//					checkLevelUp();
				}
			}
		}
	}
	
//	public void checkLevelUp() {
//		if(exp >= nextLevelExp) {
//			level++;
//			nextLevelExp = nextLevelExp*2;
//			maxLife += 2;
//			strength++;
//			dexterity++;
//			attack = getAttack();
//			defense = getDefense();
//			
//			gp.playSE(7);
//			gp.gameState = gp.dialogueState;
//			gp.ui.currentDialogue = "You are level " + level + " now!\n"
//					+ "You feel stronger!";
//		}
//	}
//	
//	public void selectItem() {
//		
//		int itemIndex = gp.ui.getItemIndexOnSlot();
//		if(itemIndex < inventory.size()) {
//			Entity selectedItem = inventory.get(itemIndex);
//			
//			if(selectedItem.type == type_sword || selectedItem.type == type_axe) {
//				currentWeapon = selectedItem;
//				attack = getAttack();
//				getPlayerAttackImage();
//			}
//			if(selectedItem.type == type_shield) {
//				currentShield = selectedItem;
//				defense = getDefense();
//			}
//			if(selectedItem.type == type_consumable) {
//				selectedItem.use(this);
//				inventory.remove(itemIndex);
//			}   
//		}
//	}
	
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
		
//		g2.drawImage(image, screenX, screenY, null);
		
		g2.drawImage(image, tempScreenX, tempScreenY, null);
		
		// RESET ALPHA
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
////		// DEBUG
//		g2.setFont(new Font("Arial", Font.PLAIN, 26));
//		g2.setColor(Color.white);
//		g2.drawString("Invincible:" + invincibleCounter, 10, 400);
	}
}

