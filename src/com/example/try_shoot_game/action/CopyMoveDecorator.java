package com.example.try_shoot_game.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CopyMoveDecorator extends MovementDecorator{
	private MovementAction action;
	
	public CopyMoveDecorator(MovementAction action){
		this.action = action;
		this.copyMovementActionList = action.copyMovementActionList;
	}
	
	@Override
	public float getDx() {
		return coreCalculationDx(action.getDx());
	}
	
	private float coreCalculationDx(float dx){
		return 2 * dx;
	}
	
	private MovementActionInfo coreCalculationMovementActionInfo(MovementActionInfo info){
		MovementActionInfo newInfo = new MovementActionInfo(info.getTotal(), info.getDelay(), info.getDx(), info.getDy(), info.getDescription());
		if(this.getAction().getActions().size() != 0){
			copyMovementActionList.add(new MovementActionItem(newInfo));
		}
		return info;
	}
	
	@Override
	public void start(){
		action.getAction().start();
	}

	@Override
	public MovementAction getAction(){
		return action.getAction();
	}
	
	@Override
	public String getDescription() {
		return "Copy " + action.getDescription();
	}

	
	
	@Override
	public MovementAction initTimer() {
//		for(MovementAction action : this.getAction().getActions()){
//		Iterator<MovementAction> iterator = this.getAction().getActions().iterator();
//		while(iterator.hasNext()){
//			MovementAction action = iterator.next();
		
		
////		if(this.getAction().getActions().size() != 0){
//			for(MovementAction action : this.getAction().getActions()){
//				this.getAction().setInfo(action.getInfo());
//				action.getAction().setInfo(getInfo());
////				copyMovementActionList.add(new MovementActionItem(action.getAction().getInfo()));
//				action.getAction().initTimer();
//			}
////		}
////		else{
////			copyMovementActionList.add(new MovementActionItem(action.getInfo()));
////		}
//		
////		for(MovementAction action : copyMovementActionList){
////			this.getAction().addMovementAction(action);
////			action.initTimer();
////		}
//			
//			
//			
//			if(this.getAction().getActions().size() != 0){
//				for(MovementAction action : copyMovementActionList){
//					this.getAction().addMovementAction(action);
//					action.initTimer();
//				}
//			}
		
		if(this.getAction().getActions().size() == 0){
			
			for(MovementAction action : this.getAction().getActions()){
				this.getAction().setInfo(action.getInfo());
				action.getAction().setInfo(getInfo());
				action.getAction().initTimer();
			}
		
		}else
		{
			List<MovementActionInfo> infos = new ArrayList<MovementActionInfo>();
			for(MovementAction action : this.getAction().getActions()){
				this.getAction().setInfo(action.getInfo());
				MovementActionInfo info = this.action.getInfo();
				action.getAction().setInfo(info);
				infos.add(info);
			}
			
			int infoSize = infos.size();
			
			for(MovementAction action : copyMovementActionList){
				this.getAction().addMovementAction(action);
			}
			
			copyMovementActionList.clear();
			
			for(int i=0; i < this.getAction().getActions().size(); i++){
				MovementAction action = this.getAction().getActions().get(i);
				
				if(this.getAction().getActions().size()>infoSize  && infoSize>0 ){
					int j = i % infoSize;
					MovementActionInfo info = infos.get(j);
					MovementActionInfo newInfo = new MovementActionInfo(info.getTotal(), info.getDelay(), info.getDx(), info.getDy(), info.getDescription());
					action.getAction().setInfo(coreCalculationMovementActionInfo(newInfo));
				}else{
					MovementActionInfo info = infos.get(i);
					action.getAction().setInfo(coreCalculationMovementActionInfo(info));
				}
				
				action.getAction().initTimer();
			}
			
			for(MovementAction action : copyMovementActionList){
				this.getAction().addMovementAction(action);
				action.initTimer();
			}
		}
		return this;
	}

	@Override
	public void addMovementAction(MovementAction action) {
		getAction().addMovementAction(action);
	}
	
	@Override
	protected void setActionsTheSameTimerOnTickListener(){
		getAction().setTimerOnTickListener(timerOnTickListener);
	}

	@Override
	public MovementActionInfo getInfo() {
		return coreCalculationMovementActionInfo(action.getInfo());
	}
}