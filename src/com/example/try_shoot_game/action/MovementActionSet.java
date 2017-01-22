package com.example.try_shoot_game.action;

import java.util.ArrayList;
import java.util.List;

import com.example.try_shoot_game.action.MovementAction.TimerOnTickListener;

public class MovementActionSet extends MovementAction {
	private boolean isActionFinish = true;
//	private int dx;
	
	@Override
	public void addMovementAction(MovementAction action) {
		// TODO Auto-generated method stub
		actions.add(action);
//		description = "Set[";
	}

	@Override
	protected void setActionsTheSameTimerOnTickListener() {
		for (MovementAction action : actions) {
			action.getAction().setTimerOnTickListener(timerOnTickListener);
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
					while (actions.size() != 0) {
						MovementAction action = actions.get(0).getAction();
						actions.remove(0);
						action.start();
						synchronized (action) {
							try {
								action.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}
					isActionFinish = true;
				}
			});

			thread.start();
		}
	}

	@Override
	public int getDx() {
		// TODO Auto-generated method stub
//		return dx;
		return 0;
	}
//	
//	@Override
//	public void setDx(int dx) {
//		// TODO Auto-generated method stub
//		this.dx = dx;
//	}

//	@Override
//	public void initInfo(){
//		for (MovementAction action : actions) {
//			int dx = action.getDx();
//			action.getAction().setDx(dx);		
//		}
//	}
	
	@Override
	public void initTimer() {
//		getAction().getDx();
		
		for (MovementAction action : actions) {
//			int dx = action.getDx();
//			action.getAction().setDx(dx);
			MovementActionInfo info = action.getInfo();
			action.getAction().setInfo(info);
			action.getAction().initTimer();
		}
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
		return null;
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
}
