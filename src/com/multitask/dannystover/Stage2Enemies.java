package com.multitask.dannystover;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class Stage2Enemies extends StageEntities {
	
	private Updater updater;
	private Spawner spawner;
	private ScoreTracker scoreTracker;

	public Stage2Enemies(float ex, float wy, float wid, float ht, BufferedImage imagey, String role, Updater u, Spawner s, ScoreTracker st) {
		super(ex, wy, wid, ht, imagey, role);
		updater = u;
		spawner = s;
		scoreTracker = st;
		
		randomizeVelX();
	}
	
	/*
	public Stage2Enemies(float ex, float wy, BufferedImage imagey, String role) {
		super(ex, wy, imagey, role);
		
		randomizeVelX();
	}
	*/
	
	
	public Shape getPolyBorder() {
		if(width == spawner.asteroidWidthChoices[0]) {
			return new Polygon(new int[] {(int)x, (int)x+35, (int)x+55, (int)x+110, (int)x+140, (int)x+105, (int)x+60, (int)x+15, (int)x},
							   new int[] {(int)y+70, (int)y+10, (int)y, (int)y, (int)y+55, (int)y+85, (int)y+125, (int)y+110, (int)y+70}, 9);
		}
		else if(width == spawner.asteroidWidthChoices[1]) {
			return getAsteroid2Border();
		}
		else if(width == spawner.asteroidWidthChoices[2]) {
			return new Polygon(new int[] {(int)x+30, (int)x+45, (int)x+120, (int)x+150, (int)x+150, (int)x+138, (int)x+100, (int)x+60, (int)x+30},
							   new int[] {(int)y+25, (int)y+35, (int)y+60, (int)y+85, (int)y+85, (int)y+110, (int)y+105, (int)y+65, (int)y+25}, 9);
		}
		else if(width == spawner.asteroidWidthChoices[3]) {
			return new Polygon(new int[] {(int)x+20, (int)x+35, (int)x+55, (int)x+90, (int)x+125, (int)x+140, (int)x+145, (int)x+125, (int)x+50, (int)x+20,(int)x+20},
							   new int[] {(int)y+50, (int)y+20, (int)y+5, (int)y+5, (int)y+35, (int)y+40, (int)y+60, (int)y+120, (int)y+120, (int)y+90, (int)y+50}, 11);
		}
		else if(width == spawner.asteroidWidthChoices[4]) {
			return new Polygon(new int[] {(int)x+5, (int)x+35, (int)x+150, (int)x+130, (int)x+130, (int)x+115, (int)x+55, (int)x+5, (int)x+5},
							   new int[] {(int)y+30, (int)y+17, (int)y+17, (int)y+55, (int)y+73, (int)y+110, (int)y+125, (int)y+90, (int)y+30}, 9);	
		}
		else if(width == spawner.asteroidWidthChoices[5]) {
			return new Polygon(new int[] {(int)x+10, (int)x+35, (int)x+38, (int)x+65, (int)x+135, (int)x+115, (int)x+80, (int)x+20, (int)x+10, (int)x+10},
							   new int[] {(int)y+55, (int)y+35, (int)y+15, (int)y+35, (int)y+50, (int)y+85, (int)y+115, (int)y+100, (int)y+75, (int)y+55}, 10);
		}
		else
			return new Polygon(new int[] {(int)x+20, (int)x+35, (int)x+55, (int)x+90, (int)x+125, (int)x+140, (int)x+145, (int)x+125, (int)x+50, (int)x+20,(int)x+20},
					   new int[] {(int)y+50, (int)y+20, (int)y+5, (int)y+5, (int)y+35, (int)y+40, (int)y+60, (int)y+120, (int)y+120, (int)y+90, (int)y+50}, 11);
	}
	
	
	private Ellipse2D getAsteroid2Border() {
		return new Ellipse2D.Float(x, y, width, height);
	}
	
	public Area getBorder() {
		return new Area(getPolyBorder());
	}
	
	
	public void update() {
		x += velX;
		
		if(x <= Multitask.WIDTH/2 - 10)
			updater.removeStageObject(this);
		if(scoreTracker.getStage() < 2) {
			updater.removeStageObject(this);
		}
	}
	
	public void render(Graphics g) {

		g.drawImage(image, (int)x, (int)y, (int)width, (int)height, null);
		
	}
	
	public void randomizeVelX() {
	
	velX = (float)((Math.random() * (3) + 2)*-1);
	
} 

	public void randomizeVelY() {
	
	velY = (float)(Math.random() * (3) + 2);
	}  
	
}
