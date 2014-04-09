package com.assaf.RottenEngineer.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.assaf.RottenEngineer.framework.GameObject;
import com.assaf.RottenEngineer.framework.ObjectId;

public class Test extends GameObject{

	public Test(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
	}

	@Override
	public void render(Graphics graphic) {
		graphic.setColor(Color.GREEN);
		//graphic.drawRect((int)x, (int)y, 25, 25);
		graphic.draw3DRect((int)x, (int)y, 50, 50, true);
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,25,25);
	}
}
