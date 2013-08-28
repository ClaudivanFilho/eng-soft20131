package com.me.mygdxgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class PausaActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pausa);
		
		butaoVoltarJogo();
		butaoVoltarMenu();
	}

	private void butaoVoltarJogo() {
		Button butaoMenu = (Button) findViewById(R.id.butao_voltarjogo);
		
		butaoMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}
	
	private void butaoVoltarMenu() {
		Button butaoMenu = (Button) findViewById(R.id.butao_voltarmenu);
		
		butaoMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent;
				intent = new Intent(getApplicationContext(),MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra("EXIT", true);
				startActivity(intent);
			}
		});
	}
	

}
