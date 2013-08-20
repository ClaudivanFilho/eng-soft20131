package com.me.mygdxgame;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.view.View.OnClickListener;

public class OpcoesActivity extends Activity {
	private int volumeSom = 50;
	private int volumeMusica = 50;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opcoes);

		final SeekBar seekbarSom = (SeekBar) findViewById(R.id.seekBarSom);
		seekbarSom.setProgress(volumeSom);
		seekbarSom.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				volumeSom = seekbarSom.getProgress();
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
			}

		});

		final SeekBar seekbarMusica = (SeekBar) findViewById(R.id.seekBarMusica);
		seekbarMusica.setProgress(volumeMusica);
		seekbarMusica.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				volumeMusica = seekbarMusica.getProgress();
				System.out.println(volumeMusica);
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
			}

		});

		Button butaoMenu = (Button) findViewById(R.id.butao_menu);
		butaoMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}

	public int getVolumeSom() {
		return volumeSom;
	}

	public int getVolumeAudio() {
		return volumeMusica;
	}

}
