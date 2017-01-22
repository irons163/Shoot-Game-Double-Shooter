package com.example.try_shoot_game;

import com.example.try_gameengine.framework.LayerManager;
import com.example.try_shoot_game.scene.Scene;

import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Menu;

public class MainActivity extends Activity {
	private IGameModel gameModel;
	private IGameController gameController;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		CommonUtil.screenHeight = dm.heightPixels;
		CommonUtil.screenWidth = dm.widthPixels;
		
		BitmapUtil.initBitmap(this);
		
		LayerManager.initLayerManager();
		
		Scene scene = new MyScene(this, "a", 1);
		
//		gameModel = new GameModel(this);
//		gameController = new GameController(this, gameModel);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
