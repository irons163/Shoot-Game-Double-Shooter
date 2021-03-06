package com.example.try_shoot_game;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.try_gameengine.action.Time;
import com.example.try_gameengine.framework.IChessBoard;
import com.example.try_gameengine.framework.IGameController;
import com.example.try_gameengine.framework.IGameModel;
import com.example.try_gameengine.framework.IMoveObserver;



public class GameView extends SurfaceView implements SurfaceHolder.Callback, IMoveObserver{
	private IGameController gameController;
	private GameModel gameModel;
	private SurfaceHolder surfaceHolder;
	
	private boolean isGameRun = true;
	private boolean isGameStop = false;
	private boolean isGameReallyStop = false;
	private boolean isSurfaceCreated = false;
	
	private int[][] allExistPoints;
	private long startTime, endTime, previousStartTime;
	
	public GameView(Context context, IGameController gameController, IGameModel gameModel) {
		super(context);
		// TODO Auto-generated constructor stub
		this.gameController = gameController;
		this.gameModel = (GameModel) gameModel;
		gameModel.registerObserver(this);
//		allExistPoints = gameModel.getAllExistPoints();
		
		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
		
		
	}

//	@Override
//	public void updateChess() {
//		// TODO Auto-generated method stub
//		
//	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		gameController.onTouchEvent(event);
		

		
		return true;
	}
	
	private void process(){
		gameModel.process();
	}
	
//	public static ICircleController circleController1;
//	public static ICircleController circleController2;
	
	private void draw(){
		Canvas canvas = surfaceHolder.lockCanvas();

		canvas.drawColor(Color.BLACK);
		
		gameModel.drawEnemis(canvas);
		gameModel.drawCrosshair(canvas);
//		if(circleController1!=null)
//		circleController1.draw(canvas);
//		if(circleController2!=null)
//			circleController2.draw(canvas);

		surfaceHolder.unlockCanvasAndPost(canvas);
	}
	
	Thread gameThread = new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Time.Time = System.currentTimeMillis();
			while(isGameRun){
				
				previousStartTime = startTime;
				startTime = System.currentTimeMillis();
				if(previousStartTime==0)
					previousStartTime = startTime;
					
				Time.DeltaTime = startTime - previousStartTime;
				process();
				draw();
				if(isGameStop){
					synchronized (GameView.this) {
						try {
							isGameReallyStop = true;
							GameView.this.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	});

	public void stop(){
		isGameStop = true;
		for(int i =0; i< 10; i++){
			if(isGameReallyStop)
				break;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void restart(){
		isGameStop = false;
		
		
//		for(int i =0; i< 100; i++){
//			if(isSurfaceCreated)
//				break;
//			try {
//				Thread.sleep(200);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}	
	}
	
	private void r(){
		synchronized (GameView.this) {
			GameView.this.notify();
		}	
	}
	
	public void resetTime(){
//		Time.time = 0;
		Time.Time = System.currentTimeMillis();
		Time.DeltaTime = 0;
		previousStartTime = startTime = 0;
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		if(!gameThread.isAlive()){
			gameThread.start();
			resetTime();
		}
		isSurfaceCreated = true;
		if(isGameReallyStop=true){
			isGameReallyStop = false;
			resetTime();
			r();
		}
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		isSurfaceCreated = false;
	}

	@Override
	public void updateChess(IChessBoard chessBoard) {
		// TODO Auto-generated method stub
		
	}

	
}
