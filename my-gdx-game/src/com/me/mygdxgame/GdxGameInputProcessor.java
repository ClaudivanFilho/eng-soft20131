package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;

public class GdxGameInputProcessor implements InputProcessor {

	private static final float FORCA_DE_IMPULSO = 1f;
	private TampinhaWorld world;
	private Camera camera;
	private Tampa tampa1;
	private Tampa tampa2;
	private boolean vezDoJogador;

	public GdxGameInputProcessor(Camera camera, TampinhaWorld world) {
		this.camera = camera;
		this.world = world;
		this.tampa1 = world.getTampa1();
		this.tampa2 = world.getTampa2();
		this.vezDoJogador = true;

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
		if (!world.flagStopTampa1 && !world.flagStopTampa2) {
			// inicia a action de Dragged
			world.dragged = new MouseDraggedAction(world, camera);
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (!world.flagStopTampa1 && !world.flagStopTampa2) {
			jogarTampa();

			if (vezDoJogador) {
				world.tampaDaVez = tampa2;
				vezDoJogador = false;
			} else {
				world.tampaDaVez = tampa1;
				vezDoJogador = true;
			}
		}
		world.dragged = null;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (world != null && world.dragged != null) {
			if (!world.flagStopTampa1 && !world.flagStopTampa2) {
				// atualiza as cordenadas do dragged
				world.dragged.atualizacordenadasHead();
			}
		}
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

		Vector2 currentMousePosition = new Vector2(x, Math.abs(y
				- camera.viewportHeight));

		// Calcula a direcao do Impulso contrario ao dragged.
		Vector2 direcao = currentMousePosition.sub(world.tampaDaVez.getBody()
				.getPosition());
		direcao.nor();
		Vector2 impulso = direcao.mul((int) world.dragged.getDistancia() * FORCA_DE_IMPULSO
				* world.tampaDaVez.getBody().getMass() * -1f);

		world.tampaDaVez.getBody().applyLinearImpulse(impulso,
				currentMousePosition);
	}
}
