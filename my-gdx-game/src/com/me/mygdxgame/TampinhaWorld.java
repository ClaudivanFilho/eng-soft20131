package com.me.mygdxgame;

import java.io.File;

import trialMode.CheckPointTrial;
import trialMode.PistaTrial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;

public class TampinhaWorld {

	private World world;
	private Vector2 gravity;
	private Body currentBody;
	protected Tampa tampa1;
	private Tampa tampa2;
	private Pista pista;
	private SpriteBatch spriteBatch;
	private BitmapFont font;
	static final float BOX_STEP = 1 / 60f;
	static final int BOX_VELOCITY_ITERATIONS = 6;
	static final int BOX_POSITION_ITERATIONS = 2;
	static final float WORLD_TO_BOX = 0.01f;
	static final float BOX_WORLD_TO = 100f;
	private String urlImagemTampa1;
	private String urlImagemTampa2;

	public MouseJoint mouseJoint;

	// se a flagStop estiver true indica que tem alguma tampa em movimento.
	public boolean flagStopTampa1 = false;
	public boolean flagStopTampa2 = false;

	TurnAnimation turnAnimation;
	MouseDraggedAction dragged;
	Tampa tampaDaVez;
	Vector2 tampa1PositionTemp = new Vector2(100 * Util.changeX(),
			564 * Util.changeY());
	Vector2 tampa2PositionTemp = new Vector2(150 * Util.changeX(),
			564 * Util.changeY());

	public TampinhaWorld(String urlImagemTampa1, String urlImagemTampa2,
			String pista) {
		this.spriteBatch = new SpriteBatch();
		this.font = new BitmapFont();
		this.urlImagemTampa1 = urlImagemTampa1;
		this.urlImagemTampa2 = urlImagemTampa2;
		// Cria o Mundo
		this.gravity = new Vector2();
		this.world = new World(gravity, true);
		// Cria as duas tampas
		criarTampas(pista);

		tampaDaVez = tampa1;
		// Cria a Pista

		criarPista(pista);

		turnAnimation = new TurnAnimation(1); // começa com o jogador 1
		// Adding the contact listener
		ContactListener listener = new MyContactListener(this);
		world.setContactListener(listener);
	}

	private void criarTampas(String pista) {
		Vector2 tampa1Position =null;
		Vector2 tampa2Position = null ;
		if (pista.equals("pista1")) {
			tampa1Position = new Vector2(80 * Util.changeX(),
					564 * Util.changeY());
			tampa2Position = new Vector2(140 * Util.changeX(),
					570 * Util.changeY());
		}else if(pista.equals("pista2")){
			tampa1Position = new Vector2(170 * Util.changeX(),
					760 * Util.changeY());
			tampa2Position = new Vector2(270 * Util.changeX(),
					760 * Util.changeY());
		}else if(pista.equals("pista3")){
			tampa1Position = new Vector2(340f * Util.changeX(),
					660 * Util.changeY());
			tampa2Position = new Vector2(440 * Util.changeX(),
					660 * Util.changeY());
		}
		
		this.tampa1 = new Tampa(this, tampa1Position, "Tampa1",
				this.urlImagemTampa1);
	
		this.tampa2 = new Tampa(this, tampa2Position, "Tampa2",
				this.urlImagemTampa2);
	}

	private void criarPista(String pista) {

		CheckPoint checkPoint1;
		CheckPoint checkPoint2;
		CheckPoint checkPoint3;
		CheckPoint checkPoint4;
		if (pista.equals("pista1")) {
			checkPoint1 = new CheckPoint(this, 140f * Util.changeX(),
					600f * Util.changeY(), 0.1f, 40f, "checkPoint1");
			checkPoint2 = new CheckPoint(this, 780f * Util.changeX(),
					840f * Util.changeY(), 0.5f, 40f, "checkPoint2");
			checkPoint3 = new CheckPoint(this, 150f * Util.changeX(),
					140f * Util.changeY(), 0.6f, 40f, "checkPoint3");
			checkPoint4 = new CheckPoint(this, 808f * Util.changeX(),
					140f * Util.changeY(), -0.5f, 40f, "checkPoint4");
		
			this.pista = new Pista(this,"teste-body-editor1.json", "pista1.jpg" , checkPoint1, checkPoint2, checkPoint3,checkPoint4);
		}
		if (pista.equals("pista2")) {
			checkPoint1 = new CheckPoint(this, 230f * Util.changeX(),
					800f * Util.changeY(), 0.0f, 40f, "checkPoint1");
			checkPoint2 = new CheckPoint(this, 760f * Util.changeX(),
					840f * Util.changeY(), 0.3f, 40f, "checkPoint2");
			checkPoint3 = new CheckPoint(this, 200f * Util.changeX(),
					190f * Util.changeY(), 0.6f, 40f, "checkPoint3");
			checkPoint4 = new CheckPoint(this, 808f * Util.changeX(),
					160f * Util.changeY(), -1.2f, 40f, "checkPoint4");
			this.pista = new Pista(this, "teste-body-editor2.json", "pista2.jpg", checkPoint1, checkPoint2, checkPoint3,
					checkPoint4);
		}
		if (pista.equals("pista3")) {
			checkPoint1 = new CheckPoint(this, 400f * Util.changeX(),
					690f * Util.changeY(), 0.0f, 40f, "checkPoint1");
			checkPoint2 = new CheckPoint(this, 620f * Util.changeX(),
					840f * Util.changeY(), 0.5f, 40f, "checkPoint2");
			checkPoint3 = new CheckPoint(this, 400f * Util.changeX(),
					300f * Util.changeY(), 0.6f, 40f, "checkPoint3");
			checkPoint4 = new CheckPoint(this, 808f * Util.changeX(),
					140f * Util.changeY(), -0.5f, 40f, "checkPoint4");
			this.pista = new Pista(this, "teste-body-editor3.json", "pista3.jpg", checkPoint1, checkPoint2, checkPoint3,
					checkPoint4);
		}

	}

