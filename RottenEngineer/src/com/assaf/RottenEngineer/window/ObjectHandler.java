package com.assaf.RottenEngineer.window;

import java.awt.Graphics;
import java.util.LinkedList;

import com.assaf.RottenEngineer.framework.GameObject;
import com.assaf.RottenEngineer.framework.ObjectId;
import com.assaf.RottenEngineer.objects.Test;

public class ObjectHandler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	public void tick(){
		for(int i=0;i<object.size();i++){
			tempObject = object.get(i);
			tempObject.tick(object);
		}
	}
	public void render(Graphics graphics){
		for(int i=0;i<object.size();i++){
			tempObject = object.get(i);
			tempObject.render(graphics);
		}
	}
	/**
	 * adding an object to the list of objects
	 * @param object
	 */
	public void addOject(GameObject object){
		this.object.add(object);
	}
	/**
	 * removing an object from the list of objects
	 * @param object
	 */
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	/**
	 * creating a basic scenario
	 */
	public void createLevel(){
		for (int i=0; i<Game.WIDTH-25;i += 25){
			addOject(new Test(i,Game.HEIGHT-25,ObjectId.Test));
		}
	}
}
