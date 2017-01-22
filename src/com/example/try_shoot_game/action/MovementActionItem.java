package com.example.try_shoot_game.action;

import java.util.List;

import android.os.CountDownTimer;
import android.os.Looper;
import android.util.Log;

public class MovementActionItem extends MovementAction{
	CountDownTimer countDownTimer; 
	long millisTotal;
	long millisDelay;
	int dx;
	int dy;
	MovementActionInfo info;
	
	public MovementActionItem(long millisTotal, long millisDelay, final int dx, final int dy){
		this(millisTotal, millisDelay, dx, dy, "MovementItem");
//		description = "MovementItem";
	}
	
	public MovementActionItem(long millisTotal, long millisDelay, final int dx, final int dy, String description){
		this.millisTotal = millisTotal;
		this.millisDelay = millisDelay;
		this.dx = dx;
		this.dy = dy;
		info = new MovementActionInfo(millisTotal, millisDelay, dx, dy);
		this.description = description + ",";
	}
	
	public MovementActionItem(MovementActionInfo info){
		millisTotal = info.getTotal();
		millisDelay = info.getDelay();
		dx = info.getDx();
		dy = info.getDy();
		if(info.getDescription()!=null)
			this.description = info.getDescription() + ",";
		this.info = info;
	}
	
	@Override
	public void setTimer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

		
		countDownTimer.start();
	}

	@Override
	public int getDx() {
		// TODO Auto-generated method stub
		return dx;
	}
	
	@Override
	public void setDx(int dx) {
		// TODO Auto-generated method stub
		this.dx = dx;
	}
	
	@Override
	public void initTimer(){
		millisTotal = info.getTotal();
		millisDelay = info.getDelay();
		dx = info.getDx();
		dy = info.getDy();
		
		countDownTimer = new CountDownTimer(millisTotal,
				millisDelay) {

			@Override
			public void onTick(long millisUntilFinished) {
				Log.e("t", millisUntilFinished+"");
				Log.e("t", millisUntilFinished/1000+"");
				// TODO Auto-generated method stub
				int x = dx;
				int y = dy;
				Log.e("dx", dx+"");
				Log.e("dy", dy+"");
				
				timerOnTickListener.onTick(dx, dy);
			}

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				synchronized (MovementActionItem.this) {
					MovementActionItem.this.notifyAll();
				}				
			}
		};
		
	}
	
	@Override
	public MovementAction getAction(){
		return this;
	}
	
	public List<MovementAction> getActions(){
		return actions;
	}

	@Override
	public MovementActionInfo getInfo() {
		// TODO Auto-generated method stub
		return info;
	}

	@Override
	public void setInfo(MovementActionInfo info) {
		// TODO Auto-generated method stub
		this.info = info;
	}
	
}
