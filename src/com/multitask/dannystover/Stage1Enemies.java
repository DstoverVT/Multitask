package com.multitask.dannystover;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class Stage1Enemies extends StageEntities {
	
	private Spawner spawner;
	private Updater updater;
	private Menu menu;
	//private Area intersection;

	public Stage1Enemies(float ex, float wy, float wid, float ht, BufferedImage imagey, String role, Spawner spawn, Updater u) {
		super(ex, wy, wid, ht, imagey, role);
		spawner = spawn;
		updater = u;
		
		randomizeVelY();
		//border = poly;
	}
	
	public Stage1Enemies(float ex, float wy, float wid, float ht, BufferedImage imagey, String role, Spawner spawn, Updater u, Menu m) {
		super(ex, wy, wid, ht, imagey, role);
		spawner = spawn;
		updater = u;
		menu = m;
		
		randomizeVelY();
		//border = poly;
	}

	
	/*
	public Polygon getBorder() {
		new Polygon(new int[] {(int)x+45, (int)x+115, (int)x+125, (int)x+75, (int)x+35, (int)x+45},
					new int[] {(int)
	}
	*/
	
	private Ellipse2D getCircleBorder() {
		if(width == spawner.meteorWidthChoices[0]) {
			return new Ellipse2D.Float(x+30, y+74, 67, 65);
		}
		else if(width == spawner.meteorWidthChoices[1]) {
			return new Ellipse2D.Float(x+35, y+84, 72, 70);
		}
		else if(width == spawner.meteorWidthChoices[2]) {
			return new Ellipse2D.Float(x+40, y+94, 77, 75);
		}
		else if(width == spawner.meteorWidthChoices[3]) {
			return new Ellipse2D.Float(x+45, y+104, 82, 80);
		}
		else if(width == spawner.meteorWidthChoices[4]) {
			return new Ellipse2D.Float(x+50, y+114, 87, 85);
		}
		else
			return null;
			
	}
	
	
	public Area getBorder() {
		//return new Rectangle((int)x + 40, (int)y+44, 80, 125);
		//return null;
		return new Area(getCircleBorder());
	}
	
	/*
	public Rectangle getRectBorder() {
		return new Rectangle((int)x + 40, (int)y+44, 80, 125);
	}
	*/

	
	public void update() {
		y += velY;
		
		if(y >= 1100) 
			updater.removeStageObject(this);
		if(menu != null) {
			if(menu.clearMenuObjects)
				updater.removeStageObject(this);
		}
	}
	
	public void render(Graphics g) {

		g.drawImage(image, (int)x, (int)y, (int)width, (int)height, null);
		
	}
	
	public void randomizeVelX() {
	
	velX = (float)(Math.random() * (3) + 2);
	
} 

	public void randomizeVelY() {
	
	velY = (float)(Math.random() * (3) + 2);
	}  
	
	public void reset() {
		y = -150;
	}

}
