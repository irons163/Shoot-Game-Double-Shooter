package com.example.try_shoot_game.enemy;

import com.example.try_shoot_game.BitmapUtil;
import com.example.try_shoot_game.action.MovementAction;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.CountDownTimer;

public abstract class Enemy {
	private float x, y;
	protected Bitmap bitmap;
	protected MovementAction action;
	
	public Enemy(float x, float y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
//		this.bitmap = BitmapUtil.redPoint;
		initBitmap();
	}
	
	public Enemy(float x, float y, MovementAction action) {
		this.x = x;
		this.y = y;
//		this.bitmap = BitmapUtil.redPoint;
		initBitmap();
		this.action = action;
		setMovementActioinTimerOnTickListener();
	}
	
	public abstract void initBitmap();
	
	public void startMovementActioin(){
		if(action!=null)
			action.start();
	}
	
	private void setMovementActioinTimerOnTickListener(){
		if(action!=null)
			action.setTimerOnTickListener(new MovementAction.TimerOnTickListener() {
				
				@Override
				public void onTick(float dx, float dy) {
					// TODO Auto-generated method stub
					move(dx, dy);
				}
			});
	}
	
	public void draw(Canvas canvas){
		canvas.drawBitmap(bitmap, x, y, null);
	}
	
	public void move(float dx, float dy){
		x += dx;
		y += dy;
	}
	
	public void moveLeftAndRight(float dx){
		x += dx;
	}
	
	public void moveUpAndDown(float dy){
		y += dy;
	}
	
	public void moveRandom(){
		
	}
	
	public void moveUP(){
		CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {
			
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
//				enemyManager.moveEnemiesUpAndDown(30);
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				
			}
		};
		
		countDownTimer.start();
	}
	
	public void startMoveLeft(int s, int delay, float dx){
		CountDownTimer countDownTimer = new CountDownTimer(s*1000, delay*1000) {
			
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				moveUP();
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
			}
		};
		
		countDownTimer.start();
	}
	
	public void startMoveLeftAndRight(int s, int delay, float dx){
		CountDownTimer countDownTimer = new CountDownTimer(s*1000, delay*1000) {
			
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				moveUP();
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
			}
		};
		
		countDownTimer.start();
	}
	
	public String getMovementActionDescriptions(){
		return action.getDescription();
	}

	public MovementAction getAction() {
		return action;
	}

	public void setAction(MovementAction action) {
		this.action = action;
	}
	
}
