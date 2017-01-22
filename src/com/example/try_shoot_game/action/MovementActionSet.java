package com.example.try_shoot_game.action;

import java.util.List;

public class MovementActionSet extends MovementAction {
	private boolean isActionFinish = true;
//	private int dx;
	private MovementActionInfo info;
	
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
	public float getDx() {
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
	public MovementAction initTimer() {
//		getAction().getDx();
		
//		Cloner cloner=new Cloner();
//
//		MovementAction actionClone = cloner.deepClone(this);
		
		for (MovementAction action : this.actions) {
			
			if(action.getAction().getActions().size()==0){
			
				MovementActionInfo info = action.getInfo();
				action.getAction().setInfo(info);
				action.getAction().initTimer();
			}else{
				action.initTimer();
			}
		}
		
//		for (MovementAction action : actions) {
//			
//			if(action.getAction().getActions().size()==0){
//			
//				MovementActionInfo info = action.getInfo();
//				action.getAction().setInfo(info);
//				action.getAction().initTimer();
//			}else{
//				action.initTimer();
//			}
//		}
		
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
	public boolean isSet(){
		boolean isSet = false;
		for(MovementAction action : actions){
			isSet = action.isSet();
			if(isSet)
				break;
		}
		return isSet;
	}
}
