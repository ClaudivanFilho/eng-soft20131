package com.me.mygdxgame;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EscolherTampaActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_escolhertampa);
		
		Button butaoVoltar = (Button) findViewById(R.id.butao_voltarTampa);
		butaoVoltar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		

		Button butaoTampa1 = (Button) findViewById(R.id.butao_tampa1);
		butaoTampa1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				 new MainActivity();

			}
		});
		
		Button butaoTampa2 = (Button) findViewById(R.id.butao_tampa2);
		butaoTampa2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				System.out.println("jogar com tampa 2");
			}
		});
		
		
		
		
	}
}
