package com.example.try_shoot_game.action;

import java.util.ArrayList;
import java.util.List;

import com.example.try_shoot_game.action.MovementAction.TimerOnTickListener;
import com.rits.cloning.Cloner;

public class MovementActionSet extends MovementAction {
	private boolean isActionFinish = true;
	private MovementActionInfo info;
	
	@Override
	public MovementAction addMovementAction(MovementAction action) {
		// TODO Auto-generated method stub
		actions.add(action);
		
		getCurrentActionList();
		getCurrentInfoList();
		
		return this;
	}

	@Override
	protected void setActionsTheSameTimerOnTickListener() {
		for (MovementAction action : actions) {
			action.getAction().setTimerOnTickListener(timerOnTickListener);
		}
	}

	private void frameStart(){
		for(MovementAction action : actions){
			cancelAction = action;
			
			action.start();
			
		}
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub

		if (isActionFinish) {
			isActionFinish = false;

			thread = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					List<MovementAction> actionss = actions;
					for(MovementAction action : actions){
						cancelAction = action;
//					while (actions.size() != 0) {
//						MovementAction action = actions.get(0).getAction();
//						actions.remove(0);
						action.start();
						synchronized (action.getAction()) {
							try {
								action.getAction().wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}
					synchronized (MovementActionSet.this) {
						MovementActionSet.this.notifyAll();
					}
					isActionFinish = true;
				}
			});

			thread.start();
		}
	}
	
	@Override
	public MovementAction initMovementAction(){
//		Cloner cloner=new Cloner();
//
//		MovementAction actionClone = cloner.deepClone(this);
//		
//		this.actions = actionClone.actions;
		
		return initTimer();
	}
	
	@Override
	protected MovementAction initTimer() {
	
		for (MovementAction action : this.actions) {
			
			if(action.getAction().getActions().size()==0){
			
//				MovementActionInfo info = action.getInfo();
//				action.getAction().setInfo(info);
//				action.getAction().initTimer();
				action.initTimer();
			}else{
				action.initTimer();
				
			}
//			for(MovementAction movementAction : action.copyMovementActionList){
//				this.getAction().movementItemList.add(movementAction);
//			}
			for(MovementAction movementAction : action.getAction().totalCopyMovementActionList){
				this.getAction().movementItemList.add(movementAction);
			}
			
			action.getAction().setCancelFocusAppendPart(true);
		}
		this.getAction().getCurrentInfoList();

		return this;
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
	public void setInfo(MovementActionInfo info){
		this.info = info;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		description = "Set[";
		for (MovementAction action : actions) {
			description += action.getDescription();
		}
		description += "]";
		return description;
	}
	
	@Override
	public List<MovementAction> getCurrentActionList() {
		// TODO Auto-generated method stub
		
		movementItemList.clear();
		for(MovementAction action : actions){
			for(MovementAction actionItem : action.getCurrentActionList()){
				movementItemList.add(actionItem);
			}
		}
		
		return movementItemList;
	}
	
	@Override
	public List<MovementActionInfo> getCurrentInfoList() {
		// TODO Auto-generated method stub

		currentInfoList.clear();
		for(MovementAction action : actions){
			for(MovementActionInfo actionItem : action.getCurrentInfoList()){
				currentInfoList.add(actionItem);
			}
		}
		
		return currentInfoList;
	}
}
