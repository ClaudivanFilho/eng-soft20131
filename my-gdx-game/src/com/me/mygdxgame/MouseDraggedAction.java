package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MouseDraggedAction {

	TampinhaWorld world;
	Camera camera;
	SpriteBatch batchImagem;
	Texture textura;
	Sprite spriteImagem;
	
	float originalX;
	float originalY;
	float atualX;
	float atualY;
	
	/**
	 * Inicia à ação dragged
	 * 
	 * @param world
	 * @param camera
	 */
	public MouseDraggedAction(TampinhaWorld world, Camera camera) {
		
		this.world = world;
		this.camera = camera;
		textura = new Texture(Gdx.files.internal("data/seta.png"));
		spriteImagem = new Sprite(textura);
		
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
		spriteImagem.setSize(64, 64 + Math.abs(originalY-atualY));
		batchImagem = new SpriteBatch();
	}
	
	/**
	 * Desenha a imagem com base no dragged.
	 */
	public void render() {
		Tampa tampa = world.getTampaDaVez();
		batchImagem.begin();
		
		// diferenca entre a posição y pressionada no começo para a posição y
		// atual.
		int diferenca = (int) Math.abs(originalY-atualY);
		
		// seta a posição da imagem
		spriteImagem.setPosition(tampa.getBody().getPosition().x-32
				, (tampa.getBody().getPosition().y-64) - diferenca);
		
		spriteImagem.draw(batchImagem);

		// desenha a imagem relativa à tampa atual e resimensiona a imagem para
		// a diferenca y.
		batchImagem.draw(textura, originalX, originalY, 50, 
				50 + diferenca, 
				textura.getWidth(),	textura.getHeight());

		spriteImagem.draw(batchImagem, 100);
		batchImagem.end();
	}
}
