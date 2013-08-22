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
	SpriteBatch batchImagem;
	TextureRegion textura;
	Sprite spriteImagem;
	
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
		textura = new TextureRegion(new Texture(Gdx.files.internal("data/seta.png")));
		
		
		originalX = Gdx.input.getX();
		originalY = Gdx.input.getY();
		
		Vector2 currentMousePosition = new Vector2(originalX, 
				Math.abs(originalY - this.camera.viewportHeight));
		this.world.getMouseJoint().setTarget(currentMousePosition);
		
		atualizacordenadas();
	}
	
	/**
	 * atualiza as cordenadas quando existe um dragged(puxar).
	 */
	public void atualizacordenadas() {
		atualX = Gdx.input.getX();
		atualY = Gdx.input.getY();
		
		Vector2 currentMousePosition = new Vector2(atualX, 
				Math.abs(atualY - this.camera.viewportHeight));
		this.world.getMouseJoint().setTarget(currentMousePosition);
		
		atualizaImagem();
	}
	
	/**
	 * atualiza as dimensões da imagem.
	 */
	private void atualizaImagem() {
		
		Tampa tampa = world.getTampaDaVez();
		spriteImagem = new Sprite(textura);
		batchImagem = new SpriteBatch();
		spriteImagem.setOrigin((float)32.0, (float)0.0);
		spriteImagem.rotate90(true);
		spriteImagem.rotate90(true);
		
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
			spriteImagem.rotate((int)(angulo*60) + 180);
		} else {
			spriteImagem.rotate((int)(angulo*60));
		}
		
	}
	
	/**
	 * Desenha a imagem com base no dragged.
	 */
	public void render() {
		Tampa tampa = world.getTampaDaVez();
		batchImagem.begin();
		
		// desenha a imagem da seta na tampa
		spriteImagem.setPosition(tampa.getBody().getPosition().x-32
					, (tampa.getBody().getPosition().y));
			
		spriteImagem.draw(batchImagem);
		spriteImagem.draw(batchImagem, 100);
		
		batchImagem.end();
	}
}
