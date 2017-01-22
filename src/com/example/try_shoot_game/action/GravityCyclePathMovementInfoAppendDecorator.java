package com.example.try_shoot_game.action;

import java.util.Collections;
import java.util.List;

import android.util.Log;

public class GravityCyclePathMovementInfoAppendDecorator extends MovementDecorator{
	private MovementAction action;

	public GravityCyclePathMovementInfoAppendDecorator(MovementAction action) {
		this.action = action;
		this.copyMovementActionList = action.copyMovementActionList;
	}

	private MovementActionInfo coreCalculationMovementActionInfo(
			MovementActionInfo info) {
		
		MovementActionInfo newInfo = new MovementActionInfo(info.getTotal(), info.getDelay(), info.getDx(), info.getDy(), info.getDescription(), info.getRotationController(), info.isEnableGravity());
		if(this.getAction().getActions().size() != 0){
			MovementAction action = new MovementActionItem(newInfo);
			copyMovementActionList.add(action);
			this.getAction().totalCopyMovementActionList.add(action);
		}
		
		newInfo.setTotal(info.getTotal());
		newInfo.setDelay(info.getDelay());
//		newInfo.setDx(-info.getDx());
//		newInfo.setDy(-info.getDy());
		newInfo.setDx(info.getDx());
		newInfo.setDy(info.getDy());
		
		if(newInfo.getGravityController()!=null){
			newInfo.setRotationController(info.getRotationController().copyNewRotationController());
			newInfo.getRotationController().setRotation(info.getRotationController().getRotation() + 180);
//			newInfo.getRotationController().setRotation(-info.getRotationController().getRotation());
		}
		if(newInfo.getGravityController()!=null){
			MathUtil mathUtil = info.getGravityController().getMathUtil();
//			mathUtil.inverseAngel();
			newInfo.getGravityController().setMathUtil(mathUtil);
			newInfo.getGravityController().isCyclePath();
		}
		return info;
	}

	@Override
	public void start() {
		action.getAction().start();
	}

	@Override
	public MovementAction getAction() {
		return action.getAction();
	}

	@Override
	public String getDescription() {
		return "Double " + action.getDescription();
	}
	
	@Override
	public MovementAction initMovementAction(){	
		return initTimer();
	}

	@Override
	protected MovementAction initTimer() {

		if (this.getAction().getActions().size() == 0) {

				action.getAction().setInfo(getInfo());
				action.getAction().initTimer();

		} else {	
			this.getAction().initTimer();
			doIn();
		}
		return this;
	}

	@Override
	public MovementAction addMovementAction(MovementAction action) {
		getAction().addMovementAction(action);
		return this;
	}

	@Override
	protected void setActionsTheSameTimerOnTickListener() {
		getAction().setTimerOnTickListener(timerOnTickListener);
	}

	@Override
	public MovementActionInfo getInfo() {
		return coreCalculationMovementActionInfo(action.getInfo());
	}

	@Override
	public List<MovementAction> getCurrentActionList() {
		// TODO Auto-generated method stub
		return action.getCurrentActionList();
	}

	@Override
	public List<MovementActionInfo> getCurrentInfoList() {
		// TODO Auto-generated method stub
		return action.getCurrentInfoList();
	}
	
	@Override
	public List<MovementAction> getMovementItemList() {
		return action.getMovementItemList();
	}
	
	@Override
	public List<MovementActionInfo> getMovementInfoList() {
		return action.getMovementInfoList();
	}
	
	@Override
	public void doIn(){
		action.doIn();
		copyMovementActionList.clear();
		this.getAction().getCurrentInfoList();
		int i = 0;
		
//		for(MovementAction action : this.getAction().getActions()){
//			
//			MovementActionInfo newInfo = new MovementActionInfo(action.get.getTotal(), info.getDelay(), info.getDx(), info.getDy(), info.getDescription());
//			MovementAction action = new MovementActionItem(newInfo);
//			copyMovementActionList.add(action);
//		}
		
		for (int j=0; j<this.getAction().currentInfoList.size(); j++) {
			MovementActionInfo info = this.getAction().currentInfoList.get(j); 
			Log.e("count", ++i + "");
			Log.e("info", info.getDx() + "");
			this.getAction().setInfo(info);
			
//			if(j < copyMovementActionList.size()){
//				this.getAction().getInfo();
//			}else{
				coreCalculationMovementActionInfo(this.getAction().getInfo());
//			}
		}
		
		for(MovementAction action : copyMovementActionList){
			this.getAction().addMovementAction(action);
			this.getAction().movementItemList.add(action);
			action.description = "inverseAppend";
			action.initTimer();
		}
		
//		inverseOrder(this);
		
		

		for (MovementAction movementItem : this.getAction().movementItemList) {
			movementItem.initTimer();
		}
		
	}
	
	private void inverseOrder(MovementAction targetAction){
		Collections.reverse(targetAction.getAction().getActions());
		for(MovementAction action : targetAction.getAction().getActions()){
			inverseOrder(action);
		}
//			inverseOrder(targetAction.get)
	}
	
	private void append(MovementAction targetAction){
		for(MovementAction action : targetAction.getAction().getActions()){
			this.addMovementAction(action);
		}
	}
}