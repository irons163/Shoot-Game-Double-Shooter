package com.example.try_shoot_game.action;

import java.util.ArrayList;
import java.util.List;

import com.example.try_shoot_game.Map;

import android.os.CountDownTimer;

public abstract class MovementAction {
	protected List<MovementAction> actions = new ArrayList<MovementAction>();
	protected Thread thread;
	protected TimerOnTickListener timerOnTickListener;
	protected String description = "Unknown Movement";
	
	public void addMovementAction(MovementAction action) {

	}
	
	public void setTimerOnTickListener(TimerOnTickListener timerOnTickListener) {
			this.timerOnTickListener = timerOnTickListener;
			setActionsTheSameTimerOnTickListener();
	}
	
	protected void setActionsTheSameTimerOnTickListener(){
		
	}
	
	public void setTimer(){
		
	}

	public void start() {

	}
	
	public interface TimerOnTickListener{
		public void onTick(int dx, int dy);
	}
	
	public void initTimer(){
		
	}
	
	public abstract int getDx();
	
	public MovementAction getAction(){
		return this;
	}
	
	public void setDx(int dx){

	}
	
	public List<MovementAction> getActions(){
		return actions;
	}
	
	public abstract MovementActionInfo getInfo();
	
	public void setInfo(MovementActionInfo info){
		
	}
	
	public String getDescription(){
		return description;
	}
}
