package com.example.try_shoot_game.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.util.Log;

public class CopyMoveDecorator extends MovementDecorator {
	private MovementAction action;
	boolean doing = false;
	public CopyMoveDecorator(MovementAction action) {
		this.action = action;
		this.copyMovementActionList = action.copyMovementActionList;
	}

	@Override
	public float getDx() {
		return coreCalculationDx(action.getDx());
	}

	private float coreCalculationDx(float dx) {
		return 2 * dx;
	}

	private MovementActionInfo coreCalculationMovementActionInfo(
			MovementActionInfo info) {
//		if (this.getAction().getActions().size() != 0 && doing) {
//			copyMovementActionList.add(new MovementActionItem(info));
////			this.getAction().getCurrentInfoList();
//		}
//		return info;
		
		MovementActionInfo newInfo = new MovementActionInfo(info.getTotal(), info.getDelay(), info.getDx(), info.getDy(), info.getDescription());
		if(this.getAction().getActions().size() != 0){
			MovementAction action = new MovementActionItem(newInfo);
			copyMovementActionList.add(action);
			MovementAction.list.add(action);
//			copyMovementActionList.add(new MovementActionItem(info));
		}
		return newInfo;
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
		return "Copy " + action.getDescription();
	}

	@Override
	public MovementAction initTimer() {

		if (this.getAction().getActions().size() == 0) {

			for (MovementAction action : this.getAction().getActions()) {
				this.getAction().setInfo(action.getInfo());
				action.getAction().setInfo(getInfo());
				action.getAction().initTimer();
			}

		} else {
			
//			List<MovementActionInfo> infos = new ArrayList<MovementActionInfo>();
			
			this.getAction().initTimer();

//			int infoSize = infos.size();
//
//			for (MovementAction action : copyMovementActionList) {
//				this.getAction().addMovementAction(action);
//			}
//
//			copyMovementActionList.clear();
//
//			for (int i = 0; i < this.getAction().getActions().size(); i++) {
//				MovementAction action = this.getAction().getActions().get(i);
//
//				if (this.getAction().getActions().size() > infoSize
//						&& infoSize > 0) {
//					int j = i % infoSize;
//					MovementActionInfo info = infos.get(j);
//					action.getAction().setInfo(info);
//				}
//			}

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
	public MovementActionInfo getCurrentInfo() {
		// TODO Auto-generated method stub
		return action.getCurrentInfo();
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
//		action.doIn();
//		this.getAction().getCurrentInfoList();
//		for (MovementActionInfo info : this.getAction().currentInfoList) {
////			Log.e("count", ++i + "");
////			Log.e("info", info.getDx() + "");
//			this.getAction().setInfo(info);
////			this.action.initTimer();
////			coreCalculationMovementActionInfo(this.getAction().getInfo());
//			coreCalculationMovementActionInfo(this.action.getInfo());
//		}
		
		action.doIn();
		doing = true;
		copyMovementActionList.clear();
		this.getAction().getCurrentInfoList();
		
//		int s = this.getAction().currentInfoList.size();
//		for (int i =0; i<s; i++) {
//			MovementActionInfo info = this.getAction().currentInfoList.get(i);
//			this.getAction().setInfo(info);
//			coreCalculationMovementActionInfo(this.action.getInfo());
//		}
		
		int i = 0;
		for (MovementActionInfo info : this.getAction().currentInfoList) {
			Log.e("count", ++i + "");
			Log.e("info", info.getDx() + "");
			this.getAction().setInfo(info);
//			this.action.initTimer();
//			coreCalculationMovementActionInfo(this.getAction().getInfo());
			coreCalculationMovementActionInfo(this.getAction().getInfo());
		}

		for (MovementAction action : copyMovementActionList) {
			this.getAction().addMovementAction(action);
			this.getAction().movementItemList.add(action);
			action.initTimer();
		}

		for (MovementAction movementItem : this.getAction().movementItemList) {
			movementItem.initTimer();
		}
	}
}