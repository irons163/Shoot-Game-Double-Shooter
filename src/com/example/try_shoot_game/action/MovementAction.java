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
	
	List<MovementAction> totalCopyMovementActionList = new ArrayList<MovementAction>();
	
	protected boolean isCancelFocusAppendPart = false;
	
	public boolean isFinish = false;
	
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
	
	public MovementAction initMovementAction(){
		return initTimer();
	}
	
	protected MovementAction initTimer(){
		return this;
		
	}
	
	public MovementAction getAction(){
		return this;
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

	public List<MovementActionInfo> getStartMovementInfoList() {
		getCurrentInfoList();
		return getMovementInfoList();
	}

	public boolean isCancelFocusAppendPart() {
		return isCancelFocusAppendPart;
	}

	public void setCancelFocusAppendPart(boolean isCancelFocusAppendPart) {
		this.isCancelFocusAppendPart = isCancelFocusAppendPart;
	}
	
	protected MovementAction cancelAction;
	
	void cancelAllMove(){
		for(MovementAction action : this.getAction().actions){
			action.cancelMove();
		}
	}
	
	void cancelMove(){
		for(MovementAction action : cancelAction.getAction().actions){
			action.cancelMove();
		}
		
		this.thread.interrupt();
		
//		this.getAction().cancelMove();
	}
	
	void pause(){
//		if(cancelAction.getAction().actions.size()!=0){
////			for(MovementAction action : cancelAction.getAction().actions){
////				action.pause();
////			}
//			cancelAction.getAction().pause();
//		}else{
//			cancelAction.getAction().pause();
//		}
		
//		if(cancelAction!=null){
//			
//		}else{
//			
//		}
		cancelAction.getAction().pause();
	}
	
	public MovementAtionController controller;
	
	public void setMovementActionController(MovementAtionController controller){
		this.controller = controller;
		this.controller.setMovementAction(this);
	}
	
	public boolean isFinish(){
		return cancelAction.getAction().isFinish();
	}
	
	String name="";
	public void setName(String name){
		this.name = name;
	}
	
	public MovementAction getPartOfMovementActionByName(String name){
		return getMovement(this, name);
	} 
	
	private MovementAction getMovement(MovementAction action, String name){
		for(MovementAction movementAction : action.getAction().getActions()){
			if(action.name.equals(name))
				return action;
			return getMovement(movementAction, name);
		}
		return null;
	}
}
