package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class MouseDraggedAction {

	TampinhaWorld world;
	Camera camera;
	
	TextureRegion texturaHead;
	Sprite spriteHead;
	SpriteBatch batchHead;
	
	TextureRegion texturaBody;
	Sprite spriteBody;
	SpriteBatch batchBody;
	
	float originalX; // posicao original do drag
	float originalY; // posicao original do drag
	float atualX; // posicao atual do drag
	float atualY; // posicao atual do drag
	
	boolean estaEmCima = false;
	
	
	/**
	 * Inicia à ação dragged
	 * 
	 * @param world
	 * @param camera
	 */
	public MouseDraggedAction(TampinhaWorld world, Camera camera) {
		
		this.world = world;
		this.camera = camera;
		
		texturaHead = new TextureRegion(new Texture(Gdx.files.internal("data/seta-head.png")));
		texturaBody = new TextureRegion(new Texture(Gdx.files.internal("data/seta-corpo.png")));
		
		originalX = Gdx.input.getX();
		originalY = Gdx.input.getY();
		
		Vector2 currentMousePosition = new Vector2(originalX, 
				Math.abs(originalY - this.camera.viewportHeight));
		this.world.getMouseJoint().setTarget(currentMousePosition);
		
		atualizacordenadasHead();
	}
	
	/**
	 * atualiza as cordenadas quando existe um dragged(puxar).
	 */
	public void atualizacordenadasHead() {
		atualX = Gdx.input.getX();
		atualY = Gdx.input.getY();
		
		Vector2 currentMousePosition = new Vector2(atualX, 
				Math.abs(atualY - this.camera.viewportHeight));
		this.world.getMouseJoint().setTarget(currentMousePosition);
		
		atualizaImagemHead();
	}
	
	/**
	 * atualiza as dimensões da imagem.
	 */
	private void atualizaImagemHead() {
		Tampa tampa = world.getTampaDaVez();
		
		spriteHead = new Sprite(texturaHead);
		batchHead = new SpriteBatch();
		spriteHead.setOrigin((float)32.0, (float)0.0);
		spriteHead.rotate90(true);
		spriteHead.rotate90(true);
		
		spriteBody = new Sprite(texturaBody);
		batchBody = new SpriteBatch();
		spriteBody.setOrigin((float)32.0, (float)-10.0);
		spriteBody.rotate90(true);
		spriteBody.rotate90(true);  
			spriteBody.setSize(spriteBody.getWidth(), 
				spriteBody.getHeight() + 
						(int)Math.sqrt(Math.pow((atualX-originalX),2) + 
						Math.pow((atualY-originalY),2)));
	
		
		double diferencay = atualY - Math.abs(tampa.getBody().getPosition().y - camera.viewportHeight) ;
		double diferencax = atualX - tampa.getBody().getPosition().x ;
		
		//rotaciona a imagem em 180 graus
		if (diferencay < 0) {
			estaEmCima = true; //a seta esta em cima
		} else if (diferencay > 0 ) {
			estaEmCima = false; //esta em baixo
		}
		
		double angulo = 0;
		// calcula o angula entre a tampa e a posicao do toque.
		if (diferencay != 0) {
			angulo = Math.atan(diferencax/diferencay);
		}
		
		// aplica a rotacao na imagem.
		if (!estaEmCima) {
			spriteHead.rotate((int)(angulo*60) + 180);
			spriteBody.rotate((int)(angulo*60) + 180);
		} else {
			spriteHead.rotate((int)(angulo*60));
			spriteBody.rotate((int)(angulo*60));
		}
		
	}
	
	/**
	 * Desenha a imagem com base no dragged.
	 */
	public void render() {
		Tampa tampa = world.getTampaDaVez();
		
		//desenha o corpo da seta
		batchBody.begin();
		spriteBody.setPosition(tampa.getBody().getPosition().x-32
				, (tampa.getBody().getPosition().y+10));
			
		spriteBody.draw(batchBody);
		spriteBody.draw(batchBody, 100);
		
		batchBody.end();
		
		batchHead.begin();
		
		
		// desenha a imagem da cabeca da seta na tampa
		spriteHead.setPosition(tampa.getBody().getPosition().x-32
					, (tampa.getBody().getPosition().y));
		
		spriteHead.draw(batchHead);
		spriteHead.draw(batchHead, 100);
		
		batchHead.end();
	}
}
