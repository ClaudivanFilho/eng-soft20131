package com.me.mygdxgame;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;

public class TampinhaWorld {
	private World world;
	private Vector2 gravity;
	private Body currentBody;
	private MouseJoint mouseJoint;

	private Tampa tampa1;
	private Tampa tampa2;
	private Pista pista;

	static final float BOX_STEP = 1 / 60f;
	static final int BOX_VELOCITY_ITERATIONS = 6;
	static final int BOX_POSITION_ITERATIONS = 2;
	static final float WORLD_TO_BOX = 0.01f;
	static final float BOX_WORLD_TO = 100f;

	public TampinhaWorld() {

		// Cria o Mundo
		this.gravity = new Vector2();
		this.world = new World(gravity, true);
		// Cria as duas tampas
		Vector2 tampa1Position = new Vector2(100*Util.changeX(), 564*Util.changeY());
		this.tampa1 = new Tampa(this, tampa1Position);
		Vector2 tampa2Position = new Vector2(150*Util.changeX(), 570*Util.changeY());
		this.tampa2 = new Tampa(this, tampa2Position);
		// Cria a Pista
		this.pista = new Pista(this);
		// Adding the contact listener
		ContactListener listener = new MyContactListener();
		world.setContactListener(listener);
	}

	public World getWorld() {
		return world;
	}

	public Body createBody(BodyDef bodyDef) {
		return world.createBody(bodyDef);

	}

	public MouseJoint createMouseJoint(Body tampaBody) {
		if (mouseJoint != null) {
			world.destroyJoint(mouseJoint);
		}
		MouseJointDef mouseJointDef = new MouseJointDef();
		currentBody = tampaBody;
		BodyDef mousePointBodyDef = new BodyDef();
		mousePointBodyDef.position.set(new Vector2(100, 100));
		Body mousePointBody = world.createBody(mousePointBodyDef);
		mouseJointDef.target.set(tampaBody.getPosition());
		mouseJointDef.bodyA = mousePointBody;
		mouseJointDef.bodyB = currentBody;
		mouseJointDef.collideConnected = true;
		mouseJointDef.maxForce = 0;
		mouseJointDef.frequencyHz = 10f;
		mouseJoint = (MouseJoint) world.createJoint(mouseJointDef);
		return mouseJoint;
	}

	public MouseJoint getMouseJoint() {
		return mouseJoint;
	}

	public void render() {
		world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
		pista.render();
		tampa1.render();
		tampa2.render();
	}

	public Tampa getTampa1() {

		return this.tampa1;
	}

	public Tampa getTampa2() {
		return this.tampa2;
	}

}
