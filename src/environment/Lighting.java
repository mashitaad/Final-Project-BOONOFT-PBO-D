package environment;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import main.GamePanel;

//source : https://www.tutorialspoint.com/javafx/lighting_effect.htm
// https://www.informit.com/articles/article.aspx?p=1639628&seqNum=4

public class Lighting {

	GamePanel gp;
	BufferedImage darknessFilter;
	public int dayCounter;
	public float filterAlpha = 0f;
	public Font maruMonica, purisaB;
	
	// DAY STATE
	public final int day = 0;
	public final int dusk = 1;
	public final int night = 2;
	public final int dawn = 3;
	public int dayState = day;
	
	public Lighting(GamePanel gp) {
		
		this.gp = gp;
		setLightSource();
		
		// CUSTOM FONT 
		try {
			InputStream is = getClass().getResourceAsStream("/font/marumonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setLightSource() {
		darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();
		
		if(gp.player.currentLight == null) {
			g2.setColor(new Color(0,0,0.1f,0.97f));
		}
		else {
			int centerX = gp.player.screenX + (gp.tileSize)/2;
			int centerY = gp.player.screenY + (gp.tileSize)/2;
			
			// GRADATION
			Color color[] = new Color[12];
			float fraction[] = new float[12];
			
			color[0] = new Color(0,0,0.1f,0.1f);
			color[1] = new Color(0,0,0.1f,0.42f);
			color[2] = new Color(0,0,0.1f,0.52f);
			color[3] = new Color(0,0,0.1f,0.61f);
			color[4] = new Color(0,0,0.1f,0.69f);
			color[5] = new Color(0,0,0.1f,0.76f);
			color[6] = new Color(0,0,0.1f,0.82f);
			color[7] = new Color(0,0,0.1f,0.87f);
			color[8] = new Color(0,0,0.1f,0.91f);
			color[9] = new Color(0,0,0.1f,0.92f);
			color[10] = new Color(0,0,0.1f,0.93f);
			color[11] = new Color(0,0,0.1f,0.94f);

			fraction[0] = 0f;
			fraction[1] = 0.4f;
			fraction[2] = 0.5f;
			fraction[3] = 0.6f;
			fraction[4] = 0.65f;
			fraction[5] = 0.7f;
			fraction[6] = 0.75f;
			fraction[7] = 0.8f;
			fraction[8] = 0.85f;
			fraction[9] = 0.9f;
			fraction[10] = 0.95f;
			fraction[11] = 1f;
			
			RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, gp.player.currentLight.lightRadius, fraction, color);
			
			g2.setPaint(gPaint);
		}
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		g2.dispose();
	}
	public void update() {
		if(gp.player.lightUpdated == true) {
			setLightSource();
			gp.player.lightUpdated = false;
		}
		
		if(dayState == day) {
			dayCounter++;
			
			if(dayCounter > 3000) {
				dayState = dusk;
				dayCounter = 0;
			}
		}
		if(dayState == dusk) {
			filterAlpha += 0.005f;
			if(filterAlpha > 0.9f) {
				filterAlpha = 0.9f;
				dayState = night;
			}
		}
		if(dayState == night) {
			dayCounter++;
			if(dayCounter > 5000) {
				dayState = dawn;
				dayCounter = 0;
			}
		}
		if(dayState == dawn) {
			filterAlpha -= 0.005f;
			if(filterAlpha < 0f) {
				filterAlpha = 0;
				dayState = day;
			}
		}
	}
	public void draw(Graphics2D g2) {
		if(gp.currentArea == gp.outside) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
		}
		if(gp.currentArea == gp.outside || gp.currentArea == gp.dungeon) {
			g2.drawImage(darknessFilter, 0, 0, null);
		}
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		// DEBUG
		String situation = "";
		
		switch(dayState) {
		case day : situation = "Day"; break;
		case dusk : situation = "Dusk"; break;
		case night : situation = "Night"; break;
		case dawn : situation = "Dawn"; break;
		}
		
		g2.setFont(maruMonica);
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(50f));
		g2.drawString(situation, 660, 550);
		
	}
}
