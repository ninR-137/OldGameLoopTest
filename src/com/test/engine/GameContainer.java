package com.test.engine;

import java.awt.event.*;

public class GameContainer implements Runnable{
	Thread thread;
	private boolean running = false;
	private final double UPDATE_CAP = 1.0 / 60.0;
	private int width = 320, height = 240;
	private float scale = 2f;
	private String title = "Russel";
	private Window window;
	private Renderer renderer;
	private Input input;

	public GameContainer() {
	}

	public void start() {
		window = new Window(this);
		thread = new Thread(this);
		renderer = new Renderer(this);
		input = new Input(this);
		//.start() for side thread, .run for main thread()
		thread.run();
	}	
	
	public void stop(){

	}

	@Override
	public void run() {
		running =  true;
		
		boolean render = false;
		double currentTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0;
		double delta = 0;
		double unprocessedTime = 0;
		double frameTime = 0;
		int frames = 0;
		int fps = 0;
		

		while(running) {

			render = false;
			currentTime = System.nanoTime() / 1000000000.0;
			delta = currentTime - lastTime;
			lastTime = currentTime;

			unprocessedTime += delta;
			frameTime += delta;

			while(unprocessedTime >= UPDATE_CAP){
				unprocessedTime -= UPDATE_CAP;
				render = true;
				
				//TODO: update game
					
				System.out.println("x: "+ input.getMouseX()+ " y: " + input.getMouseY());

				input.update();

				if(frameTime >= 1.0) {
					frameTime = 0;
					fps = frames;
					frames = 0;
					System.out.println("FPS: " + fps);

				}
			}

			if (render) {
				renderer.clear();
				//TODO: Render Game
				window.update();
				frames ++;
			}
			
			else {
				//If the thread doesn't update
				try
				{
					thread.sleep(1);
				}
				catch (InterruptedException e)
				{
					//TODO : Auto generate catch block
					e.printStackTrace();
				}
			}
		}
		dispose();

	}

	public void dispose() {

	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public float getScale() {
		return scale;
	}

	public String getTitle() {
		return title;
	}

	public Window getWindow() {
		return window;
	}
	
	public static void main (String args[]) {
		GameContainer gc = new GameContainer();
		gc.start();
	}
}