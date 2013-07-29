package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class GdxGameInputProcessor implements InputProcessor {

	
	private static final float FORCA_DE_IMPULSO = 1000f;
	private Tampa tampa;
	private Sprite sprite;
	private TampinhaWorld world;
	private Camera camera;

	public GdxGameInputProcessor(Camera camera, TampinhaWorld world, Tampa circleBody, Sprite sprite) {
		this.camera = camera;
		this.world = world;
		this.tampa = circleBody;
		this.sprite = sprite;
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
		
		Vector2 currentMousePosition = new Vector2(x, Math.abs(y - camera.viewportHeight));

		System.out.println(currentMousePosition.x + ", " + currentMousePosition.y);

		//Cálculo da Direção do Impulso contrario ao mousejoint.position
		Vector2 direcao = currentMousePosition.sub(tampa.getBody().getPosition());
		direcao.nor();
		Vector2 impulso = direcao.mul(FORCA_DE_IMPULSO * tampa.getBody().getMass() * -1f);
				
		tampa.getBody().applyLinearImpulse(impulso, currentMousePosition);
		
		System.out.println("touchUp");
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		float x = Gdx.input.getX();
		float y = Gdx.input.getY();
		
		Vector2 currentMousePosition = new Vector2(x, Math.abs(y - camera.viewportHeight));
		world.getMouseJoint().setTarget(currentMousePosition);
//		System.out.println("touchDragged");
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
