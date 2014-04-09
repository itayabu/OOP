package com.assaf.RottenEngineer.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.assaf.RottenEngineer.framework.GameObject;
import com.assaf.RottenEngineer.framework.ObjectId;

public class Player extends GameObject{
	
	private float width = 15, height = 40;
	

	public Player(float x, float y, ObjectId id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		
	}

	public void render(Graphics graphic) {
		graphic.setColor(Color.white);
		graphic.fillRect((int)x, (int)y, (int)width, (int)height);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,(int)width,(int)height);
	}
	
}
