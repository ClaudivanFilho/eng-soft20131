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
	private String imageURL;;
	private Body circleBody;
	private SpriteBatch spriteBatch;
	private Sprite sprite;
	private Texture texture;
	private Vector2 position;
	private boolean reset = false;
	private String id;
	private final float width = 56f;
	private boolean checkPointComplete1 = false;
	private boolean checkPointComplete2 = false;
	private boolean checkPointComplete3 = false;
	private boolean checkPointComplete4 = false;

	
	
	
	public boolean isCheckPointComplete1() {
		return checkPointComplete1;
	}

	public boolean isRaceComplete(){
		return checkPointComplete1 && checkPointComplete2 && checkPointComplete3 && checkPointComplete4;
	}
	
	public void setCheckPointComplete1(boolean checkPointComplete1) {
		this.checkPointComplete1 = checkPointComplete1;
	}

	public boolean isCheckPointComplete2() {
		return checkPointComplete2;
	}

	public void setCheckPointComplete2(boolean checkPointComplete2) {
		this.checkPointComplete2 = checkPointComplete2;
	}

	public boolean isCheckPointComplete3() {
		return checkPointComplete3;
	}

	public void setCheckPointComplete3(boolean checkPointComplete3) {
		this.checkPointComplete3 = checkPointComplete3;
	}

	public boolean isCheckPointComplete4() {
		return checkPointComplete4;
	}

	public void setCheckPointComplete4(boolean checkPointComplete4) {
		this.checkPointComplete4 = checkPointComplete4;
	}

	public boolean isReset() {
		return reset;
	}

	public void setReset(boolean reset) {
		this.reset = reset;
	}

	public Tampa(TampinhaWorld world, Vector2 tampa1Position,String id,String imageURL) {
		this.id = id;
		this.imageURL = imageURL;
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
		circleBody.setUserData(this.id);
		CircleShape dynamicCircle = new CircleShape();
		dynamicCircle.setRadius((width/2)*Util.changeX());
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
		sprite.setSize(width*Util.changeX(), width*Util.changeY());
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
		sprite.setPosition(circleBody.getPosition().x - (width/2)*Util.changeX(),
				circleBody.getPosition().y - (width/2)*Util.changeX());

		// this is only one possible drawing out of many
		sprite.draw(spriteBatch);

		// this is another one
		spriteBatch.draw(texture, circleBody.getPosition().x,
				circleBody.getPosition().y, (int)width,(int) width, texture.getWidth(),
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
