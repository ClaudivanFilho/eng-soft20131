package com.me.mygdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;

public class MyGdxGame implements ApplicationListener {
	Vector2 gravity = new Vector2(0f, -100f);
	World world = new World(gravity, true);
	Box2DDebugRenderer debugRenderer;
	OrthographicCamera camera;
	static final float BOX_STEP = 1 / 60f;
	static final int BOX_VELOCITY_ITERATIONS = 6;
	static final int BOX_POSITION_ITERATIONS = 2;
	static final float WORLD_TO_BOX = 0.01f;
	static final float BOX_WORLD_TO = 100f;
	private BodyDef circle;
	private CircleShape dynamicCircle;
	private Body currentBody;
	private Vector2 currentMousePosition;
	private MouseJoint mousejoint;

	@Override
	public void create() {

		// Camera settings
		camera = new OrthographicCamera();
		camera.viewportHeight = 240;
		camera.viewportWidth = 432;
		camera.position.set(camera.viewportWidth * .5f,
				camera.viewportHeight * .5f, 0f);
		camera.update();

		// Ground body
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(new Vector2(0, 10));
		Body groundBody = world.createBody(groundBodyDef);
		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox((camera.viewportWidth) * 2, 10.0f);
		groundBody.createFixture(groundBox, 0.0f);

		circle = new BodyDef();
		circle.type = BodyType.DynamicBody;
		circle.position.set(camera.viewportWidth / 2,
				camera.viewportHeight / 2);
		Body circleBody = world.createBody(circle);

		dynamicCircle = new CircleShape();
		dynamicCircle.setRadius(5f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicCircle;
		fixtureDef.density = 1.0f;
		fixtureDef.friction = 0.0f;
		fixtureDef.restitution = 1;
		circleBody.createFixture(fixtureDef);
		debugRenderer = new Box2DDebugRenderer();

		//MouseJoint
		MouseJointDef mouseJointDef = new MouseJointDef();
		
		mouseJointDef.bodyA = groundBody;

		currentBody = circleBody;
		
		mouseJointDef.bodyB = currentBody;

		Vector2 worldCenter = new Vector2(camera.viewportWidth / 2,
				camera.viewportHeight / 2);
		
		Vector2 bodypos = worldCenter;

		mouseJointDef.target.set(bodypos.x, bodypos.y);

		mouseJointDef.collideConnected = true;

		
		mouseJointDef.maxForce = 300 * currentBody.getMass();

		mousejoint = (MouseJoint) world.createJoint(mouseJointDef);
	}

	@Override
	public void dispose() {
	}

	// O render serve para atualizar a tela de acordo com os eventos de input
	@Override
	public void render() {
		// TouchPad
		// Escuta o clique do TouchPad
		if (Gdx.input.isTouched()) {
			//MouseJoint
			currentMousePosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
			mousejoint.setTarget(currentMousePosition);
		}

		
		
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
