package com.example.try_shoot_game.scene;

import com.example.try_shoot_game.IGameController;
import com.example.try_shoot_game.IGameModel;

import android.app.Activity;
import android.content.Context;

public abstract class Scene extends Activity{
	protected IGameModel gameModel;
	protected IGameController gameController;
	private String id;
	protected Context context;
//	public Scene(){
//		
//	}
	protected int level;
	
	public Scene(Context context, String id){
		this.context = context;
		this.id = id;
		initGameModel();
		initGameController();
	}
	
	public Scene(Context context, String id, int level){
		this.context = context;
		this.id = id;
		this.level = level;
		initGameModel();
		initGameController();
	}
	
	public String getId(){
		return id;
	}
	
	public abstract void initGameModel();
	
	public abstract void initGameController();
	
	public void start(){
		gameController.start();
	}
	
	public void stop(){
		gameController.stop();
	}
}
