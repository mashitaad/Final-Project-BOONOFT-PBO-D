package moves_tile;

import main.GamePanel;

public class MT_Trunk extends MovesTile {

	GamePanel gp;
	
	public MT_Trunk(GamePanel gp, int col, int row) {
		super(gp,col,row);
		this.gp = gp;
		
		this.worldX = gp.tileSize * col;
		this.worldY = gp.tileSize * row;
		
		down1 = setup("/tiles/trunk", gp.tileSize, gp.tileSize);
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 0;
		solidArea.height = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
}
