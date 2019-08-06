package com.multitask.dannystover;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Stage4Enemies extends StageEntities {
	
	private Updater updater;
	Random r = new Random();
	private ScoreTracker scoreTracker;
	private int growTimer = 0;
	
	public Stage4Enemies(float ex, float wy, float wid, float ht, BufferedImage imagey, String role, Updater u, ScoreTracker st) {
		super(ex, wy, wid, ht, imagey, role);
		updater = u;
		scoreTracker = st;
		
		randomizeVelX();
		randomizeVelY();
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
		y += velY;
		
		growTimer++;
		
		if(growTimer >= 10) {
			width++;
			height++;
			if(growTimer >= 20) {
				growTimer = 0;
			}
		}
		
		if(x >= Multitask.WIDTH-200 || x <= 0) 
			velX *= -1;
		
		if(y >= Multitask.HEIGHT-200 || y<= 0)
			velY *=-1;
	
		
		x = Multitask.setBounds(x, 0, Multitask.WIDTH - 200);
		y = Multitask.setBounds(y, 0, Multitask.HEIGHT - 200);
		
		if(scoreTracker.getStage() != 4) {
			updater.removeStageObject(this);
		}
	}
	
	public void render(Graphics g) {

		g.drawImage(image, (int)x, (int)y, (int)width, (int)height, null);
		
	}
	
	public void randomizeVelX() {
		
		velX = (float)(Math.random() * (7) - 4);
		
	} 

		public void randomizeVelY() {
		
		velY = (float)(Math.random() * (7) - 4);
		
		}  
	
	
}
