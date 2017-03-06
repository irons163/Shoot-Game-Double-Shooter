package com.example.try_shoot_game;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;

import com.example.try_gameengine.framework.IGameController;
import com.example.try_gameengine.framework.IGameModel;
import com.example.try_gameengine.framework.LayerManager;
import com.example.try_gameengine.scene.Scene;
import com.example.try_gameengine.scene.SceneManager;
import com.example.try_gameengine.stage.Stage;

public class MainActivity2 extends Stage {
	private IGameModel gameModel;
	private IGameController gameController;
	private SceneManager sceneManager;
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
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		CommonUtil.screenHeight = dm.heightPixels;
		CommonUtil.screenWidth = dm.widthPixels;
		
		BitmapUtil.initBitmap(this);
//		LayerManager.initLayerManager();
//		LayerManager.setLayerBySenceIndex(0);
		Scene scene = new MyScene(this, "a", 1);
		Scene scene2 = new MyScene(this, "b", 2);
		Scene scene3 = new MyScene(this, "c", 3);
		
		sceneManager = SceneManager.getInstance();
		sceneManager.addScene(scene);
		sceneManager.addScene(scene2);
		sceneManager.addScene(scene3);
	
//		sceneManager.startScene(0);
		sceneManager.startScene(1);
		
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
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
//		SceneManager.next();
		sceneManager.previousWithExistedScenes();
	}

	@Override
	public SceneManager initSceneManager() {
		// TODO Auto-generated method stub
		return null;
	}
}
