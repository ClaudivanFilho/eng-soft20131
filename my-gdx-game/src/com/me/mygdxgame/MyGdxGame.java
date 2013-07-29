package com.me.mygdxgame;

import aurelienribon.bodyeditor.BodyEditorLoader;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.FrictionJoint;
import com.badlogic.gdx.physics.box2d.joints.FrictionJointDef;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;

public class MyGdxGame implements ApplicationListener {
	private Vector2 gravity;
	private TampinhaWorld tampinhaWorld;
	private Box2DDebugRenderer debugRenderer;
	public static OrthographicCamera camera;
	private Body currentBody;
	private Vector2 currentMousePosition;
	public static MouseJoint mousejoint;
	private MouseJointDef mouseJointDef;
	private Body mousePointBody;
	private BodyDef boxBodyDef;
	private Body boxBody;
	private FrictionJoint frictionJoint;
	private BodyDef mousePointBodyDef;
	private Texture texture;
	private Sprite sprite;
	private SpriteBatch spriteBatch;
	private Tampa tampa;
	private Pista pista;
	
	@Override
	public void create() {
		

		//Create World
		tampinhaWorld = new TampinhaWorld();
		
		//Camera settings
		createCamera();

		//Cria a tampa
		tampa = new Tampa(tampinhaWorld);

		//Cria MouseJoint
		tampinhaWorld.createMouseJoint(tampa.getBody());
		
		//Cria Pista
		pista = new Pista(tampinhaWorld);
		
		//Setting input processor
		Gdx.input.setInputProcessor(new GdxGameInputProcessor(camera, tampinhaWorld, tampa, sprite));
		
		//Setting Rederer
		debugRenderer = new Box2DDebugRenderer();
	}

	private void createCamera() {
		camera = new OrthographicCamera();
		camera.viewportHeight = 720;
		camera.viewportWidth = 1024;
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
		 
		tampa.render();
		
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