	public Pista getPista() {
		return pista;
	}

	public World getWorld() {
		return world;
	}

	public Body createBody(BodyDef bodyDef) {
		return world.createBody(bodyDef);

	}

	public MouseJoint createMouseJoint(Body tampaBody) {
		tampa1PositionTemp = new Vector2(tampa1.getBody().getPosition());
		tampa2PositionTemp = new Vector2(tampa2.getBody().getPosition());
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
		paraTampa2((float) 2.5);

		pista.render();
		isVitoria = isVitoria(tampa1);
		isVitoria = isVitoria || isVitoria(tampa2);

		tampa1.render();
		tampa2.render();
		if (!flagStopTampa1 && !flagStopTampa2) {
			resetarTampas();
		}
		if (!isVitoria) {
			turnAnimation.render();
		}

		if (dragged != null) {
			dragged.render();
		}
	}

	private boolean isVitoria(Tampa tampa) {
		if (tampa.isRaceComplete()) {

			CharSequence str = tampa.getId() + " Venceu!!!";

			float textWidth = font.getBounds(str).width;

			int center = Util.center(textWidth);

			spriteBatch.begin();
			font.draw(spriteBatch, str, center, Util.h / 2);
			spriteBatch.end();

			if (!flagStopTampa1 && !flagStopTampa2) {
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
		if ((velocidadeX > velocidade || velocidadeY > velocidade)
				&& !flagStopTampa2) {
			flagStopTampa1 = true;
			// tampa1.getBody().setAwake(false);
		}
		if (velocidadeX < velocidade && velocidadeY < velocidade
				&& flagStopTampa1
				&& Math.abs(tampa2.getBody().getLinearVelocity().x) < velocidade
				&& Math.abs(tampa2.getBody().getLinearVelocity().y) < velocidade) {
			tampa1.getBody().setAwake(false);
			flagStopTampa1 = false;
			turnAnimation = new TurnAnimation(2);
		}
	}

	/**
	 * Para a tampa2 ao chegar em uma determinada velocidade
	 * 
	 * @param velocidade
	 */
	private void paraTampa2(float velocidade) {

		float velocidadeX = Math.abs(tampa2.getBody().getLinearVelocity().x);
		float velocidadeY = Math.abs(tampa2.getBody().getLinearVelocity().y);
		if ((velocidadeX > velocidade || velocidadeY > velocidade)
				&& !flagStopTampa1) {
			flagStopTampa2 = true;
			// tampa1.getBody().setAwake(false);
		}
		if (velocidadeX < velocidade && velocidadeY < velocidade
				&& flagStopTampa2
				&& Math.abs(tampa1.getBody().getLinearVelocity().x) < velocidade
				&& Math.abs(tampa1.getBody().getLinearVelocity().y) < velocidade) {
			tampa2.getBody().setAwake(false);
			flagStopTampa2 = false;
			turnAnimation = new TurnAnimation(1);
		}
	}


	public Tampa getTampa1() {
		return this.tampa1;
	}

	public Tampa getTampa2() {
		return this.tampa2;
	}

	public Tampa getTampaDaVez() {
		return tampaDaVez;
	}

	private void resetarTampas() {
		if (tampa1.isReset()) {
			tampa1.getBody().setTransform(tampa1PositionTemp, 0f);
			tampa1.setReset(false);
		}
		if (tampa2.isReset()) {
			tampa2.getBody().setTransform(tampa2PositionTemp, 0f);
			tampa2.setReset(false);
		}

	}
}