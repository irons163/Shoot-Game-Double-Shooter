package com.example.try_shoot_game;

import com.example.try_shoot_game.enemy.Enemy;
import com.example.try_shoot_game.enemy.EnemyManager;
import com.example.try_shoot_game.shooter.Crosshair;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.MotionEvent;

public class GameModel implements IGameModel{
	private Context context;
	private int[][] allExistPoints;
	
	private EnemyManager enemyManager;
	Crosshair crosshair;
	
	public GameModel(Context context, int level) {
		// TODO Auto-generated constructor stub
		this.context = context;
		
		enemyManager = new EnemyManager();
//		enemyManager.createEnemy();
		
		crosshair = new Crosshair();
		
		if(level==1)
			enemyManager.createLevel1Enemy();
		else if(level==2)
			enemyManager.createLevel2Enemy();
		
		enemyManager.showEnemiesMovementDescriptioins();
		
//		moveUP();
//		moveLeft();
//		chessBoard.setPlayersBySquential(playerManager.getPlayersBySquential());
	}

	@Override
	public void registerObserver(IMoveObserver moveObserver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeObserver(IMoveObserver moveObserver) {
		// TODO Auto-generated method stub
		
	}
	
	int count =0;
	float x = 0;
	float y = 0;
	@Override
	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
//			playerManager.onTouchEvent(event);
		
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			x = event.getX();
			y = event.getY();
			++count;
			if(count%2==1){
				enemyManager.startMoveEnemies();
			}else{
				Enemy enemy = enemyManager.getEnemies().get(0);
				if(enemy.getC()!=null)
				enemy.getC().pause();
			}
		}else if(event.getAction() == MotionEvent.ACTION_MOVE){
			float dx = event.getX() - x;
			float dy = event.getY() - y;
			
			x = event.getX();
			y = event.getY();
			
//			enemyManager.moveEnemies((int)dx, (int)dy);
			crosshair.move(dx, dy);
		}
		
//		int action = event.getAction();
//		int pointerCount = event.getPointerCount();
//		int pointerId = 0;
//		if (pointerCount > 1) {
//			pointerId = (action & MotionEvent.ACTION_POINTER_ID_MASK) >>> MotionEvent.ACTION_POINTER_ID_SHIFT;
//		}
//
//		if ((event.getAction() & MotionEvent.ACTION_MASK) % 5 == MotionEvent.ACTION_DOWN) {
//			x = event.getX(pointerId);
//			y = event.getY(pointerId);
//			if (x >= rStick.left && x <= rStick.right && y >= rStick.top
//					&& y <= rStick.bottom) {
////				stickMovePointIndex = event.getPointerCount();
//				stickMovePointIndex = pointerId;
//				deltaX = x - rStick.left;
//				bStickTouched = true;
//			}
//		} else if ((event.getAction() & MotionEvent.ACTION_MASK) % 5 == MotionEvent.ACTION_MOVE) {
//			System.out.println("bStickTouched= " + bStickTouched);
//			System.out.println("pointerId= " + pointerId);
//
//			// if(bStickTouched && x - deltaX >= 0 && x - deltaX +
//			// widthScreen/3
//			// <= widthScreen) {
//			// rStick.left = (int) (x - deltaX);
//			// rStick.right = (int) (rStick.left + widthScreen/3);
//			// }
//
//			for (int i = 0; i < pointerCount; ++i) {
//				pointerId = event.getPointerId(i);
//				Log.d("pointer id - move", Integer.toString(pointerId));
//
//				if (bStickTouched && stickMovePointIndex == pointerId ) {
//					Log.d("pointer id ", Integer.toString(pointerId)+"stickMovePointIndex"+stickMovePointIndex);
//					
//					if(pointerId>=pointerCount){
//						x = event.getX(pointerId - ((pointerId+1)-pointerCount));
//						y = event.getY(pointerId - ((pointerId+1)-pointerCount));
////						stickMovePointIndex = pointerCount;
//					}else{
//						x = event.getX(pointerId);
//						y = event.getY(pointerId);
//					}
//					
//					if (bStickTouched && x - deltaX <= 0) {
//						rStick.left = 0;
//						rStick.right = (int) (rStick.left + stickWidth);
//					} else if (bStickTouched
//							&& x - deltaX + stickWidth >= widthScreen) {
//						rStick.left = (int) (widthScreen - stickWidth);
//						rStick.right = (int) widthScreen;
//					} else {
//						if (x - deltaX < rStick.left) {
//							stickMoveingLeft = true;
//							stickMoveingRight = false;
//						} else if (x - deltaX > rStick.left) {
//							stickMoveingLeft = false;
//							stickMoveingRight = true;
//						} else {
//							stickMoveingLeft = false;
//							stickMoveingRight = false;
//						}
//						rStick.left = (int) (x - deltaX);
//						rStick.right = (int) (rStick.left + stickWidth);
//					}
//					if (!ball_isRun) {
//						imageX = rStick.left + stickWidth / 2;
//						ballUtils.get(0).setImageX(imageX);
//					}
//				}
//			}
//		} else if ((event.getAction() & MotionEvent.ACTION_MASK) % 5 == MotionEvent.ACTION_UP) {
//			if (bStickTouched && stickMovePointIndex == pointerId) {
//				stickMoveingLeft = false;
//				stickMoveingRight = false;
//				bStickTouched = false;
////				stickMovePointIndex = 0;
//			}
//
//		}
	}

	@Override
	public int[][] getAllExistPoints() {
		// TODO Auto-generated method stub
		return null;
	}

	public void drawEnemis(Canvas canvas){
		enemyManager.drawEnemies(canvas);
	}
	

	public void drawEnemisMove(Canvas canvas) {
		// TODO Auto-generated method stub
		
	}
	
	public void moveUP(){
		CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {
			
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				enemyManager.moveEnemiesUpAndDown(30);
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				
			}
		};
		
		countDownTimer.start();
	}
	
	public void moveLeft(){
		CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {
			
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				enemyManager.moveEnemiesLeftAndRight(30);
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				
			}
		};
		
		countDownTimer.start();
	}
	
	public void drawCrosshair(Canvas canvas){
		crosshair.draw(canvas);
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}
}
