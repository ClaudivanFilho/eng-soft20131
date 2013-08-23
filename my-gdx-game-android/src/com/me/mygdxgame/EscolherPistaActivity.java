package com.me.mygdxgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.me.mygdxgame.R;

public class EscolherPistaActivity extends Activity {
	Intent intentPista;
	private Button butaoPista1;
	private Button butaoPista2;
	private Button butaoPista3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_escolherpista);

		// butoes para escolher pista
		butaoPista1();
		butaoPista2();
		butaoPista3();
		butaoVoltar();
		butaoAvancar();
	}

	private void butaoVoltar() {
		ImageButton butaoVoltar = (ImageButton) findViewById(R.id.butao_voltar);
		butaoVoltar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}

	private void butaoAvancar() {
		ImageButton butaoAvancar = (ImageButton) findViewById(R.id.butao_avancar);
		butaoAvancar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				avancarParaJogo();
			}
		});
	}

	private void butaoPista3() {
		butaoPista3 = (Button) findViewById(R.id.butaopista3);
		butaoPista3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Jogar na pista 3
				atualizaSelecao(butaoPista3);

			}

		});
	}

	private void butaoPista2() {
		butaoPista2 = (Button) findViewById(R.id.butaopista2);
		butaoPista2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				atualizaSelecao(butaoPista2);
			}
		});
	}

	private void butaoPista1() {
		butaoPista1 = (Button) findViewById(R.id.butaopista1);
		butaoPista1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				atualizaSelecao(butaoPista1);

			}
		});
	}

	public void avancarParaJogo() {
		if (butaoPista1.isSelected()) {
			System.out.println("Pista 1");
		} else if (butaoPista2.isSelected()) {
			System.out.println("Pista 2");
		} else if (butaoPista3.isSelected()) {
			System.out.println("Pista 3");
		}
		iniciarActiveTampa();

	}

	private void iniciarActiveTampa() {
		if (intentPista == null) {
			intentPista = new Intent(EscolherPistaActivity.this,
					EscolherTampaActivity.class);
		}
		startActivity(intentPista);
	}

	public void setTodosButaoFalse() {
		butaoPista1.setSelected(false);
		butaoPista2.setSelected(false);
		butaoPista3.setSelected(false);
	}

	private void atualizaSelecao(Button butao) {
		setTodosButaoFalse();
		butao.setSelected(true);
	}

}
