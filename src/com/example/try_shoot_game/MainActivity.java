package com.example.try_shoot_game;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;

import com.example.try_gameengine.framework.IGameController;
import com.example.try_gameengine.framework.IGameModel;
import com.example.try_gameengine.framework.LayerManager;

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
		
		com.example.try_gameengine.BitmapUtil.initBitmap(this);
		BitmapUtil.initBitmap(this);
		
//		LayerManager.initLayerManager();
		LayerManager.setLayerBySenceIndex(0);
//		Scene scene = new MyScene(this, "a", 1);
		
		gameModel = (IGameModel) new GameModel(this, 1);
		gameController = (IGameController) new GameController(this, gameModel);
		
		gameController.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
