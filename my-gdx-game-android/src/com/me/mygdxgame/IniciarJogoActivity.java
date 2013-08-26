package com.me.mygdxgame;


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

        initialize(new MyGdxGame(tampa1,tampa2), cfg);
    }
}