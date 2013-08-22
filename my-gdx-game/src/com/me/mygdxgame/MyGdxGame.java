package com.me.mygdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class MyGdxGame implements ApplicationListener {
	private TampinhaWorld tampinhaWorld;
	private Box2DDebugRenderer debugRenderer;

	public static OrthographicCamera camera;
	
	@Override
	public void create() {
		//Camera settings
		createCamera();
		
		//Create World
		tampinhaWorld = new TampinhaWorld();
		
		//Setting input processor
		Gdx.input.setInputProcessor(new GdxGameInputProcessor(camera, tampinhaWorld));
		
		//Setting Rederer
		debugRenderer = new Box2DDebugRenderer();
	}

	private void createCamera() {
		
		
		camera = new OrthographicCamera();
		camera.viewportHeight =  Gdx.graphics.getHeight();
		camera.viewportWidth = Gdx.graphics.getWidth();;
		camera.position.set(camera.viewportWidth * .5f,
				camera.viewportHeight * .5f, 0f);
		camera.update();
	}

	@Override
	public void dispose() {
	}

	// O render serve para atualizar a tela de acordo com os eventos de input
	@Override
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		debugRenderer.render(tampinhaWorld.getWorld(), camera.combined);
		tampinhaWorld.render();
	}

	@Override
	public void resize(int width, int height) {
		
		
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	
}
