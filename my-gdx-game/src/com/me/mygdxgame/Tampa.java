package com.me.mygdxgame;

import java.io.File;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class Tampa {
	private String imageURL = "data" + File.separator + "tampa1.png";
	private Body circleBody;
	private SpriteBatch spriteBatch;
	private Sprite sprite;
	private Texture texture;
	private Vector2 position;

	public Tampa(TampinhaWorld world, Vector2 tampa1Position) {
		this.position = tampa1Position;
		createCircleBody(world, this.position);
		createImage();
	}

	private void createCircleBody(TampinhaWorld world, Vector2 position) {
		BodyDef circleBodyDef = new BodyDef();
		circleBodyDef.type = BodyType.DynamicBody;
		circleBodyDef.position.set(position);
		circleBodyDef.angularDamping = 10f;
		circleBodyDef.linearDamping = 0.6f;
		circleBody = world.createBody(circleBodyDef);

		CircleShape dynamicCircle = new CircleShape();
		dynamicCircle.setRadius(16f);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicCircle;
		fixtureDef.density = 1.0f;
		fixtureDef.friction = 5.0f;
		fixtureDef.restitution = 1;
		circleBody.createFixture(fixtureDef);
	}

	private void createImage() {
		// loading a texture from image file
		texture = new Texture(imageURL);

		// setting a filter is optional, default = Nearest
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		// binding texture to sprite and setting some attributes
		sprite = new Sprite(texture);
		sprite.setSize(32f, 32f);
		sprite.setPosition(circleBody.getPosition().x - 16,
				circleBody.getPosition().y - 16);

		spriteBatch = new SpriteBatch();

	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public Body getBody() {
		return circleBody;
	}

	public void render() {
		spriteBatch.begin();
		sprite.setPosition(circleBody.getPosition().x - 16,
				circleBody.getPosition().y - 16);

		// this is only one possible drawing out of many
		sprite.draw(spriteBatch);

		// this is another one
		spriteBatch.draw(texture, circleBody.getPosition().x,
				circleBody.getPosition().y, 32, 32, texture.getWidth(),
				texture.getHeight());

		// and a third...
		sprite.draw(spriteBatch, 100);

		spriteBatch.end();

		// re-enable GL state... (if you need it)
//		Gdx.gl.glEnable(GL10.GL_CULL_FACE);
	}

	public Sprite getSprite() {
		return sprite;
	}

}
