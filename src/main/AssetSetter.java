package main;

import entity.NPC_Merchant;
import entity.NPC_Villager;
import moves_tile.MT_Stone_Quarry;
import moves_tile.MT_Tree;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Gold_Coin;
import object.OBJ_Gold_Chest;
import object.OBJ_Key;
import object.OBJ_Tent;
import villain.VIL_Bat;
import villain.VIL_Giant_Crab;
import villain.VIL_Eye_Monster;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {

		int mapNum = 0;
		int i = 0;
		gp.obj[mapNum][i] = new OBJ_Tent(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 19;
		gp.obj[mapNum][i].worldY = gp.tileSize * 20;
		i++;
//		gp.obj[mapNum][i] = new OBJ_Door(gp);
//		gp.obj[mapNum][i].worldX = gp.tileSize * 14;
//		gp.obj[mapNum][i].worldY = gp.tileSize * 28;
//		i++;
//		gp.obj[mapNum][i] = new OBJ_Door(gp);
//		gp.obj[mapNum][i].worldX = gp.tileSize * 12;
//		gp.obj[mapNum][i].worldY = gp.tileSize * 12;
//		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp, new OBJ_Key(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize * 30;
		gp.obj[mapNum][i].worldY = gp.tileSize * 29;
		i++;
		
		mapNum = 2;
		i = 0;
		gp.obj[mapNum][i] = new OBJ_Gold_Chest(gp, new OBJ_Gold_Coin(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize * 40;
		gp.obj[mapNum][i].worldY = gp.tileSize * 41;
		i++;
		gp.obj[mapNum][i] = new OBJ_Gold_Chest(gp, new OBJ_Gold_Coin(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize * 13;
		gp.obj[mapNum][i].worldY = gp.tileSize * 16;
		i++;
		gp.obj[mapNum][i] = new OBJ_Gold_Chest(gp, new OBJ_Gold_Coin(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize * 26;
		gp.obj[mapNum][i].worldY = gp.tileSize * 15;
		i++;
	}
	
	public void setNPC() {
		int mapNum = 0;
		int i = 0;
		// MAP 0
		gp.npc[mapNum][i] = new NPC_Villager(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 21;
		gp.npc[mapNum][i].worldY = gp.tileSize * 21;
		i++;
		
		// MAP 1
		mapNum = 1;
		i = 0;
		gp.npc[mapNum][i] = new NPC_Merchant(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 12;
		gp.npc[mapNum][i].worldY = gp.tileSize * 7;
		i++;
	}

	public void setMonster() {
		int mapNum = 0;
		int i = 0;
		gp.monster[mapNum][i] = new VIL_Eye_Monster(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*21;
		gp.monster[mapNum][i].worldY = gp.tileSize*38;
		i++;
		
		gp.monster[mapNum][i] = new VIL_Eye_Monster(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*23;
		gp.monster[mapNum][i].worldY = gp.tileSize*42;
		i++;
		
		gp.monster[mapNum][i] = new VIL_Eye_Monster(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*24;
		gp.monster[mapNum][i].worldY = gp.tileSize*37;
		i++;
		
		gp.monster[mapNum][i] = new VIL_Eye_Monster(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*34;
		gp.monster[mapNum][i].worldY = gp.tileSize*42;
		i++;
		
		gp.monster[mapNum][i] = new VIL_Eye_Monster(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*38;
		gp.monster[mapNum][i].worldY = gp.tileSize*42;
		i++;
		
		mapNum = 2;
		i++;
		gp.monster[mapNum][i] = new VIL_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*34;
		gp.monster[mapNum][i].worldY = gp.tileSize*39;
		i++;
		gp.monster[mapNum][i] = new VIL_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*36;
		gp.monster[mapNum][i].worldY = gp.tileSize*25;
		i++;
		gp.monster[mapNum][i] = new VIL_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*39;
		gp.monster[mapNum][i].worldY = gp.tileSize*26;
		i++;
		gp.monster[mapNum][i] = new VIL_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*28;
		gp.monster[mapNum][i].worldY = gp.tileSize*11;
		i++;
		gp.monster[mapNum][i] = new VIL_Bat(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*10;
		gp.monster[mapNum][i].worldY = gp.tileSize*19;
		i++;
		
		mapNum = 3;
		i++;
		gp.monster[mapNum][i] = new VIL_Giant_Crab(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*23;
		gp.monster[mapNum][i].worldY = gp.tileSize*16;
		i++;
	}
	public void setInteractiveTile() {
		int mapNum = 0;
		int i = 0;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 27, 12);i++;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 28, 12);i++;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 29, 12);i++;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 30, 12);i++;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 31, 12);i++;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 32, 12);i++;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 33, 12);i++;
	
		// TREE MENUJU MERCHANT
		gp.iTile[mapNum][i] = new MT_Tree(gp, 18, 40);i++;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 17, 40);i++;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 16, 40);i++;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 15, 40);i++;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 14, 40);i++;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 13, 40);i++;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 13, 41);i++;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 12, 41);i++;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 11, 41);i++;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 10, 41);i++;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 10, 40);i++;
	
		//31 - 27 itu baris
		gp.iTile[mapNum][i] = new MT_Tree(gp, 27, 31);i++;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 28, 31);i++;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 29, 31);i++;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 27, 30);i++;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 27, 29);i++;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 27, 28);i++;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 26, 27);i++;
		gp.iTile[mapNum][i] = new MT_Tree(gp, 25, 27);i++;
		
		// WALL DI MAP 2
		mapNum = 2;
		i = 0;
		
		gp.iTile[mapNum][i] = new MT_Stone_Quarry(gp, 18, 30);i++;
		gp.iTile[mapNum][i] = new MT_Stone_Quarry(gp, 17, 31);i++;
		gp.iTile[mapNum][i] = new MT_Stone_Quarry(gp, 17, 32);i++;
		gp.iTile[mapNum][i] = new MT_Stone_Quarry(gp, 17, 34);i++;
		gp.iTile[mapNum][i] = new MT_Stone_Quarry(gp, 18, 34);i++;
		gp.iTile[mapNum][i] = new MT_Stone_Quarry(gp, 18, 33);i++;
		gp.iTile[mapNum][i] = new MT_Stone_Quarry(gp, 10, 22);i++;
		gp.iTile[mapNum][i] = new MT_Stone_Quarry(gp, 10, 24);i++;
		gp.iTile[mapNum][i] = new MT_Stone_Quarry(gp, 38, 18);i++;
		gp.iTile[mapNum][i] = new MT_Stone_Quarry(gp, 38, 19);i++;
		gp.iTile[mapNum][i] = new MT_Stone_Quarry(gp, 38, 20);i++;
		gp.iTile[mapNum][i] = new MT_Stone_Quarry(gp, 38, 21);i++;
		gp.iTile[mapNum][i] = new MT_Stone_Quarry(gp, 18, 13);i++;
		gp.iTile[mapNum][i] = new MT_Stone_Quarry(gp, 18, 14);i++;
		gp.iTile[mapNum][i] = new MT_Stone_Quarry(gp, 22, 28);i++;
		gp.iTile[mapNum][i] = new MT_Stone_Quarry(gp, 30, 28);i++;
		gp.iTile[mapNum][i] = new MT_Stone_Quarry(gp, 32, 28);i++;
	
		gp.iTile[mapNum][i] = new MT_Stone_Quarry(gp, 20, 22);i++;
		gp.iTile[mapNum][i] = new MT_Stone_Quarry(gp, 8, 17);i++;
		gp.iTile[mapNum][i] = new MT_Stone_Quarry(gp, 39, 31);i++;
	}
}
