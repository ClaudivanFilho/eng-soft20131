package trialMode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.me.mygdxgame.TurnAnimation;
import com.me.mygdxgame.Util;

public class TampinhaTrialWorld {

	private World world;
	private Vector2 gravity;
	protected TampaTrial tampa1;
	private PistaTrial pista;
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	static final float BOX_STEP = 1 / 60f;
	static final int BOX_VELOCITY_ITERATIONS = 6;
	static final int BOX_POSITION_ITERATIONS = 2;
	static final float WORLD_TO_BOX = 0.01f;
	static final float BOX_WORLD_TO = 100f;
	private String urlImagemTampa1;

	public MouseJoint mouseJoint;

	// se a flagStop estiver true indica que tem alguma tampa em movimento.
	public boolean flagStopTampa1 = false;

	TurnAnimation turnAnimation;
	MouseDraggedActionTrial dragged;
	Vector2 tampa1PositionTemp = new Vector2(100 * Util.changeX(),
			564 * Util.changeY());
	Vector2 tampa2PositionTemp = new Vector2(150 * Util.changeX(),
			564 * Util.changeY());

	public TampinhaTrialWorld(String urlImagemTampa1, String pista) {
		this.spriteBatch = new SpriteBatch();
		this.font = new BitmapFont();
		this.urlImagemTampa1 = urlImagemTampa1;
		// Cria o Mundo
		this.gravity = new Vector2();
		this.world = new World(gravity, true);
		// Cria as duas tampas
		Vector2 tampa1Position = new Vector2(80 * Util.changeX(),
				564 * Util.changeY());
		this.tampa1 = new TampaTrial(this, tampa1Position, "Tampa1",
				this.urlImagemTampa1);

		// Cria a Pista

		criarPista(pista);

		turnAnimation = new TurnAnimation(1); // começa com o jogador 1
		// Adding the contact listener
		ContactListener listener = new MyContactListenerTrial(this);
		world.setContactListener(listener);
	}

	private void criarPista(String pista) {

		CheckPointTrial checkPoint1;
		CheckPointTrial checkPoint2;
		CheckPointTrial checkPoint3;
		CheckPointTrial checkPoint4;
		if (pista.equals("pista1")) {
			checkPoint1 = new CheckPointTrial(this, 140f * Util.changeX(),
					600f * Util.changeY(), 0.1f, 40f, "checkPoint1");
			checkPoint2 = new CheckPointTrial(this, 780f * Util.changeX(),
					840f * Util.changeY(), 0.5f, 40f, "checkPoint2");
			checkPoint3 = new CheckPointTrial(this, 150f * Util.changeX(),
					140f * Util.changeY(), 0.6f, 40f, "checkPoint3");
			checkPoint4 = new CheckPointTrial(this, 808f * Util.changeX(),
					140f * Util.changeY(), -0.5f, 40f, "checkPoint4");
			this.pista = new PistaTrial(this,"teste-body-editor1.json", "pista1.png" , 
					checkPoint1, checkPoint2, checkPoint3,checkPoint4);
		}
		if (pista.equals("pista2")) {
			checkPoint1 = new CheckPointTrial(this, 140f * Util.changeX(),
					600f * Util.changeY(), 0.1f, 40f, "checkPoint1");
			checkPoint2 = new CheckPointTrial(this, 780f * Util.changeX(),
					840f * Util.changeY(), 0.5f, 40f, "checkPoint2");
			checkPoint3 = new CheckPointTrial(this, 150f * Util.changeX(),
					140f * Util.changeY(), 0.6f, 40f, "checkPoint3");
			checkPoint4 = new CheckPointTrial(this, 808f * Util.changeX(),
					140f * Util.changeY(), -0.5f, 40f, "checkPoint4");
			this.pista = new PistaTrial(this, "teste-body-editor2.json", "pista2.png", 
					checkPoint1, checkPoint2, checkPoint3,checkPoint4);
		}
		if (pista.equals("pista3")) {
			checkPoint1 = new CheckPointTrial(this, 140f * Util.changeX(),
					600f * Util.changeY(), 0.1f, 40f, "checkPoint1");
			checkPoint2 = new CheckPointTrial(this, 780f * Util.changeX(),
					840f * Util.changeY(), 0.5f, 40f, "checkPoint2");
			checkPoint3 = new CheckPointTrial(this, 150f * Util.changeX(),
					140f * Util.changeY(), 0.6f, 40f, "checkPoint3");
			checkPoint4 = new CheckPointTrial(this, 808f * Util.changeX(),
					140f * Util.changeY(), -0.5f, 40f, "checkPoint4");
			this.pista = new PistaTrial(this, "teste-body-editor3.json", "pista3.png", checkPoint1, checkPoint2, checkPoint3,
					checkPoint4);
		}

	}

