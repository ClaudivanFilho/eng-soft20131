package trialMode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;

public class GdxGameInputProcessorTrial implements InputProcessor {

	private static final float FORCA_DE_IMPULSO = 1f;
	private TampinhaTrialWorld world;
	private Camera camera;

	public GdxGameInputProcessorTrial(Camera camera, TampinhaTrialWorld world) {
		this.camera = camera;
		this.world = world;

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
		if (!world.flagStopTampa1) {
			// inicia a action de Dragged
			world.dragged = new MouseDraggedActionTrial(world, camera);
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (!world.flagStopTampa1) {
			jogarTampa();
		}
		world.dragged = null;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (world != null && world.dragged != null) {
			if (!world.flagStopTampa1) {
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
		Vector2 direcao = currentMousePosition.sub(world.getTampa1().getBody()
				.getPosition());
		direcao.nor();
		Vector2 impulso = direcao.mul((int) world.dragged.getDistancia() * FORCA_DE_IMPULSO
				* world.getTampa1().getBody().getMass() * -1f);

		world.getTampa1().getBody().applyLinearImpulse(impulso,
				currentMousePosition);
	}
}
