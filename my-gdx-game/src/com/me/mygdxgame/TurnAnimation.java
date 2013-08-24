package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TurnAnimation {

	private static final int FRAME_COLS = 1;
	private static final int FRAME_ROWS = 8;

	Animation walkAnimation;
	Texture walkSheet;
	TextureRegion[] walkFrames;
	SpriteBatch spriteBatch;
	TextureRegion currentFrame;

	int tampa;

	float stateTime;

	public TurnAnimation(int tampa) {
		this.tampa = tampa;
		create();
	}

	/**
	 * Cria a animação
	 */
	public void create() {
		if (tampa == 1) {
			walkSheet = new Texture(
					Gdx.files.internal("data/animation-turno.png"));
		} else {
			walkSheet = new Texture(
					Gdx.files.internal("data/animation-turno2.png"));
		}
		TextureRegion[][] tmp = TextureRegion.split(walkSheet,
				walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight()
						/ FRAME_ROWS);
		walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation(0.07f, walkFrames);
		spriteBatch = new SpriteBatch();
		stateTime = 0f;
	}

	/**
	 * Executa a animação continuamente.
	 */
	public void render() {
		// Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		if (stateTime < 1) {
			stateTime += Gdx.graphics.getDeltaTime();
			currentFrame = walkAnimation.getKeyFrame(stateTime, true);
			spriteBatch.begin();
			spriteBatch.draw(currentFrame, 100, 320);
			spriteBatch.end();
		}
	}

	public void dispose() {
		spriteBatch.dispose();
	}

	public void reseta() {
		stateTime = 0;
	}

}
