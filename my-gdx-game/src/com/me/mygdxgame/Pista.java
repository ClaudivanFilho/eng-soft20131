package com.me.mygdxgame;

import aurelienribon.bodyeditor.BodyEditorLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Pista {
	private TampinhaWorld world;

	public Pista(TampinhaWorld world) {
		this.world = world;
		criarPista();
	}

	private void criarPista() {
		// 0. Create a loader for the file saved from the editor.
	    BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("data/teste-body-editor.json"));
	 
	    // 1. Create a BodyDef, as usual.
	    BodyDef bd = new BodyDef();
	    bd.position.set(300, 250);
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
}
