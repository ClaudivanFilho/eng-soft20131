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
	private boolean isColisaoAtual = false;

	public MyContactListener(TampinhaWorld world) {
		this.tampa1 = world.getTampa1();
		this.tampa2 = world.getTampa2();
		this.world = world;
	}

	@Override
	public void beginContact(Contact contact) {

		isColisaoAtual = true;
	}

	@Override
	public void endContact(Contact contact) {
		if (isColisaoAtual) {
			Fixture x1 = contact.getFixtureA();
			Fixture x2 = contact.getFixtureB();
			boolean isContatoComTampa1 = x1.getBody().getUserData()
					.equals("Tampa1")
					|| x2.getBody().getUserData().equals("Tampa1");
			
			
			boolean isContatoComTampa2 = x1.getBody().getUserData()
					.equals("Tampa2")
					|| x2.getBody().getUserData().equals("Tampa2");

			contatoComAPista(x1, x2, isContatoComTampa1, isContatoComTampa2);
			contatoComCheckPont1(x1, x2, isContatoComTampa1, isContatoComTampa2);
			contatoComCheckPoint2(x1, x2, isContatoComTampa1,
					isContatoComTampa2);
			contatoComCheckPoint3(x1, x2, isContatoComTampa1,
					isContatoComTampa2);
			contatoComCheckPoint4(x1, x2, isContatoComTampa1,
					isContatoComTampa2);
		}
		isColisaoAtual = false;

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

	private void contatoComCheckPoint4(final Fixture x1, final Fixture x2,
			boolean isContatoComTampa1, boolean isContatoComTampa2) {
		if (x1.getBody().getUserData().equals("checkPoint4")
				|| x2.getBody().getUserData().equals("checkPoint4")) {

			if (isContatoComTampa1) {
				tampa1.setCheckPointComplete4(true);
			} else if (isContatoComTampa2) {
				tampa2.setCheckPointComplete4(true);

			}
		}
	}

	private void contatoComCheckPoint3(final Fixture x1, final Fixture x2,
			boolean isContatoComTampa1, boolean isContatoComTampa2) {
		if (x1.getBody().getUserData().equals("checkPoint3")
				|| x2.getBody().getUserData().equals("checkPoint3")) {
			if (isContatoComTampa1) {
				tampa1.setCheckPointComplete3(true);
			} else if (isContatoComTampa2) {
				tampa2.setCheckPointComplete3(true);

			}
		}
	}

	private void contatoComCheckPoint2(final Fixture x1, final Fixture x2,
			boolean isContatoComTampa1, boolean isContatoComTampa2) {
		if (x1.getBody().getUserData().equals("checkPoint2")
				|| x2.getBody().getUserData().equals("checkPoint2")) {
			if (isContatoComTampa1) {
				tampa1.setCheckPointComplete2(true);
			} else if (isContatoComTampa2) {
				tampa2.setCheckPointComplete2(true);

			}
		}
	}

	private void contatoComCheckPont1(final Fixture x1, final Fixture x2,
			boolean isContatoComTampa1, boolean isContatoComTampa2) {
		if (x1.getBody().getUserData().equals("checkPoint1")
				|| x2.getBody().getUserData().equals("checkPoint1")) {

			if (isContatoComTampa1) {
				vitoria();
				tampa1.setCheckPointComplete1(true);
				if (tampa1.isRaceComplete()) {
					// Tampa 1 venceu
					vitoria();
				}
			} else if (isContatoComTampa2) {
				vitoria() ;
				tampa2.setCheckPointComplete1(true);
				if (tampa2.isRaceComplete()) {
					// Tampa 2 venceu
					vitoria();
				}
			}
		}
	}

	private void contatoComAPista(final Fixture x1, final Fixture x2,
			boolean isContatoComTampa1, boolean isContatoComTampa2) {
		if (x1.getBody().getUserData() != null
				&& x2.getBody().getUserData() != null) {
		
			boolean isContatoComAPista = x1.getBody().getUserData()
					.equals("Pista")
					|| x2.getBody().getUserData().equals("Pista");
			if (isContatoComAPista) {
				if (isContatoComTampa1) {
					atualizaResetTampa1();

				} else if (isContatoComTampa2) {

					atualizaResetTampa2();
				}
			}

		}
	}

	private void atualizaResetTampa2() {
		if (tampa2.isReset()) {
			tampa2.setReset(false);

		} else {
			tampa2.setReset(true);
		}
	}

	private void atualizaResetTampa1() {
		if (tampa1.isReset()) {
			tampa1.setReset(false);

		} else {
			tampa1.setReset(true);
		}
	}

	private void vitoria() {
		Gdx.app.exit();
		Sound sound = Gdx.audio.newSound(Gdx.files
				.internal("data/glass-ding.mp3"));
		sound.play();
	}

}
