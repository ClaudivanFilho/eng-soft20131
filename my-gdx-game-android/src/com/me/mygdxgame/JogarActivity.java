package com.me.mygdxgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class JogarActivity extends Activity {
	Intent intentEscolher;
	Intent intentTampa;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jogar);
		
		butaoArcade();
		butaoTime();
		butaoCampeonato();
		butaoTreinamento();
		butaoMenu();
	}
	
	private void butaoTime() {
		Button butaoMenu = (Button) findViewById(R.id.butao_timetrial);
		butaoMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//time trial
			}
		});
	}
	
	private void butaoTreinamento() {
		Button butaoMenu = (Button) findViewById(R.id.butao_treinamento);
		butaoMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//time trial
			}
		});
	}
	
	private void butaoMenu() {
		Button butaoMenu = (Button) findViewById(R.id.butao_menu2);
		butaoMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}
	private void butaoCampeonato() {
		Button butaoCampeonato= (Button) findViewById(R.id.butao_campeonato);
		butaoCampeonato.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (intentTampa == null) {
					intentTampa = new Intent(JogarActivity.this, EscolherTampaActivity.class);
					intentTampa.putExtra("pista", "pista1");
					intentTampa.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

				}
				startActivity(intentTampa);
			}
		});
	}
	private void butaoArcade() {
		Button butaoArcade= (Button) findViewById(R.id.butao_arcade);
		butaoArcade.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (intentEscolher == null) {
					intentEscolher = new Intent(JogarActivity.this, EscolherPistaActivity.class);
				}
				startActivity(intentEscolher);
			}
		});
	}
}
