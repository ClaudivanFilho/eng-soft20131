package com.me.mygdxgame;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class EscolherTampaActivity extends Activity {
	private Button butaoTampa1;
	private Button butaoTampa2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_escolhertampa);

		butaoVoltar();
		butaoIniciarPartida();
		butaoTampa1();
		butaoTampa2();

	}

	private void butaoVoltar() {
		ImageButton butaoVoltar = (ImageButton) findViewById(R.id.butao_voltarTampa);
		butaoVoltar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}

	private void butaoIniciarPartida() {
		ImageButton butaoIniciarPartida = (ImageButton) findViewById(R.id.butao_iniciarPartida);
		butaoIniciarPartida.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				iniciarJogo();
			}
		});
	}

	
	private void butaoTampa2() {
		 butaoTampa2 = (Button) findViewById(R.id.butao_tampa2);
		butaoTampa2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				atualizaSelecao(butaoTampa2);
			}
		});
	}

	private void butaoTampa1() {
		 butaoTampa1 = (Button) findViewById(R.id.butao_tampa1);
		butaoTampa1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				atualizaSelecao(butaoTampa1);
			}

			
		});
	}
	
	public void setTodosButaoFalse() {
		butaoTampa1.setSelected(false);
		butaoTampa2.setSelected(false);
	}

	private void atualizaSelecao(Button butao) {
		setTodosButaoFalse();
		butao.setSelected(true);
	}
	
	private void iniciarJogo() {
		
		Intent inciarJogo = new Intent(EscolherTampaActivity.this,
				IniciarJogoActivity.class);
		if(butaoTampa1.isSelected()){
			inciarJogo.putExtra("tampa1", "tampa1.png");
			inciarJogo.putExtra("tampa2", "tampa2.png");

		}else{
			inciarJogo.putExtra("tampa1", "tampa2.png");
			inciarJogo.putExtra("tampa2", "tampa1.png");
		}
		startActivity(inciarJogo);
	}
}
