package com.assaf.RottenEngineer.framework;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {
	protected float x, y;
	protected float velocityX = 0 , velocityY = 0;
	protected ObjectId id;
	
	public GameObject (float x, float y, ObjectId id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	/**
	 * 
	 * @param object
	 */
	public abstract void tick (LinkedList<GameObject> object);
	/**
	 * 
	 * @param graphic
	 */
	public abstract void render (Graphics graphic);
	/**
	 * collision bounding for the play
	 * @return
	 */
	public abstract Rectangle getBounds();
	
	/**
	 * get x location
	 * @return
	 */
	public float getX(){
		return x;
	}
	/**
	 * get y location
	 * @return
	 */
	public float getY(){
		return y;
	}
	/**
	 * set x location
	 * @param x
	 */
	public void setX(float x){
		this.x = x;
	}
	/**
	 * set y location
	 * @param y
	 */
	public void setY(float y){
		this.y = y;
	}
	
	/**
	 * get x velocity
	 * @return
	 */
	public float getVelocityX(){
		return velocityX;
	}
	/**
	 * get y velocity
	 * @return
	 */
	public float getVelocityY(){
		return velocityY;
	}
	/**
	 * set x velocity
	 * @param x
	 */
	public void setVelocityX(float velocityX){
		this.velocityX = velocityX;
	}
	/**
	 * set y velocity
	 * @param y
	 */
	public void setVelocityY(float velocityY){
		this.velocityY = velocityY;
	}
	
	public ObjectId getId(){
		return id;
	}
}
