package trialMode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MyContactListenerTrial implements ContactListener {

	private TampaTrial tampa1;
	private TampinhaTrialWorld world;
	private boolean isColisaoAtual = false;

	public MyContactListenerTrial(TampinhaTrialWorld world) {
		this.tampa1 = world.getTampa1();
		this.world = world;
	}

	@Override
	public void beginContact(Contact contact) {
		//verifica se houve contato com a pista de alguma tampa.
		contatoComAPista(contact);
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
	}
	
	private void contatoComAPista(Contact contact) {
		if (contact.getFixtureA().getBody().getUserData().equals("Pista")
				&& contact.getFixtureB().getBody().getUserData().equals("Tampa1")) {
			world.getTampa1().setReset(true);
		}
	}

	private void contatoComCheckPoint4(final Fixture x1, final Fixture x2,
			boolean isContatoComTampa1, boolean isContatoComTampa2) {
		if (x1.getBody().getUserData().equals("checkPoint4")
				|| x2.getBody().getUserData().equals("checkPoint4")) {

			if (isContatoComTampa1) {
				tampa1.setCheckPointComplete4(true);
			}
		}
	}

	private void contatoComCheckPoint3(final Fixture x1, final Fixture x2,
			boolean isContatoComTampa1, boolean isContatoComTampa2) {
		if (x1.getBody().getUserData().equals("checkPoint3")
				|| x2.getBody().getUserData().equals("checkPoint3")) {
			if (isContatoComTampa1) {
				tampa1.setCheckPointComplete3(true);
			}
		}
	}

	private void contatoComCheckPoint2(final Fixture x1, final Fixture x2,
			boolean isContatoComTampa1, boolean isContatoComTampa2) {
		if (x1.getBody().getUserData().equals("checkPoint2")
				|| x2.getBody().getUserData().equals("checkPoint2")) {
			if (isContatoComTampa1) {
				tampa1.setCheckPointComplete2(true);
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
			}
		}
	}

	private void vitoria() {
		Sound sound = Gdx.audio.newSound(Gdx.files
				.internal("data/glass-ding.mp3"));
		sound.play();
		
	}
}
