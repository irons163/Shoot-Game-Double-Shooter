package com.example.try_shoot_game.action;

import java.util.List;

public class HalfDecorator extends MovementDecorator{
	private MovementAction action;
	
	public HalfDecorator(MovementAction action){
		this.action = action;
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
		info.setDx(0.5f * info.getDx());
		info.setDy(0.5f * info.getDy());
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
		return "Half " + action.getDescription();
	}

	@Override
	public MovementAction initTimer() {
		for(MovementAction action : this.getAction().getActions()){
			this.getAction().setInfo(action.getInfo());
			action.getAction().setInfo(getInfo());
			action.getAction().initTimer();
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
