package com.test.engine;

import java.awt.image.DataBufferInt;

public class Renderer {
	private int pH, pW;
	private int[] p;

	public Renderer(GameContainer gc) {
		pH = gc.getHeight();
		pW = gc.getWidth();
		p = ((DataBufferInt)gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
	}
	
	public void clear() {
		for(int i = 0; i < p.length; i++){
			//RAINBOW!!!!!!!
			p[i] += 0;
		}
	}
}