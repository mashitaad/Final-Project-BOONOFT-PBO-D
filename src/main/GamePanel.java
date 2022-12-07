package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	
	// SCREEN SETTINGS
	final int originalTileSize = 16; //16x16 tile
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; //48x48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; //768 px
	public final int screenHeight = tileSize * maxScreenRow; //576 px
	
	// WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;

	// FPS
	int FPS = 60;
//	
//	// SYSTEM
	TileManager tileM = new TileManager(this);
	public KeyboardListener keyL = new KeyboardListener(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	public SuperObject obj[] = new SuperObject[10];
	Thread gameThread; 
	
	// SET PLAYER DEFAULT POSITION
//	int playerX = 100;
//	int playerY = 100;
//	int playerSpeed = 4;
	
	// ENTITY AND OBJECT
	public Player player = new Player(this,keyL);
//	public Entity obj[] = new Entity[10];
	public Entity npc[] = new Entity[10];
//	public Entity monster[] = new Entity[20]; //NUMBER OF MONSTER WE CAN DISPLAY AT THE MOST TIME
//	public ArrayList<Entity> projectileList = new ArrayList<>();
//	public ArrayList<Entity> entityList = new ArrayList<>();
//	
	// GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
//	public final int characterState = 4;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyL);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		
		aSetter.setObject();
		aSetter.setNPC();
//		aSetter.setMonster();
//		playMusic(5);
//		stopMusic();
		gameState = titleState;
//		gameState = playState;
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
//				System.out.println("FPS : " + drawCount);
				drawCount = 0;
				timer = 0;
			}
			
		}
	}
	
	public void update() {
		
//		if(keyL.upPressed == true) {
//			playerY -= playerSpeed;
//		}
//		else if(keyL.downPressed == true) {
//			playerY += playerSpeed;
//		}
//		else if(keyL.leftPressed == true) {
//			playerX -= playerSpeed;
//		}
//		else if(keyL.rightPressed == true) {
//			playerX += playerSpeed;
//		}
//		
		if(gameState == playState) {
			// PLAYER
			player.update();
			// NPC
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
//			for(int i = 0; i < monster.length; i++) {
//				if(monster[i] != null) {
//					if(monster[i].alive == true && monster[i].dying == false) {
//						monster[i].update();
//					}
//					if(monster[i].alive == false) {
//						monster[i] = null;
//					}
//				}
//			}
//			for(int i = 0; i < projectileList.size() ; i++) {
//				if(projectileList.get(i) != null) {
//					if(projectileList.get(i).alive == true) {
//						projectileList.get(i).update();
//					}
//					if(projectileList.get(i).alive == false) {
//						projectileList.remove(i);
//					}
//				}
//			}
		}
		if(gameState == pauseState) {
			// Nothing 
		}
		
	}
//	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		// DEBUG
		long drawStart = 0;
		if(keyL.checkDrawTime == true ) {
			drawStart = System.nanoTime();
		}
		
		// TITLE SCREEN 
		if(gameState == titleState) {
			ui.draw(g2);
		}
		
		// OTHERS
		else {
			
			// TILE
			tileM.draw(g2);
			//OBJECTS
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					obj[i].draw(g2, this);
				}
			}
			
			// NPC
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].draw(g2);
				}
			}
			// UI
			ui.draw(g2);
			//PLAYER DRAW
			player.draw(g2);
			
		}
					

//			
//			// ADD ETITIES TO THE LIST
//			entityList.add(player);
//			
//			for(int i = 0; i < npc.length; i++) {
//				if(npc[i] != null) {
//					entityList.add(npc[i]);
//				}
//			}
//			
//			for(int i = 0; i < obj.length; i++) {
//				if(obj[i] != null) {
//					entityList.add(obj[i]);
//				}
//			}
//			
//			for(int i = 0; i < monster.length; i++) {
//				if(monster[i] != null) {
//					entityList.add(monster[i]);
//				}
//			}
//			for(int i = 0; i < projectileList.size(); i++) {
//				if(projectileList.get(i) != null) {
//					entityList.add(projectileList.get(i));
//				}
//			}
//			// SORT
//			Collections.sort(entityList, new Comparator<Entity>() {
//
//				@Override
//				public int compare(Entity e1, Entity e2) {
//
//					int result = Integer.compare(e1.worldY, e2.worldY);
//					return result;
//				}
//				
//			});
//			
//			// DRAW ENTITIES
//			for(int i = 0; i < entityList.size(); i++) {
//				entityList.get(i).draw(g2);
//			}
//			
//			// EMPTY ENTITIES LIST
//			entityList.clear();
//		
		
		// DEBUG
		if(keyL.checkDrawTime == true) {
			
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2.setColor(Color.white);
			g2.drawString("Draw Time: " + passed, 10, 400);
			System.out.println("Draw Time: " + passed);
			
		}
		
		g2.dispose();
	}
	
	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		
		music.stop();
	}
	
	public void playSE(int i) {
		
		se.setFile(i);
		se.play();
	}
	
}
