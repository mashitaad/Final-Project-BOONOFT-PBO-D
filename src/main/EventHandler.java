package main;

import entity.Entity;

public class EventHandler {

	GamePanel gp;
	EventRect eventRect[][][];

	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	int tempMap, tempCol, tempRow;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

		int map = 0;
		int col = 0;
		int row = 0;
		
		while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			// MAKE A HOLE
			eventRect[map][col][row] = new EventRect();
			eventRect[map][col][row].x = 23;
			eventRect[map][col][row].y = 23;
			eventRect[map][col][row].width = 2;
			eventRect[map][col][row].height = 2;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
			
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
				
				if(row == gp.maxWorldRow) {
					row = 0;
					map++;
				}
			}
		}
	}
	
	public void checkEvent() {

		// CHECK IF PLAYER IS MORE THAN 1 TILE AWAY FROM THE LAST EVENT
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance > gp.tileSize) {
			canTouchEvent = true;
		}
		
		if(canTouchEvent == true) {
			if(hit(0,27,16,"right") == true) {teleport(gp.dialogueState);}
			else if(hit(0,23,12,"up") == true) {healingPool(gp.dialogueState);}
			else if(hit(0,10,39,"any") == true) {anyteleport(1, 12, 13, gp.indoor);} // ke merchant map
			else if(hit(1,12,13,"any") == true) {anyteleport(0, 10, 39, gp.outside);} //ke map awal = 0
			else if(hit(1,12,9, "up") == true) {speak(gp.npc[1][0]);}
			else if(hit(0,12,9,"any") == true) {anyteleport(2, 9, 41, gp.dungeon);} // monster
			else if(hit(2,9,41,"any") == true) {anyteleport(0, 12, 9, gp.outside);} // ke luar map = 0
			else if(hit(2,8,7,"any") == true) {anyteleport(3, 26, 41, gp.dungeon);}
			else if(hit(3,26,41,"any") == true) {anyteleport(2, 8, 7, gp.dungeon);}
		}

	}
	
	public boolean hit(int map, int col, int row, String reqDirection) {
		
		boolean hit = false;
		
		if(map == gp.currentMap) {
			gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
			gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
			eventRect[map][col][row].x = col*gp.tileSize + eventRect[map][col][row].x;
			eventRect[map][col][row].y = row*gp.tileSize + eventRect[map][col][row].y;
			
			if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
				if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
					hit = true;
					
					previousEventX = gp.player.worldX;
					previousEventY = gp.player.worldY;
				}
			}
			
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;

			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		}
		return hit;
	}
	
	// TELEPORT IN THE SAME MAP
	public void teleport(int gameState) {
		
		gp.gameState = gameState;
		gp.ui.currentDialogue = "Teleport!";
		gp.player.worldX = gp.tileSize*37;
		gp.player.worldY = gp.tileSize*10;
	}
	
	public void healingPool(int gameState) {
		
		if(gp.keyL.enterPressed == true) {
			gp.gameState = gameState;
			gp.player.attackCanceled = true;
			gp.playSE(2);
			gp.ui.currentDialogue = "Are you thristy?, you drink the water.";
			gp.player.life = gp.player.maxLife;
			gp.aSetter.setMonster();
		}
	}
	
	// TELEPORT INTO ANOTHER MAP
	public void anyteleport(int map, int col, int row, int area) {
		
		gp.gameState = gp.transitionState;
		gp.nextArea = area;
		tempMap = map;
		tempCol = col;
		tempRow = row;
		canTouchEvent = false;
		gp.ui.currentDialogue = "Du..du..looking for gold!";
		gp.playSE(11);
	}
	
	public void speak(Entity entity) {
		
		if(gp.keyL.enterPressed == true) {
			gp.gameState = gp.dialogueState;
			gp.player.attackCanceled = true;
			entity.speak();
		}
	}
}
