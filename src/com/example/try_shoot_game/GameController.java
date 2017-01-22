package com.example.try_shoot_game;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public class GameController implements IGameController{
	private IGameModel gameModel;
	private Activity activity;
	GameView gameView;
	
	public GameController(Activity activity, IGameModel gameModel) {
		// TODO Auto-generated constructor stub
		this.gameModel = gameModel;
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

}
