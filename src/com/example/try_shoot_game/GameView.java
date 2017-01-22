package com.example.try_shoot_game;

import com.example.try_shoot_game.shooter.Crosshair;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback, IMoveObserver{
	private IGameController gameController;
	private IGameModel gameModel;
	private SurfaceHolder surfaceHolder;
	
	private boolean isGameRun = true;
	private int[][] allExistPoints;
	
	
	public GameView(Context context, IGameController gameController, IGameModel gameModel) {
		super(context);
		// TODO Auto-generated constructor stub
		this.gameController = gameController;
		this.gameModel = gameModel;
		gameModel.registerObserver(this);
		allExistPoints = gameModel.getAllExistPoints();
		
		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
		
		
	}

	@Override
	public void updateChess() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		gameController.onTouchEvent(event);
		

		
		return true;
	}
	
	private void process(){
		gameModel.process();
	}
	
	private void draw(){
		Canvas canvas = surfaceHolder.lockCanvas();

		canvas.drawColor(Color.BLACK);
		
		gameModel.drawEnemis(canvas);
		gameModel.drawCrosshair(canvas);
		
//		drawChssboardLines(canvas);
//		drawAllExistPoints(canvas);
//		drawPlayerPocessableMovePoints(canvas);

		surfaceHolder.unlockCanvasAndPost(canvas);
	}
	
	Thread gameThread = new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(isGameRun){
				process();
				draw();
			}
		}
	});

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		gameThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

}
