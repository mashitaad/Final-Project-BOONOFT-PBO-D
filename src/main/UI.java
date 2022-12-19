package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import entity.Entity;
import object.OBJ_Coin;
import object.OBJ_Heart;

// source : https://www.sanfoundry.com/java-program-create-menu-with-several-menu-items/
// https://stackoverflow.com/questions/5652344/how-can-i-use-a-custom-font-in-java
//https://www.oreilly.com/library/view/learning-java/1565927184/ch17s06.html

public class UI {

	GamePanel gp;
	Font arial_40, arial_80B;
	Graphics2D g2;
	public Font maruMonica;
	BufferedImage heart_full, heart_half, heart_blank, coin;
	public boolean messageOn = false;
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	public boolean gameFinished = false;

	public String currentDialogue = "";
	public int commandNum = 0;
	public int titleScreenState = 0; // 0: THE FIRST SCREEN, 1: THE SECOND SCREEN
	public int playerSlotCol = 0;
	public int playerSlotRow = 0;
	public int npcSlotCol = 0;
	public int npcSlotRow = 0;
	int subState = 0;
	int counter = 0;
	public Entity npc;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);

		// CUSTOM FONT 
		try {
			InputStream is = getClass().getResourceAsStream("/font/marumonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// CREATE HUD OBJECT
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
		Entity goldCoin = new OBJ_Coin(gp);
		coin = goldCoin.down1;
	}

	public void addMessage(String text) {
		message.add(text);
		messageCounter.add(0);
	}
	
	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
	
		g2.setFont(maruMonica);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		
		// TITLE STATE
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		// PLAY STATE
		if(gp.gameState == gp.playState) {
			drawPlayerLife();
			drawMessage();
		}
		// PAUSE STATE
		if(gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		// DIALOGUE STATE
		if(gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
		// CHARACTER STATE
		if(gp.gameState == gp.characterState) {
			drawCharacterScreen();
			drawInventory(gp.player, true);
		}
		// TRANSITION STATE
		if(gp.gameState == gp.transitionState) {
			drawTransition();
		}
		// TRANSITION STATE
		if(gp.gameState == gp.tradeState) {
			drawTradeScreen();
		}
		// SLEEP STATE
		if(gp.gameState == gp.sleepState) {
			drawSleepScreen();
		}
	}
	
	public void drawPlayerLife() {

		gp.player.life = 5;
		
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		
		// DRAW BLANK HEART
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		
		// RESET
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		
		// DRAW CURRENT LIFE
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
				
			}
			i++;
			x += gp.tileSize;
		}
	}

	public void drawMessage() {
		int messageX = gp.tileSize;
		int messageY = gp.tileSize*4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
		
		for(int i = 0; i < message.size(); i++) {
			if(message.get(i) != null) {
				
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX+2, messageY+2);
				
				g2.setColor(Color.WHITE);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i) + 1; // messageCounter++
				messageCounter.set(i, counter); // set the counter to the array
				messageY += 50;
				
				if(messageCounter.get(i) > 180) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}
	}
	
	public void drawTitleScreen() {
		
		if(titleScreenState == 0) {
			g2.setColor(new Color(70, 120, 80));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			
			// TITLE NAME
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
			String text = "Royal Valley";
			int x = getXforCenteredText(text);
			int y = gp.tileSize*3;
			
			// SHADOW
			g2.setColor(Color.gray);
			g2.drawString(text, x+5, y+5);
			
			// MAIN COLOR
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			// PIRATE IMAGE
			x = gp.screenWidth/2 - (gp.tileSize*2)/2; //IMAGE IN CENTER
			y += gp.tileSize*2;
			g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
			
			// MENU
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
			
			text = "START";
			x = getXforCenteredText(text);
			y += gp.tileSize*3.5;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "ABOUT GAME";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "QUIT";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tileSize, y);
			}
		}
	}
	
	public void drawPauseScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	
	public void drawDialogueScreen() {
		
		// WINDOW 
		int x = gp.tileSize * 3;
		int y = gp.tileSize / 2;
		int width =  gp.screenWidth - (gp.tileSize * 6);
		int height = gp.tileSize * 4;
		
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
	}
	
	public void drawCharacterScreen() {
		// CREATE A FRAME
		final int frameX = gp.tileSize;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize*5;
		final int frameHeight = gp.tileSize*10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32f));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 35;
		
		// NAMES
		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Life", textX, textY);
		textY += lineHeight;
		g2.drawString("Strength", textX, textY);
		textY += lineHeight;
		g2.drawString("Dexterity", textX, textY);
		textY += lineHeight;
		g2.drawString("Attack", textX, textY);
		textY += lineHeight;
		g2.drawString("Defense", textX, textY);
		textY += lineHeight;
		g2.drawString("Exp", textX, textY);
		textY += lineHeight;
		g2.drawString("Next Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Coin", textX, textY);
		textY += lineHeight + 10;
		g2.drawString("Weapon", textX, textY);
		textY += lineHeight + 15;
		g2.drawString("Shield", textX, textY);
		textY += lineHeight;
		
		// VALUES
		int tailX = (frameX + frameWidth) - 30;
		// Reset textY
		textY = frameY + gp.tileSize;
		String value;
		
		value = String.valueOf(gp.player.level);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, tailX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.life);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, tailX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.strength);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, tailX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.dexterity);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, tailX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.attack);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, tailX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.defense);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, tailX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.exp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, tailX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, tailX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.coin);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, tailX, textY);
		textY += lineHeight;
		
		g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 24, null);
		textY += gp.tileSize;
		g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 24, null);
	}
	
	public void drawInventory(Entity entity, boolean cursor) {
		
		int frameX = 0;
		int frameY = 0;
		int frameWidth = 0;
		int frameHeight = 0;
		int slotCol = 0;
		int slotRow = 0;
		if(entity == gp.player) {
			frameX = gp.tileSize*9;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize*6;
			frameHeight = gp.tileSize*5;
			slotCol = playerSlotCol;
			slotRow = playerSlotRow;
		}
		else {
			frameX = gp.tileSize*2;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize*6;
			frameHeight = gp.tileSize*5;
			slotCol = npcSlotCol;
			slotRow = npcSlotRow;
		}
		
		// FRAME 
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// SLOT
		final int slotXstart = frameX + 20;
		final int slotYstart = frameY + 20;
		int slotX = slotXstart;
		int slotY = slotYstart;
		int slotSize = gp.tileSize;
		
		// DRAW PLAYER'S ITEMS
		for(int i = 0; i < entity.inventory.size(); i++) {
			// EQUIP CURSOR
			if(entity.inventory.get(i) == entity.currentWeapon ||
					entity.inventory.get(i) == entity.currentShield ||
					entity.inventory.get(i) == entity.currentLight) {
				g2.setColor(new Color(240, 190, 90));
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}
			
			g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
			
			// DISPLAY AMOUNT
			if(entity == gp.player && entity.inventory.get(i).amount > 1) {
				g2.setFont(g2.getFont().deriveFont(32f));
				int amountX;
				int amountY;
				
				String s = "" + entity.inventory.get(i).amount;
				amountX = getXforAlignToRightText(s, slotX + 44);
				amountY = slotY + gp.tileSize;
				// SHADOW
				g2.setColor(new Color(60,60,60));
				g2.drawString(s, amountX, amountY);
				// NUMBER
				g2.setColor(Color.white);
				g2.drawString(s, amountX-3, amountY-3);
			}
			
			slotX += gp.tileSize;
			
			if(i == 4 || i == 9 || i == 14) {
				slotX = slotXstart;
				slotY += gp.tileSize;
			}
		}
		// CURSOR
		if(cursor == true) {
			
			int cursorX = slotXstart + (slotSize * slotCol);
			int cursorY = slotYstart + (slotSize * slotRow);
			int cursorWidth = gp.tileSize;
			int cursorHeight = gp.tileSize;
			
			// DRAW CURSOR
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(3));
			g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
			
			// DECSRIPTION FRAME
			int dFrameX = frameX;
			int dFrameY = frameY + frameHeight;
			int dFrameWidth = frameWidth;
			int dFrameHeight = gp.tileSize*3;
			
			// DRAW DESCRIPTION TEXT
			int textX = dFrameX + 20;
			int textY = dFrameY + gp.tileSize;
			g2.setFont(g2.getFont().deriveFont(28F));
			
			int itemIndex = getItemIndexOnSlot(slotCol, slotRow);
			
			if(itemIndex < entity.inventory.size()) {
				drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
				
				for(String line : entity.inventory.get(itemIndex).description.split("\n")){
					g2.drawString(line, textX, textY);
					textY += 32;
				}
			}
		}
	}
	public void drawTransition() {
		 
		counter++;
		g2.setColor(new Color  (0,0,0, counter * 5));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);;
		
		if(counter == 50) {
			counter = 0;
			gp.gameState = gp.playState;
			gp.currentMap = gp.eHandler.tempMap;
			gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
			gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
			gp.eHandler.previousEventX = gp.player.worldX;
			gp.eHandler.previousEventY = gp.player.worldY;
			gp.changeArea();
		}
	}
	public void drawTradeScreen() {
		
		switch(subState) {
		case 0 : trade_select(); break;
		case 1 : trade_buy(); break;
		case 2 : trade_sell(); break;
		}
		gp.keyL.enterPressed = false;
	}
	public void trade_select() {
		
		drawDialogueScreen();
		
		// DRAW WINDOW
		int x = gp.tileSize * 10;
		int y = gp.tileSize * 5;
		int width = gp.tileSize * 3;
		int height = (int)(gp.tileSize * 3.5);
		drawSubWindow(x, y, width, height);
		
		// DRAW TEXTS
		x += gp.tileSize;
		y += gp.tileSize;
		g2.drawString("Buy", x, y);
		if(commandNum == 0) {
			g2.drawString(">", x - 24, y);
			if(gp.keyL.enterPressed == true) {
				subState = 1;
			}
		}
		y+= gp.tileSize;
		
		g2.drawString("Sell", x, y);
		if(commandNum == 1) {
			g2.drawString(">", x - 24, y);
			if(gp.keyL.enterPressed == true) {
				subState = 2;
			}
		}
		y+= gp.tileSize;
		
		g2.drawString("Leave", x, y);
		if(commandNum == 2) {
			g2.drawString(">", x - 24, y);
			if(gp.keyL.enterPressed == true) {
				commandNum = 0;
				gp.gameState = gp.dialogueState;
				currentDialogue = "Dont forget to come again!";
			}
		}
	}
	public void trade_buy() {
		
		// DRAW PLAYER INVENTORY
		drawInventory(gp.player, false);
		// DRAW NPC INVENTORY
		drawInventory(npc, true);
		
		// DRAW HINT WINDOW
		int x = gp.tileSize*2;
		int y = gp.tileSize*9;
		int width = gp.tileSize*6;
		int height = gp.tileSize*2;
		drawSubWindow(x,y,width,height);
		g2.drawString("[ESC] Back", x+24, y+60);
		
		// DRAW PLAYER COIN WINDOW
		x = gp.tileSize * 9;
		y = gp.tileSize * 9;
		width = gp.tileSize*6;
		height = gp.tileSize*2;
		drawSubWindow(x,y,width,height);
		g2.drawString("Your Coin: " + gp.player.coin, x+24, y+60);

		// DRAW PRICE WINDOW
		int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
		if(itemIndex < npc.inventory.size()) {
			
			x = (int)(gp.tileSize*5.5);
			y = (int)(gp.tileSize*5.5);
			width = (int)(gp.tileSize*2.5);
			height = gp.tileSize;
			drawSubWindow(x,y,width,height);
			g2.drawImage(coin, x+10, y+8, 32, 32, null);
			
			int price = npc.inventory.get(itemIndex).price;
			String text = "" + price;
			x = getXforAlignToRightText(text, gp.tileSize * 8 - 20);
			g2.drawString(text, x, y+34);
		
			// BUY AN ITEM
			if(gp.keyL.enterPressed == true) {
				if(npc.inventory.get(itemIndex).price > gp.player.coin) {
					subState = 0;
					gp.gameState = gp.dialogueState;
					currentDialogue = "You need more coin to buy that!";
					drawDialogueScreen();
				}
				else {
					if(gp.player.canObtainItem(npc.inventory.get(itemIndex)) == true) {
						gp.player.coin -= npc.inventory.get(itemIndex).price;
					}
					else {
						subState = 0;
						gp.gameState = gp.dialogueState;
						currentDialogue = "You can't carry anymore!";
					}
				}
			}
		}
	}
	public void trade_sell() {
		
		// DRAW PLAYER INVENTORY
		drawInventory(gp.player, true);
		
		int x;
		int y;
		int height;
		int width;
		
		// DRAW HINT WINDOW
		x = gp.tileSize*2;
		y = gp.tileSize*9;
		width = gp.tileSize*6;
		height = gp.tileSize*2;
		drawSubWindow(x,y,width,height);
		g2.drawString("Click ESC to back", x+24, y+60);
		
		// DRAW PLAYER COIN WINDOW
		x = gp.tileSize * 9;
		y = gp.tileSize * 9;
		width = gp.tileSize*6;
		height = gp.tileSize*2;
		drawSubWindow(x,y,width,height);
		g2.drawString("Your Coin: " + gp.player.coin, x+24, y+60);

		// DRAW PRICE WINDOW
		int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
		if(itemIndex < gp.player.inventory.size()) {
			
			x = (int)(gp.tileSize*12.5);
			y = (int)(gp.tileSize*5.5);
			width = (int)(gp.tileSize*2.5);
			height = gp.tileSize;
			drawSubWindow(x,y,width,height);
			g2.drawImage(coin, x+10, y+8, 32, 32, null);
			
			int price = gp.player.inventory.get(itemIndex).price/2;
			String text = "" + price;
			x = getXforAlignToRightText(text, gp.tileSize * 14 - 20);
			g2.drawString(text, x+13, y+34);
		
			// SELL AN ITEM
			if(gp.keyL.enterPressed == true) {

				if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon ||
						gp.player.inventory.get(itemIndex) == gp.player.currentShield) {
					commandNum = 0;
					subState = 0;
					gp.gameState = gp.dialogueState;
					currentDialogue = "You can't sell this item!";
				}
				else {
					if(gp.player.inventory.get(itemIndex).amount > 1) {
						gp.player.inventory.get(itemIndex).amount--;
					}
					else {
						gp.player.inventory.remove(itemIndex);
					}
					gp.player.coin += price;
				}
			}
		}	
	}
	public void drawSleepScreen() {
		
		counter++;
		
		if(counter < 120) {
			gp.eManager.lighting.filterAlpha += 0.01f;
			if(gp.eManager.lighting.filterAlpha > 1f) {
				gp.eManager.lighting.filterAlpha = 1f;
			}
		}
		if(counter >= 120) {
			gp.eManager.lighting.filterAlpha -= 0.01f;
			if(gp.eManager.lighting.filterAlpha <= 0f) {
				gp.eManager.lighting.filterAlpha = 0f;
				counter = 0;
				gp.eManager.lighting.dayState = gp.eManager.lighting.day;
				gp.eManager.lighting.dayCounter = 0;
				gp.gameState = gp.playState;
				gp.player.getPlayerImage();
			}
		}
	}
	public int getItemIndexOnSlot(int slotCol, int slotRow) {
		int itemIndex = slotCol + (slotRow*5);
		return itemIndex;
	}
	public void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(139,69,19,210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	
	public int getXforCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
	
	public int getXforAlignToRightText(String text, int tailX) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
}
		