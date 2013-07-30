package com.me.mygdxgame;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;

public class TampinhaWorld {
	private World world;
	private Vector2 gravity;
	private Body currentBody;
	private MouseJoint mouseJoint;
	private Tampa tampa;
	private Pista pista;
	
	static final float BOX_STEP = 1 / 60f;
	static final int BOX_VELOCITY_ITERATIONS = 6;
	static final int BOX_POSITION_ITERATIONS = 2;
	static final float WORLD_TO_BOX = 0.01f;
	static final float BOX_WORLD_TO = 100f;
	
	public TampinhaWorld() {
		//Cria o Mundo
		this.gravity = new Vector2();
		this.world = new World(gravity, true);
		//Cria a tampa
		this.tampa = new Tampa(this);
		//Cria o MouseJoint
		createMouseJoint(tampa.getBody());
		//Cria Pista
		this.pista = new Pista(this);
		
	}
	
	public World getWorld() {
		return world;
	}

	public Body createBody(BodyDef bodyDef) {
		return world.createBody(bodyDef);
	}


	public void createMouseJoint(Body tampaBody) {
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
		
	}
	
	public MouseJoint getMouseJoint() {
		return mouseJoint;
	}
	
	public void render() {
		world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
		pista.render();
		tampa.render();
	}

	public Tampa getTampa() {
		
		return this.tampa;
	}

}