	public PistaTrial getPista() {
		return pista;
	}

	public World getWorld() {
		return world;
	}

	public Body createBody(BodyDef bodyDef) {
		return world.createBody(bodyDef);

	}

	public MouseJoint getMouseJoint() {
		return mouseJoint;
	}

	private boolean isVitoria = false;

	public void render() {
		world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
		/*
		 * System.out.println("ATIVO: " + tampa1.getBody().isActive());
		 * System.out.println("AWAKE: " + tampa1.getBody().isAwake());
		 * System.out.println("BULLET: " + tampa1.getBody().isBullet());
		 * System.out.println("SLEPING: " +
		 * tampa1.getBody().isSleepingAllowed());
		 * System.out.println("ANGULAR VELOCITY: " +
		 * tampa1.getBody().getAngularVelocity());
		 * System.out.println("DAMPINF VELOCITY: " +
		 * tampa1.getBody().getAngularDamping());
		 * System.out.println("LINEAR DAMPING: " +
		 * tampa1.getBody().getLinearDamping()); System.out.println("X: " +
		 * tampa1.getBody().getLinearVelocity().x + " Y: " +
		 * tampa1.getBody().getLinearVelocity().x);
		 */

		paraTampa1((float) 2.5);

		pista.render();
		isVitoria = isVitoria(tampa1);

		tampa1.render();
		if (!flagStopTampa1) {
			resetarTampas();
		}
		if (!isVitoria) {
			turnAnimation.render();
		}

		if (dragged != null) {
			dragged.render();
		}
	}

	private boolean isVitoria(TampaTrial tampa) {
		if (tampa.isRaceComplete()) {

			CharSequence str = tampa.getId() + " Venceu!!!";

			float textWidth = font.getBounds(str).width;

			int center = Util.center(textWidth);

			spriteBatch.begin();
			font.draw(spriteBatch, str, center, Util.h / 2);
			spriteBatch.end();

			if (!flagStopTampa1) {
				Gdx.app.exit();

			}
			return true;
		}
		return false;
	}

	/**
	 * Para a tampa1 ao chegar em uma determinada velocidade
	 * 
	 * @param velocidade
	 */
	private void paraTampa1(float velocidade) {

		float velocidadeX = Math.abs(tampa1.getBody().getLinearVelocity().x);
		float velocidadeY = Math.abs(tampa1.getBody().getLinearVelocity().y);
		if ((velocidadeX > velocidade || velocidadeY > velocidade)) {
			flagStopTampa1 = true;
			// tampa1.getBody().setAwake(false);
		}
		if (velocidadeX < velocidade && velocidadeY < velocidade
				&& flagStopTampa1) {
			tampa1.getBody().setAwake(false);
			flagStopTampa1 = false;
			turnAnimation = new TurnAnimation(1);
		}
	}

	public TampaTrial getTampa1() {
		return this.tampa1;
	}

	public TampaTrial getTampaDaVez() {
		return tampa1;
	}

	private void resetarTampas() {
		if (tampa1.isReset()) {
			tampa1.getBody().setTransform(tampa1PositionTemp, 0f);
			tampa1.setReset(false);
		}
	}
}