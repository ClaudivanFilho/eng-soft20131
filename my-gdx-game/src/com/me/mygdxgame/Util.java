package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;

public class Util {
	public static float w = Gdx.graphics.getWidth();
	public static float h = Gdx.graphics.getHeight();
	public final static float wDefault = 1024f;
	public final static float hDefault = 1024f;
	public static float p = hDefault / wDefault;
	public static float p2 = wDefault / hDefault;

	

	public static int center(float textWidth){
		return   (int) ((w / 2) - (textWidth / 2));
	}
	
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
