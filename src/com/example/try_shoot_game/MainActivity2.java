package com.example.try_shoot_game;

import com.example.try_gameengine.framework.BaseActivity;
import com.example.try_gameengine.framework.BaseInitActivity;
import com.example.try_gameengine.framework.LayerManager;
import com.example.try_shoot_game.scene.BaseScene;
import com.example.try_shoot_game.scene.Scene;
import com.example.try_shoot_game.scene.SceneManager;

import android.os.Bundle;
import android.app.Activity;
import android.app.Application;
import android.util.DisplayMetrics;
import android.view.Menu;

public class MainActivity2 extends BaseInitActivity {
	private IGameModel gameModel;
	private IGameController gameController;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		
//		DisplayMetrics dm = new DisplayMetrics();
//		getWindowManager().getDefaultDisplay().getMetrics(dm);
//		
//		CommonUtil.screenHeight = dm.heightPixels;
//		CommonUtil.screenWidth = dm.widthPixels;
//		
//		BitmapUtil.initBitmap(this);
//		
//		LayerManager.initLayerManager();
		
		Scene scene = new MyScene(this, "a", 1);
		Scene scene2 = new MyScene(this, "b", 2);
		
		SceneManager.addScene(scene);
		SceneManager.addScene(scene2);
		
		SceneManager.startScene(0);
//		SceneManager.startScene(1);
		
//		gameModel = new GameModel(this);
//		gameController = new GameController(this, gameModel);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void initCommonUtil() {
		// TODO Auto-generated method stub
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		CommonUtil.screenHeight = dm.heightPixels;
		CommonUtil.screenWidth = dm.widthPixels;
	}

	@Override
	protected void initBitmapUtil() {
		// TODO Auto-generated method stub
		BitmapUtil.initBitmap(this);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
//		SceneManager.next();
		SceneManager.previous();
	}
}
