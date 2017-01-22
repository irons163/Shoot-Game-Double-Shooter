package com.example.try_shoot_game.action;

import java.util.ArrayList;
import java.util.List;

public class DoubleDecorator extends MovementDecorator{
	private MovementAction action;
	
	public DoubleDecorator(MovementAction action){
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
		info.setTotal(info.getTotal());
		info.setDelay(info.getDelay());
		info.setDx(2 * info.getDx());
		info.setDy(2 * info.getDy());
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
		return "Double " + action.getDescription();
	}

	@Override
	public MovementAction initTimer() {
		if(this.getAction().getActions().size() == 0){
			
		for(MovementAction action : this.getAction().getActions()){
			this.getAction().setInfo(action.getInfo());
			action.getAction().setInfo(getInfo());
			action.getAction().initTimer();
		}
//		for(MovementAction action : copyMovementActionList){
//			this.getAction().addMovementAction(action);
//			action.initTimer();
//		}
		
		}else
		{
			
//			for(MovementAction action : this.getAction().getActions()){
//				action.initTimer();
//			}
			List<MovementActionInfo> currentInfos = new ArrayList<MovementActionInfo>();
			
			List<MovementActionInfo> infos = new ArrayList<MovementActionInfo>();
			for(MovementAction action : this.getAction().getActions()){
//				MovementActionInfo info;
				
				if(action.getAction().getActions().size()!=0){
//					action.initTimer();
//					MovementActionInfo info = action.getAction().getInfo();
//					this.getAction().setInfo(info);
//					MovementActionInfo info = this.action.getInfo();
//					action.getAction().setInfo(info);
//					infos.add(info);
					for(MovementActionInfo info : action.getAction().currentInfoList){
						currentInfos.add(info);
					}
					action.initTimer();
				}else{
					this.getAction().setInfo(action.getInfo());
					MovementActionInfo info = this.action.getInfo();
					action.getAction().setInfo(info);
					infos.add(info);
					action.getAction().initTimer();
				}
				
			}
			
			int infoSize = infos.size();
			
			for(MovementAction action : copyMovementActionList){
				this.getAction().addMovementAction(action);
//				action.initTimer();
			}
			
			copyMovementActionList.clear();
			
			for(int i=0; i < this.getAction().getActions().size(); i++){
				MovementAction action = this.getAction().getActions().get(i);
				
				if(this.getAction().getActions().size()>infoSize && infoSize>0){
					int j = i % infoSize;
					MovementActionInfo info = infos.get(j);
					MovementActionInfo newInfo = new MovementActionInfo(info.getTotal(), info.getDelay(), info.getDx(), info.getDy(), info.getDescription());
					action.getAction().setInfo(coreCalculationMovementActionInfo(newInfo));
					action.getAction().initTimer();
				}else if(infoSize==this.getAction().getActions().size()){
					MovementActionInfo info = infos.get(i);
					action.getAction().setInfo(coreCalculationMovementActionInfo(info));
					action.getAction().initTimer();
				}else{
					for(MovementActionInfo info : currentInfos){
						this.getAction().setInfo(info);
						coreCalculationMovementActionInfo(this.action.getInfo());
					}
					
					for(MovementAction movementItem : this.getAction().movementItemList){
						movementItem.initTimer();
					}
					
//					action.getAction().setInfo(coreCalculationMovementActionInfo(this.action.getInfo()));
					
//					MovementAction movementAction = action.getAction();
//					while(movementAction.isSet()){
//						movementAction = movementAction.getAction().isSet();
//					}
//					action.getAction().initTimer();
//					movementAction.initTimer();
					
				}
				
			}
			
//			for(MovementAction action : this.getAction().getActions()){
//				this.getAction().setInfo(action.getInfo());
//				action.getAction().setInfo(getInfo());
//				action.getAction().initTimer();
//			}

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

	@Override
	public MovementActionInfo getCurrentInfo() {
		// TODO Auto-generated method stub
		return action.getCurrentInfo();
	}
	
	@Override
	public List<MovementAction> getCurrentActionList() {
		// TODO Auto-generated method stub
		return action.getCurrentActionList();
	}
}
