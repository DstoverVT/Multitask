package com.multitask.dannystover;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

//every enemy and player created in this game will be a subclass of this abstract class so they
//inherit all its methods and instance variables
public abstract class StageEntities{
	
	protected float x, y, velX, velY, width, height;
	protected String id;
	protected BufferedImage image;
	

	public StageEntities(float ex, float wy, float wid, float ht, BufferedImage imagey, String role) {
		x = ex;
		y = wy;
		image = imagey;
		id = role;
		width = wid;
		height = ht;
	}
	
	public StageEntities(float ex, float wy, float wid, float ht, String role) {
		x = ex;
		y = wy;
		id = role;
		width = wid;
		height = ht;
	}
	
	
	
	/*
	public StageEntities(float ex, float wy, BufferedImage imagey, String role) {
		x = ex;
		y = wy;
		image = imagey;
		id = role;
	}
	*/
	
	//subclasses must 
	public abstract void update();
	public abstract void render(Graphics g);
	public abstract Area getBorder();
	abstract void randomizeVelX();
	abstract void randomizeVelY();
	
	//getter/accessor methods
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public BufferedImage getImage() {
		return image;
	}
	public float getVelX() {
		return velX;
	}
	public float getVelY() {
		return velY;
	}
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}
	public String getRole() {
		return id;
	}
	
	//setter/mutator methods
	public void setX(float x1) {
		x = x1;
	}
	public void setY(float y1) {
		y = y1;
	}
	public void setVelX(float velocityX) {
		velX = velocityX;
	}
	public void setVelY(float velocityY) {
		velY = velocityY;
	}
	public void setImage(BufferedImage imageicon) {
		image = imageicon;
	}
	public void setWidth(float w) {
		width = w;
	}
	public void setHeight(float h) {
		height = h;
	}
	public void setRole(String role) {
		id = role;
	}
	
		
}
