package com.me.mygdxgame;

import java.io.File;
import aurelienribon.bodyeditor.BodyEditorLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Pista {
	private TampinhaWorld world;
	private Texture texture;
	private Sprite sprite;
	private Body speedwayBody;
	private SpriteBatch spriteBatch;
	private Vector2 speedWayBodyOrigin;
	private Vector2 speedWayPosition;
	private CheckPoint checkPoint1;
	private CheckPoint checkPoint2;
	private CheckPoint checkPoint3;
	private CheckPoint checkPoint4;
	private String imageURL;
	private String bodyPath;
			

	public Pista(TampinhaWorld world,  String bodyName,String imageName,
			CheckPoint checkPoint1, CheckPoint checkPoint2,
			CheckPoint checkPoint3, CheckPoint checkPoint4) {
		this.bodyPath = "data" + File.separator + bodyName;
		this.imageURL = "data" + File.separator + imageName;
		this.world = world;
		this.checkPoint1 = checkPoint1;
		this.checkPoint2 = checkPoint1;
		this.checkPoint3 = checkPoint1;
		this.checkPoint4 = checkPoint1;

		criarPista();
		criarImagemDaPista();

	}

	private void criarPista() {
		// 0. Create a loader for the file saved from the editor.

		BodyEditorLoader loader = new BodyEditorLoader(
				Gdx.files.internal(bodyPath));

		// 1. Create a BodyDef, as usual.
		BodyDef bd = new BodyDef();
		speedWayPosition = new Vector2(0 * Util.changeX(), 0 * Util.changeX());// TODO
																				// padronizar
																				// as
																				// posições
		bd.position.set(speedWayPosition);
		bd.type = BodyType.StaticBody;

		// 2. Create a FixtureDef, as usual.
		FixtureDef fd = new FixtureDef();
		fd.density = 1;
		fd.friction = 0.5f;
		fd.restitution = 0.3f;
		fd.isSensor = true;
		speedwayBody = world.createBody(bd);
		speedwayBody.setUserData("Pista");
		float SPEEDWAY_WIDTH = 1024 * Util.changeX();

		// 4. Create the body fixture automatically by using the loader.
		loader.attachFixture(speedwayBody, "pista1", fd, SPEEDWAY_WIDTH);
		speedWayBodyOrigin = loader.getOrigin("pista1", SPEEDWAY_WIDTH).cpy();
		speedWayBodyOrigin.mul(0.1f, 0.1f);
	}

	public CheckPoint getCheckPoint1() {
		return checkPoint1;
	}

	public void setCheckPoint1(CheckPoint checkPoint1) {
		this.checkPoint1 = checkPoint1;
	}

	public CheckPoint getCheckPoint2() {
		return checkPoint2;
	}

	public void setCheckPoint2(CheckPoint checkPoint2) {
		this.checkPoint2 = checkPoint2;
	}

	public CheckPoint getCheckPoint3() {
		return checkPoint3;
	}

	public void setCheckPoint3(CheckPoint checkPoint3) {
		this.checkPoint3 = checkPoint3;
	}

	public CheckPoint getCheckPoint4() {
		return checkPoint4;
	}

	public void setCheckPoint4(CheckPoint checkPoint4) {
		this.checkPoint4 = checkPoint4;
	}

	private void criarImagemDaPista() {
		texture = new Texture(imageURL);

		// setting a filter is optional, default = Nearest
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		// binding texture to sprite and setting some attributes
		sprite = new Sprite(texture);
		sprite.setSize(1024 * Util.changeX(), 1024 * Util.changeY());
		// sprite.setScale(60f);
		// sprite.setPosition(500,500);//TODO padronizar as posições

		spriteBatch = new SpriteBatch();

	}

	public void render() {

		spriteBatch.begin();
		// Setting the position
		Vector2 pistaPos = speedwayBody.getPosition().sub(speedWayBodyOrigin);

		sprite.setPosition(pistaPos.x, pistaPos.y);
		sprite.setOrigin(speedWayBodyOrigin.x, speedWayBodyOrigin.y);
		sprite.setRotation(speedwayBody.getAngle() * MathUtils.radiansToDegrees);

		// this is only one possible drawing out of many
		TextureRegion region = new TextureRegion(texture, 0, 0, 720, 960);
		spriteBatch.draw(region, 0, 0, 1024 * Util.changeX(),
				1024 * Util.changeY());

		// this is another one

		// and a third...
		// sprite.draw(spriteBatch, 100);
		// sprite.draw(spriteBatch);

		spriteBatch.end();

		// re-enable GL state... (if you need it)
		// Gdx.gl.glEnable(GL10.GL_CULL_FACE);

	}

	public Body getBody() {
		return this.speedwayBody;
	}
}
