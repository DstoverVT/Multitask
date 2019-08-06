package com.multitask.dannystover;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class Stage2Player extends StageEntities{

	private Spawner spawner;
	private Area intersection;
	private Area headIntersection;
	private Updater updater;
	private ScoreTracker scoreTracker;
	
	public Stage2Player(float ex, float wy, float wid, float ht, BufferedImage imagey, String role, Spawner sp, Updater upd, ScoreTracker st) {
		super(ex, wy, wid, ht, imagey, role);
		spawner = sp;
		updater = upd;
		scoreTracker = st;
		
	}
	
	public Area getBorder() {
		return new Area(getPolyBorder());
	}
	
	public Area getHeadBorder() {
		return new Area(getCircleBorder());
	}
	
	public Polygon getPolyBorder() {
		return new Polygon(new int[] {(int)x+100, (int)x+115, (int)x+200, (int)x+225, (int)x+200, (int)x+180, (int)x+170, (int)x+173, (int)x+170, (int)x+195, (int)x+190, (int)x+165, (int)x+125, (int)x+105, (int)x+107, (int)x+125, (int)x+125, (int)x+100}, 
						   new int[] {(int)y+180, (int)y+200, (int)y+155, (int)y+145, (int)y+140, (int)y+150, (int)y+135, (int)y+125, (int)y+110, (int)y+95, (int)y+90, (int)y+95, (int)y+105, (int)y+120, (int)y+130, (int)y+125, (int)y+170, (int)y+180}, 18);

	}
		
	public Ellipse2D getCircleBorder() {
			return new Ellipse2D.Float(x+120, y+55, 50, 50);
			
		}
	

	
	public void update() {
		y += velY;
		y = Multitask.setBounds(y, 1010-spawner.stage2Height, spawner.stage2Height-230);
		
		if(scoreTracker.getStage() < 2) {
			updater.removeStageObject(this);
		}
		
		intersection = getBorder();
		headIntersection = getHeadBorder();
		
		
		if(scoreTracker.getStage() != 1) {
			for(int i = 0; i < updater.allStageObjects.size(); i++) {
				
				StageEntities temp = updater.allStageObjects.get(i);
			
				if(temp.getRole().equals("Stage2Enemy")) {
				
					intersection.intersect(temp.getBorder());
					headIntersection.intersect(temp.getBorder());
				
					if(!intersection.isEmpty() || !headIntersection.isEmpty()) {
						Menu.howDied = "Death by Stage 2 asteroid";
						scoreTracker.setGameState(true);
					}

					intersection = getBorder();
					headIntersection = getHeadBorder();
				}
			
			}
		}
		
		
	}
	
	
	public void render(Graphics g) {
		
		g.drawImage(image, (int)x, (int)y, (int)width, (int)height, null);
	}
	
public void randomizeVelX() {
		
		velX = (float)(Math.random() * (3) + 1);
		
	} 

		public void randomizeVelY() {
		
		velY = (float)(Math.random() * (3) + 1);
		
		}  
	
}
