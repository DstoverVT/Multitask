package com.multitask.dannystover;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Stage3Enemies extends StageEntities {
	
	private Updater updater;
	private Color objectColor;
	Random r = new Random();
	private ScoreTracker scoreTracker;
	
	public Stage3Enemies(float ex, float wy, float wid, float ht, String role, Color c, Updater u, ScoreTracker st) {
		super(ex, wy, wid, ht, role);
		updater = u;
		objectColor = c;
		scoreTracker = st;
		
		velX = -3;
	}
	
	/*
	public Stage2Enemies(float ex, float wy, BufferedImage imagey, String role) {
		super(ex, wy, imagey, role);
		
		randomizeVelX();
	}
	*/
	
	
	public Rectangle getRectBorder() {
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
		
	}
	
	public Area getBorder() {
		return new Area(getRectBorder());
	}
	
	
	public void update() {
		x += velX;
		
		if(x <= -100)
			updater.removeStageObject(this);
		if(scoreTracker.getStage() < 3) {
			updater.removeStageObject(this);
		}
	}
	
	public void render(Graphics g) {
		
		g.setColor(objectColor);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
		
	}
	
	
public void randomizeVelX() {
		
		velX = (float)(Math.random() * (3) + 1);
		
	} 

		public void randomizeVelY() {
		
		velY = (float)(Math.random() * (3) + 1);
		
		}  
	
	
}
