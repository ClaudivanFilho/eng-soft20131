package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;

public class Util {
	private static float w = Gdx.graphics.getWidth();
	private static float h = Gdx.graphics.getHeight();
	private final static float wDefault = 1024f;
	private final static float hDefault = 1024f;
	private static float p = hDefault / wDefault;
	private static float p2 = wDefault / hDefault;

	

	
	
	public static float changeX() {
		if(p < h/w){
			return  (w / wDefault)* p2;
		}
		return  (w / wDefault);
	}

	public static float changeY() {
	
		if(p > h/w){
			return  (h / hDefault)*p2;
		}
		return (h / hDefault);
	}

	/*
	 * public static Vector2 resize(float x, float y) { int h =
	 * Gdx.graphics.getWidth(); int w = Gdx.graphics.getHeight(); changeX = w /
	 * wDefault; // being your screen size that you're developing changeY = h /
	 * hDefault; return new Vector2(position.x * changeX, position.y * changeY);
	 * width = (int) (1024 * changeX); height = (int) (720 * changeY); Vector2
	 * bounds = new Vector2(position.x,(Gdx.graphics.getHeight() - position.y) -
	 * height); }
	 */

}
