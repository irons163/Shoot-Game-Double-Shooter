package com.example.try_shoot_game;


import android.app.Activity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.try_gameengine.framework.IGameController;
import com.example.try_gameengine.framework.IGameModel;

public class GameController implements IGameController{
	private IGameModel gameModel;
	private Activity activity;
	GameView gameView;
	
	public GameController(Activity activity, IGameModel gameModel2) {
		// TODO Auto-generated constructor stub
		this.gameModel = (IGameModel) gameModel2;
		this.activity = activity;
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		if(gameView==null){
			gameView = new GameView(activity, this, gameModel);
			activity.setContentView(gameView);
		}else{
			activity.setContentView(gameView);
			gameView.restart();
//			gameView = new GameView(activity, this, gameModel);
//			activity.setContentView(gameView);
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		gameView.stop();
	}

	@Override
	public void showWin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showLose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		gameModel.onTouchEvent(event);
	}

	@Override
	public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void runStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

}
