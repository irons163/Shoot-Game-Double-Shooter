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
	List<MovementAction> copyMovementActionList = new ArrayList<MovementAction>();
	List<MovementActionInfo> currentInfoList = new ArrayList<MovementActionInfo>();
	
	List<MovementAction> movementItemList = new ArrayList<MovementAction>();
	
	public static List<MovementAction> list = new ArrayList<MovementAction>();
	
	public MovementAction addMovementAction(MovementAction action) {
		throw new UnsupportedOperationException();
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
		public void onTick(float dx, float dy);
	}
	
	public MovementAction initTimer(){
		return this;
		
	}
	
	public abstract float getDx();
	
	public MovementAction getAction(){
		return this;
	}
	
	public void setDx(float dx){

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
	
	public boolean isSet(){
		return false;
	}
	
//	public void addList(MovementActionInfo info){
//		this.currentInfoList.add(info);
//	}
	
	public abstract MovementActionInfo getCurrentInfo();
	
	public abstract List<MovementAction> getCurrentActionList();
	
	public abstract List<MovementActionInfo> getCurrentInfoList();
	
	public void doInfo(){
		getCurrentInfoList();
	}

	public List<MovementAction> getMovementItemList() {
		return movementItemList;
	}
	
	public List<MovementActionInfo> getMovementInfoList() {
		return currentInfoList;
	}

	public void doIn(){
		
	}
}
