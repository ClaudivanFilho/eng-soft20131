package com.me.mygdxgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.me.mygdxgame.R;

public class EscolherPistaActivity extends Activity {
	Intent intentPista;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_escolherpista);

		//butoes para escolher pista
		Button butaoPista1 = (Button) findViewById(R.id.butaopista1);
		butaoPista1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//Jogar na pista 1
				if (intentPista == null) {
					intentPista = new Intent(EscolherPistaActivity.this, EscolherTampaActivity.class);
				}
				startActivity(intentPista);
			}
		});
		Button butaoPista2 = (Button) findViewById(R.id.butaopista2);
		butaoPista2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//Jogar na pista 2
				if (intentPista == null) {
					intentPista = new Intent(EscolherPistaActivity.this, EscolherTampaActivity.class);
				}
				startActivity(intentPista);
			}
		});
		Button butaoPista3 = (Button) findViewById(R.id.butaopista3);
		butaoPista3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//Jogar na pista 3
				if (intentPista == null) {
					intentPista = new Intent(EscolherPistaActivity.this, EscolherTampaActivity.class);
				}
				startActivity(intentPista);
			}
		});
		
		
		
		Button butaoVoltar = (Button) findViewById(R.id.butao_voltar);
		butaoVoltar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}
}
