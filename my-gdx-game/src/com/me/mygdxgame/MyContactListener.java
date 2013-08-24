package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MyContactListener implements ContactListener {

	private Tampa tampa1;
	private Tampa tampa2;

	public MyContactListener(Tampa tampa1, Tampa tampa2) {
		this.tampa1 = tampa1;
		this.tampa2 = tampa2;
	}

	@Override
	public void beginContact(Contact contact) {
		final Fixture x1 = contact.getFixtureA();
		final Fixture x2 = contact.getFixtureB();
		System.out.println("------------");

		if (x1.getBody().getUserData() != null
				&& x2.getBody().getUserData() != null) {
			if (x1.getBody().getUserData().equals("Pista")
					|| x2.getBody().getUserData().equals("Pista")) {

				if (x1.getBody().getUserData().equals("Tampa1")
						|| x2.getBody().getUserData().equals("Tampa1")) {
					Sound sound = Gdx.audio.newSound(Gdx.files.internal("data/glass-ding.mp3"));
					sound.play();

					if (tampa1.isReset()) {
						tampa1.setReset(false);

					} else {
						tampa1.setReset(true);
					}

				}
				else{
					if (tampa2.isReset()) {
						tampa2.setReset(false);

					} else {
						tampa2.setReset(true);
					}

				}
			}
			
		}

	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

}
