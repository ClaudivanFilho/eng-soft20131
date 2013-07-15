package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

public class GdxGameInputProcessor implements InputProcessor {

	
	private Body circleBody;

	public GdxGameInputProcessor(Body circleBody) {
		this.circleBody = circleBody;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		System.out.println("touch down!");
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		float x = Gdx.input.getX();
		float y = Gdx.input.getY();
		
		Vector2 currentMousePosition = new Vector2(x, Math.abs(y - MyGdxGame.camera.viewportHeight));

		
		circleBody.applyLinearImpulse(currentMousePosition.add(circleBody.getPosition()).add(new Vector2(100f, 100f)), circleBody.getPosition());
		
		System.out.println("touchUp");
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		float x = Gdx.input.getX();
		float y = Gdx.input.getY();
		
		Vector2 currentMousePosition = new Vector2(x, Math.abs(y - MyGdxGame.camera.viewportHeight));
		MyGdxGame.mousejoint.setTarget(currentMousePosition);
		System.out.println("touchDragged");
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
