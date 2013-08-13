package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class GdxGameInputProcessor implements InputProcessor {

	
	private static final float FORCA_DE_IMPULSO = 1000f;
	private TampinhaWorld world;
	private Camera camera;
	private Tampa tampa1;
	private Tampa tampa2;
	private Tampa tampaDaVez;
	private boolean vezDoJogador;

	public GdxGameInputProcessor(Camera camera, TampinhaWorld world) {
		this.camera = camera;
		this.world = world;
		this.tampa1 = world.getTampa1();
		this.tampa2 = world.getTampa2();
		this.tampaDaVez = tampa1; 
		this.vezDoJogador= true;
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
		world.createMouseJoint(tampaDaVez.getBody());
		System.out.println("-- TouchDown --");
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		jogarTampa();
		if (vezDoJogador) {
			tampaDaVez = tampa1;
			vezDoJogador = false;
		} else {
			tampaDaVez = tampa2;
			vezDoJogador = true;
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		float x = Gdx.input.getX();
		float y = Gdx.input.getY();
		Vector2 currentMousePosition = new Vector2(x, Math.abs(y - camera.viewportHeight));
		world.getMouseJoint().setTarget(currentMousePosition);
		System.out.println("-- TouchDragged --");
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

	private void jogarTampa() {
		float x = Gdx.input.getX();
		float y = Gdx.input.getY();
		
		Vector2 currentMousePosition = new Vector2(x, Math.abs(y - camera.viewportHeight));

//		System.out.println(currentMousePosition.x + ", " + currentMousePosition.y);

		//Cálculo da Direção do Impulso contrario ao mousejoint.position
		Vector2 direcao = currentMousePosition.sub(tampaDaVez.getBody().getPosition());
		direcao.nor();
		Vector2 impulso = direcao.mul(FORCA_DE_IMPULSO * tampaDaVez.getBody().getMass() * -1f);
				
		tampaDaVez.getBody().applyLinearImpulse(impulso, currentMousePosition);
	}
}
