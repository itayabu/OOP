package com.assaf.RottenEngineer.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.assaf.RottenEngineer.framework.ObjectId;
import com.assaf.RottenEngineer.objects.Player;


public class Game extends Canvas implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1998028471763036052L;
	
	public boolean running = false;
	public Thread thread;
	public static int HEIGHT;
	public static int WIDTH;
	
	ObjectHandler obejectHandler;
	
	private void init(){
		WIDTH = getWidth();
		HEIGHT = getHeight();
		obejectHandler = new ObjectHandler();
		obejectHandler.addOject(new Player(100,100,ObjectId.Player));
		obejectHandler.createLevel();
	}
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	

	public void run(){	
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	private void tick(){
		obejectHandler.tick(); 
		
	}
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics graphic = bs.getDrawGraphics();
		
		
		
		graphic.setColor(Color.BLUE);
		graphic.fillRect(0, 0, getWidth(), getHeight());
		obejectHandler.render(graphic);
		
		
		
		
		graphic.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		new Window(1300, 650, "ROTTEN ENGINEER", new Game());
	}
}
