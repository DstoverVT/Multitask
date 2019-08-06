package com.multitask.dannystover;

import java.awt.Graphics;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.model.core.ID;

public class Updater {

	// this list is all the Updater is, it holds all the GameObjects in the game and
	// ensures they are updated each frame.
	ArrayList<StageEntities> allStageObjects = new ArrayList<StageEntities>();

	// called in Multitask class by Updater
	// this stage1ObjectsUpdate method goes through each object and calls its
	// update() method,
	// updating it depending on what is in its update() method
	public void stage1ObjectsUpdate() {

		// loops through each object in ArrayList
		for (int i = 0; i < allStageObjects.size(); i++) {
			// sets temporary object holder to the current object in list, not necessary but
			// okay.
			StageEntities temp = allStageObjects.get(i);

			// runs the stage 1 object's update method
			if (temp.getRole().substring(0, 6).equals("Stage1"))
				temp.update();
		}

	}

	public void stage2ObjectsUpdate() {

		// loops through each object in ArrayList
		for (int i = 0; i < allStageObjects.size(); i++) {
			// sets temporary object holder to the current object in list, not necessary but
			// easier.
			StageEntities temp = allStageObjects.get(i);

			// runs t
			if (temp.getRole().substring(0, 6).equals("Stage2"))
				temp.update();
		}

	}
	public void stage3ObjectsUpdate() {

		// loops through each object in ArrayList
		for (int i = 0; i < allStageObjects.size(); i++) {
			// sets temporary object holder to the current object in list, not necessary but
			// easier.
			StageEntities temp = allStageObjects.get(i);

			// runs t
			if (temp.getRole().substring(0, 6).equals("Stage3"))
				temp.update();
		}

	}
	
	public void stage4ObjectsUpdate() {

		// loops through each object in ArrayList
		for (int i = 0; i < allStageObjects.size(); i++) {
			// sets temporary object holder to the current object in list, not necessary but
			// easier.
			StageEntities temp = allStageObjects.get(i);

			// runs t
			if (temp.getRole().substring(0, 6).equals("Stage4"))
				temp.update();
		}

	}

	// called in Multitask class by Updater
	// this stage1ObjectsRender method goes through each object and calls its own
	// render method for its graphics
	public void stage1ObjectsRender(Graphics g) {

		for (int i = 0; i < allStageObjects.size(); i++) {

			StageEntities temp = allStageObjects.get(i);

			// runs render method on all objects
			if (temp.getRole().substring(0, 6).equals("Stage1"))
				temp.render(g);
		}

	}

	public void stage2ObjectsRender(Graphics g) {

		for (int i = 0; i < allStageObjects.size(); i++) {

			StageEntities temp = allStageObjects.get(i);

			// runs render method on all objects
			if (temp.getRole().substring(0, 6).equals("Stage2"))
				temp.render(g);
		}

	}

	public void stage3ObjectsRender(Graphics g) {

		for (int i = 0; i < allStageObjects.size(); i++) {

			StageEntities temp = allStageObjects.get(i);

			// runs render method on all objects
			if (temp.getRole().substring(0, 6).equals("Stage3"))
				temp.render(g);
		}

	}

	public void stage4ObjectsRender(Graphics g) {

		for (int i = 0; i < allStageObjects.size(); i++) {

			StageEntities temp = allStageObjects.get(i);

			// runs render method on all objects
			if (temp.getRole().substring(0, 6).equals("Stage4"))
				temp.render(g);
		}

	}
	
	public void removeAllStage1Enemies() {
		for (int i = 0; i < allStageObjects.size(); i++) {
			StageEntities temp = allStageObjects.get(i);

			if (temp.getRole().equals("Stage1Enemy")) {
				removeStageObject(temp);
			}
		}
	}

	public void removeAllStage2Enemies() {
		for (int i = 0; i < allStageObjects.size(); i++) {
			StageEntities temp = allStageObjects.get(i);

			if (temp.getRole().equals("Stage2Enemy")) {
				removeStageObject(temp);
			}
		}
	}
	
	public void removeAllStage2Objects() {
		for (int i = 0; i < allStageObjects.size(); i++) {
			StageEntities temp = allStageObjects.get(i);

			if (temp.getRole().substring(0, 6).equals("Stage2")) {
				removeStageObject(temp);
			}
		}
	}
	
	public void removeAllObjects() {
		for (int i = 0; i < allStageObjects.size(); i++) {
			allStageObjects.remove(i);
		}
	}

	public void addStageObject(StageEntities object) {
		this.allStageObjects.add(object);
	}

	public void removeStageObject(StageEntities object) {
		this.allStageObjects.remove(object);
	}

}
