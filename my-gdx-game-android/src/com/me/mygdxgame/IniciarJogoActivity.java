package com.me.mygdxgame;


import android.content.Intent;
import android.os.Bundle;
import android.text.style.BulletSpan;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class IniciarJogoActivity extends AndroidApplication {
	 
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;
        
        Bundle extras = getIntent().getExtras();
        String tampa1 = extras.getString("tampa1");
        String tampa2 = extras.getString("tampa2");
        String pista = extras.getString("pista");
        MyGdxGame novojogo;
        if(pista != null){
        	 novojogo =   new MyGdxGame(tampa1,tampa2,pista);
        }else{
        	novojogo =   new MyGdxGame(tampa1,tampa2);
        }
        initialize(novojogo, cfg);
    }
	@Override
	protected void onDestroy(){
		Intent intent;
		intent = new Intent(getApplicationContext(), MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("EXIT", true);
		startActivity(intent);
		super.onDestroy();
	}

	
	
	@Override
	public void onBackPressed() {
		Intent intent;
		intent = new Intent(getApplicationContext(), PausaActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("EXIT", true);
		startActivity(intent);
	}
}