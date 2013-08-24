package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;

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
			world.createMouseJoint(world.tampaDaVez.getBody());
			//inicia a action de Dragged
			world.dragged = new MouseDraggedAction(world, camera);
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (!world.flagStopTampa1 && !world.flagStopTampa2) {
			jogarTampa();

			world.getWorld().destroyJoint(world.mouseJoint);
			if (vezDoJogador) {
				world.tampaDaVez = tampa2;
				vezDoJogador = false;
			} else {
				world.tampaDaVez = tampa1;
				vezDoJogador = true;
			}
			createJointVazio();
		}
		world.dragged = null;
		return false;
	}

	
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (!world.flagStopTampa1 && !world.flagStopTampa2) {
			// atualiza as cordenadas do dragged
			world.dragged.atualizacordenadasHead();
		}
		return false;
	}

	/**
	 * Quanto um joint é deletado, necessita-se de outro para substituí-lo.
	 */
	private void createJointVazio() {
		MouseJointDef mouseJointDef = new MouseJointDef();
		Body currentBody = world.tampaDaVez.getBody();
		BodyDef mousePointBodyDef = new BodyDef();
		mousePointBodyDef.position.set(new Vector2(100, 100));
		Body mousePointBody = world.createBody(mousePointBodyDef);
		mouseJointDef.target.set(world.tampaDaVez.getBody().getPosition());
		mouseJointDef.bodyA = mousePointBody;
		mouseJointDef.bodyB = currentBody;
		world.getWorld().createJoint(mouseJointDef);
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

		// System.out.println(currentMousePosition.x + ", " +
		// currentMousePosition.y);

		float x1 = currentMousePosition.x;
		float x2 = world.tampaDaVez.getBody().getPosition().x;
		float y1 = currentMousePosition.y;
		float y2 = world.tampaDaVez.getBody().getPosition().y;
		double distancia = Math.sqrt(Math.pow((x1 - x2), 2)
				+ Math.pow((y1 - y2), 2));
		// CÃ¡lculo da DireÃ§Ã£o do Impulso contrario ao mousejoint.position
		Vector2 direcao = currentMousePosition.sub(world.tampaDaVez.getBody()
				.getPosition());
		direcao.nor();
		Vector2 impulso = direcao.mul((int) distancia * FORCA_DE_IMPULSO
				* world.tampaDaVez.getBody().getMass() * -1f);

		world.tampaDaVez.getBody().applyLinearImpulse(impulso,
				currentMousePosition);
	}
}
