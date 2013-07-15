package com.me.mygdxgame;

import sun.management.Sensor;

import aurelienribon.bodyeditor.BodyEditorLoader;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.JointDef.JointType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.FrictionJoint;
import com.badlogic.gdx.physics.box2d.joints.FrictionJointDef;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.sun.org.apache.xpath.internal.operations.And;

public class MyGdxGame implements ApplicationListener {
	private Vector2 gravity;
	private World world;
	private Box2DDebugRenderer debugRenderer;
	public static OrthographicCamera camera;
	static final float BOX_STEP = 1 / 60f;
	static final int BOX_VELOCITY_ITERATIONS = 6;
	static final int BOX_POSITION_ITERATIONS = 2;
	static final float WORLD_TO_BOX = 0.01f;
	static final float BOX_WORLD_TO = 100f;
	private Body currentBody;
	private Vector2 currentMousePosition;
	public static MouseJoint mousejoint;
	private MouseJointDef mouseJointDef;
	private Body circleBody;
	private Body groundBody;
	private BodyDef boxBodyDef;
	private Body boxBody;
	private FrictionJoint frictionJoint;
	private BodyDef groundBodyDef;
	
	@Override
	public void create() {
		

		//Create World
		createWorld();
		
		// Camera settings
		createCamera();

		//Circle
		createCircle();
		
		createBox();

		createGroundBody();
		
		createMouseJoint();
		
//		createFrictionJoint();
		
		createSpeedway();
		
		//Setting input processor
		Gdx.input.setInputProcessor(new GdxGameInputProcessor(circleBody));
		
		//Setting Rederer
		debugRenderer = new Box2DDebugRenderer();
	}

	private void createBox() {
//		boxBodyDef = new BodyDef();
//		boxBodyDef.type = BodyType.DynamicBody;
//		boxBodyDef.position.set(12,120);
//		boxBody = world.createBody(boxBodyDef);
//
//		PolygonShape dynamicBox = new PolygonShape();
//		
//		dynamicBox.setAsBox(10f, 10f);
//		FixtureDef fixtureDef = new FixtureDef();
//		fixtureDef.shape = dynamicBox;
//		fixtureDef.density = 1.0f;
//		fixtureDef.friction = 5.0f;
//		fixtureDef.restitution = 1;
//		boxBody.createFixture(fixtureDef);
		
	}

	private void createFrictionJoint() {
		FrictionJointDef frictionJointDef = new FrictionJointDef();
		
//		frictionJointDef.initialize(circleBody, boxBody, new Vector2(10,10));
		frictionJointDef.bodyA = groundBody;
		frictionJointDef.bodyB = circleBody;
		frictionJointDef.maxForce = 10 * currentBody.getMass();
		frictionJointDef.maxTorque = 0f;
		frictionJointDef.collideConnected = true;
		frictionJoint = (FrictionJoint) world.createJoint(frictionJointDef);
		
	}

	private void createSpeedway() {
	    // 0. Create a loader for the file saved from the editor.
	    BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("data/teste-body-editor.json"));
	 
	    // 1. Create a BodyDef, as usual.
	    BodyDef bd = new BodyDef();
	    bd.position.set(200, 300);
	    bd.type = BodyType.StaticBody;
	 
	    // 2. Create a FixtureDef, as usual.
	    FixtureDef fd = new FixtureDef();
	    fd.density = 1;
	    fd.friction = 0.5f;
	    fd.restitution = 0.3f;
	 
	    // 3. Create a Body, as usual.
	    Body speedwayModel = world.createBody(bd);
	 
	    float SPEEDWAY_WIDTH = 250f;
		// 4. Create the body fixture automatically by using the loader.
	    loader.attachFixture(speedwayModel, "Name", fd, SPEEDWAY_WIDTH );
	}
	
	private void createMouseJoint() {
		mouseJointDef = new MouseJointDef();
		currentBody = circleBody;
		
		mouseJointDef.bodyA = groundBody;
		mouseJointDef.bodyB = currentBody;
		mouseJointDef.target.set(circleBody.getPosition());
		mouseJointDef.collideConnected = true;
		mouseJointDef.maxForce = 0;
		mouseJointDef.frequencyHz = 10f;
		mousejoint = (MouseJoint) world.createJoint(mouseJointDef);
	}

	private void createGroundBody() {
		groundBodyDef = new BodyDef();  
        groundBodyDef.position.set(new Vector2(100, 100));
        groundBody = world.createBody(groundBodyDef);
        
	}

	private void createCircle() {
		BodyDef circleBodyDef = new BodyDef();
		circleBodyDef.type = BodyType.DynamicBody;
		circleBodyDef.position.set(100,120);
		circleBodyDef.angularDamping = 10f;
		circleBody = world.createBody(circleBodyDef);

		CircleShape dynamicCircle = new CircleShape();
		dynamicCircle.setRadius(10f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicCircle;
		fixtureDef.density = 1.0f;
		fixtureDef.friction = 5.0f;
		fixtureDef.restitution = 1;
		circleBody.createFixture(fixtureDef);
	}

	private void createCamera() {
		camera = new OrthographicCamera();
		camera.viewportHeight = 720;
		camera.viewportWidth = 1024;
		camera.position.set(camera.viewportWidth * .5f,
				camera.viewportHeight * .5f, 0f);
		camera.update();
	}

	private void createWorld() {
		gravity = new Vector2();
		world = new World(gravity, true);
	}

	@Override
	public void dispose() {
	}

	// O render serve para atualizar a tela de acordo com os eventos de input
	@Override
	public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		debugRenderer.render(world, camera.combined);
		world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
		
		
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
