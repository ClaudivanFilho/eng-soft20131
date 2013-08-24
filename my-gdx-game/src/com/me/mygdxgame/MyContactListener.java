package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MyContactListener implements ContactListener {

	private Tampa tampa1;
	private Tampa tampa2;
	private TampinhaWorld world;
	public MyContactListener(TampinhaWorld world) {
		this.tampa1 = world.getTampa1();
		this.tampa2 = world.getTampa2();
		this.world = world;
	}

	@Override
	public void beginContact(Contact contact) {
		
	}

	@Override
	public void endContact(Contact contact) {
		final Fixture x1 = contact.getFixtureA();
		final Fixture x2 = contact.getFixtureB();
		System.out.println("------------");

		if (x1.getBody().getUserData() != null
				&& x2.getBody().getUserData() != null) {
		
			if (x1.getBody().getUserData().equals("Pista")
					|| x2.getBody().getUserData().equals("Pista")) {

				if (x1.getBody().getUserData().equals("Tampa1")
						|| x2.getBody().getUserData().equals("Tampa1")) {
					

					if (tampa1.isReset()) {
						tampa1.setReset(false);
						System.out.println("tampa1: False");

					} else {
						System.out.println("tampa1: True");
						tampa1.setReset(true);
					}

				}
				else{
					if (tampa2.isReset()) {
						System.out.println("tampa2: False");

						tampa2.setReset(false);

					} else {
						System.out.println("tampa2: True");

						tampa2.setReset(true);
					}

				}
			}
			
			
			
		}
		
		if (x1.getBody().getUserData().equals("checkPoint1")
				|| x2.getBody().getUserData().equals("checkPoint1")) {
			
			if (x1.getBody().getUserData().equals("Tampa1")
					|| x2.getBody().getUserData().equals("Tampa1")) {
				
				tampa1.setCheckPointComplete1(true);
				if(tampa1.isRaceComplete()){
					//Tampa 1 venceu
						
					tocarSomVitoria();
				}
			}
			else{
				tampa2.setCheckPointComplete1(true);
				if(tampa2.isRaceComplete()){
					//Tampa 2 venceu

					tocarSomVitoria();
				}
			}
			
			
			
		}
		else if (x1.getBody().getUserData().equals("checkPoint2")
				|| x2.getBody().getUserData().equals("checkPoint2")) {
			if (x1.getBody().getUserData().equals("Tampa1")
					|| x2.getBody().getUserData().equals("Tampa1")) {
				System.out.println("tampa 1 completou 2");
				tampa1.setCheckPointComplete2(true);
			}else{
				System.out.println("tampa 2 completou 2");

				tampa2.setCheckPointComplete2(true);

			}
		}
		else if (x1.getBody().getUserData().equals("checkPoint3")
				|| x2.getBody().getUserData().equals("checkPoint3")) {
			if (x1.getBody().getUserData().equals("Tampa1")
					|| x2.getBody().getUserData().equals("Tampa1")) {
				System.out.println("tampa 1 completou 3");

				tampa1.setCheckPointComplete3(true);
			}else{
				System.out.println("tampa 2 completou 3");

				tampa2.setCheckPointComplete3(true);

			}
		}
		else if (x1.getBody().getUserData().equals("checkPoint4")
				|| x2.getBody().getUserData().equals("checkPoint4")) {
			if (x1.getBody().getUserData().equals("Tampa1")
					|| x2.getBody().getUserData().equals("Tampa1")) {
				tampa1.setCheckPointComplete4(true);
			}else{
				tampa2.setCheckPointComplete4(true);

			}
		}


	}

	private void tocarSomVitoria() {
		Sound sound = Gdx.audio.newSound(Gdx.files.internal("data/glass-ding.mp3"));
		sound.play();
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

}
