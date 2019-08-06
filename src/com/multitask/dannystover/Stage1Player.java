package com.multitask.dannystover;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

public class Stage1Player extends StageEntities {

	private Spawner spawner;
	private Updater updater;
	private ScoreTracker scoreTracker;
	private Area intersection;
	
	public Stage1Player(float ex, float wy, float wid, float ht, BufferedImage imagey, String role, Spawner sp, Updater upd, ScoreTracker st) {
		super(ex, wy, wid, ht, imagey, role);
		spawner = sp;
		updater = upd;
		scoreTracker = st;
	}
	
	public void update() {

		x += velX;
		y += velY;
		if(y < spawner.stage1Height - 254) {
			velY +=1;
		}
		else
			velY = 0;
		
		velY = Multitask.setBounds(velY, -10, 5);
		//System.out.println(y);
		x = Multitask.setBounds(x, -80, spawner.stage1Width - 240);
		intersection = getBorder();
		
		for(int i = 0; i < updater.allStageObjects.size(); i++) {
			StageEntities temp = updater.allStageObjects.get(i);
			
			if(temp.getRole().equals("Stage1Enemy")) {
				
				intersection.intersect(temp.getBorder());
				
				if(!intersection.isEmpty()) {
					Menu.howDied = "Death by Stage 1 meteor";
					scoreTracker.setGameState(true);
				}

				intersection = new Area(getPolyBorder());
			}
		}
		//stage 3 object collision
		if(scoreTracker.getStage() >= 3) {
			for(int i = 0; i < updater.allStageObjects.size(); i++) {
				StageEntities temp = updater.allStageObjects.get(i);
				
				if(temp.getRole().equals("Stage3Enemy")) {
				
					intersection.intersect(temp.getBorder());
				
					if(!intersection.isEmpty()) {
						Menu.howDied = "Death by Stage 3 ground rectangles";
						scoreTracker.setGameState(true);
					}

					intersection = new Area(getPolyBorder());
				}
			}
		}
		
			
	}
	
	private Polygon getPolyBorder() {
		return new Polygon(new int[] {(int)x+115, (int)x+200, (int)x+200, (int)x+235, (int)x+230, (int)x+190, (int)x+195, (int)x+180, (int)x+160, (int)x+145, (int)x+125, (int)x+130, (int)x+85, (int)x+80, (int)x+115, (int)x+115}, 
				   new int[] {(int)y+34, (int)y+34, (int)y+99, (int)y+134, (int)y+149, (int)y+134, (int)y+219, (int)y+219, (int)y+184, (int)y+220, (int)y+220, (int)y+139, (int)y+154, (int)y+129, (int)y+99, (int)y+34}, 16);
		
	}

	
	public Area getBorder() {
		return new Area(getPolyBorder());
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
