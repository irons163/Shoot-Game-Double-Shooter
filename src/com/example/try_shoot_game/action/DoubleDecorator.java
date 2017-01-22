package com.example.try_shoot_game.action;

import com.example.try_shoot_game.action.MovementAction.TimerOnTickListener;

public class DoubleDecorator extends MovementDecorator{
	private MovementAction action;
	
	public DoubleDecorator(MovementAction action){
		this.action = action;
	}
	
	@Override
	public int getDx() {
		// TODO Auto-generated method stub
		return coreCalculationDx(action.getDx());
//		return 50;
	}
	
	private int coreCalculationDx(int dx){
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
//
//	@Override
//	public void setTimerOnTickListener(TimerOnTickListener timerOnTickListener) {
//		action.setTimerOnTickListener(timerOnTickListener);
//	}

	@Override
	public MovementAction getAction(){
		return action.getAction();
	}
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Double " + action.getDescription();
	}

	@Override
	public void initTimer() {
		// TODO Auto-generated method stub
//		action.getAction().setDx(getDx());

//		action.getAction().initTimer();
//		action.getAction().initInfo();
		for(MovementAction action : this.getAction().getActions()){
//			action.getAction().setDx(coreCalculationDx(action.getDx()));
			action.getAction().setInfo(coreCalculationMovementActionInfo(action.getInfo()));
			action.getAction().initTimer();
		}
	}

	@Override
	public void addMovementAction(MovementAction action) {
		// TODO Auto-generated method stub
//		getAction().getActions().add(action);
		getAction().addMovementAction(action);
	}
	
	@Override
	protected void setActionsTheSameTimerOnTickListener(){
		getAction().setTimerOnTickListener(timerOnTickListener);
	}

	@Override
	public MovementActionInfo getInfo() {
		// TODO Auto-generated method stub
		return coreCalculationMovementActionInfo(action.getInfo());
	}
}
