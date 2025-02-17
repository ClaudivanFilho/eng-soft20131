package com.me.mygdxgame;

import com.me.mygdxgame.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NovoActivity extends Activity {
	private Intent intentOpcoes;
	private Intent intentJogar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button butaoJogar = (Button) findViewById(R.id.butao_jogar);
		butaoJogar.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				if (intentJogar == null) {
					intentJogar = new Intent(NovoActivity.this, JogarActivity.class);
				}
				startActivity(intentJogar);

			}
		});
		Button butaoOpcoes = (Button) findViewById(R.id.butao_opcoes);
		butaoOpcoes.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				if (intentOpcoes == null) {
					intentOpcoes = new Intent(NovoActivity.this, OpcoesActivity.class);
				}
				startActivity(intentOpcoes);
			}
		});
		Button butaoSair = (Button) findViewById(R.id.butao_sair);
		butaoSair.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				System.exit(0);

			}
		});
	}

	

}
