package com.example.try_shoot_game.action;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class DoubleDecorator extends MovementDecorator {
	private MovementAction action;

	public DoubleDecorator(MovementAction action) {
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
		info.setTotal(info.getTotal());
		info.setDelay(info.getDelay());
		info.setDx(2 * info.getDx());
		info.setDy(2 * info.getDy());
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
	public MovementAction initTimer() {

		if (this.getAction().getActions().size() == 0) {

			for (MovementAction action : this.getAction().getActions()) {
				this.getAction().setInfo(action.getInfo());
				action.getAction().setInfo(getInfo());
				action.getAction().initTimer();
			}

		} else {

			List<MovementActionInfo> infos = new ArrayList<MovementActionInfo>();
//			for (MovementAction action : this.getAction().getActions()) {
//
//				if (action.getAction().getActions().size() != 0) {
//					action.initTimer();
////					this.getAction().setInfo(action.getInfo());
////					MovementActionInfo info = this.action.getInfo();
//					
////					MovementActionInfo info = action.getCurrentInfo();
////					action.getAction().setInfo(info);
////					infos.add(info);
////					infos = action.getMovementInfoList();
//				} else {
////					this.getAction().setInfo(action.getInfo());
////					MovementActionInfo info = this.action.getInfo();
//					
//					MovementActionInfo info = action.getInfo();
//					this.getAction().setInfo(info);
//					
//					action.getAction().setInfo(info);
//					infos.add(info);
////					action.getAction().initTimer();
//				}
//			}
			
			this.getAction().initTimer();

			int infoSize = infos.size();

			for (MovementAction action : copyMovementActionList) {
				this.getAction().addMovementAction(action);
			}

			copyMovementActionList.clear();

			for (int i = 0; i < this.getAction().getActions().size(); i++) {
				MovementAction action = this.getAction().getActions().get(i);

				if (this.getAction().getActions().size() > infoSize
						&& infoSize > 0) {
					int j = i % infoSize;
					MovementActionInfo info = infos.get(j);
//					MovementActionInfo newInfo = new MovementActionInfo(
//							info.getTotal(), info.getDelay(), info.getDx(),
//							info.getDy(), info.getDescription());
//					action.getAction().setInfo(
//							coreCalculationMovementActionInfo(newInfo));
//					action.getAction().setInfo(newInfo);
					action.getAction().setInfo(info);
//					action.getAction().initTimer();
				}else if(this.getAction().getActions().size() == infoSize){
//					MovementActionInfo info = infos.get(i);
					
					
//					MovementActionInfo newInfo = new MovementActionInfo(
//							info.getTotal(), info.getDelay(), info.getDx(),
//							info.getDy(), info.getDescription());
//					action.getAction().setInfo(
//							coreCalculationMovementActionInfo(newInfo));
					
//					action.getAction().setInfo(coreCalculationMovementActionInfo(info));
					
					
					
//					action.getAction().setInfo(info);
//					action.getAction().initTimer();
				}
				
			}
			
//			this.getAction().getCurrentInfoList();

			doIn();
			
//			int i = 0;
//			for (MovementActionInfo info : this.getAction().currentInfoList) {
//				Log.e("count", ++i + "");
//				Log.e("info", info.getDx() + "");
//				this.getAction().setInfo(info);
////				coreCalculationMovementActionInfo(this.getAction().getInfo());
////				this.action.initTimer();
//				coreCalculationMovementActionInfo(this.action.getInfo());
//			}
//
//			for (MovementAction movementItem : this.getAction().movementItemList) {
//				movementItem.initTimer();
//			}
			
//			for (MovementAction movementItem : this.getAction().movementItemList) {
//				for (MovementAction movementItem2 : movementItem.movementItemList) {
//					movementItem2.initTimer();
//				}
//			}

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
		action.doIn();
		this.getAction().getCurrentInfoList();
		int i = 0;
		for (MovementActionInfo info : this.getAction().currentInfoList) {
			Log.e("count", ++i + "");
			Log.e("info", info.getDx() + "");
			this.getAction().setInfo(info);
			coreCalculationMovementActionInfo(this.getAction().getInfo());
//			this.action.initTimer();
//			coreCalculationMovementActionInfo(this.action.getInfo());
		}

		for (MovementAction movementItem : this.getAction().movementItemList) {
			movementItem.initTimer();
			if(i==16)
			Log.e("dx", movementItem.getDx() + "");
		}
		
//		for(MovementAction movementItem : MovementAction.list){
//			movementItem.initTimer();
//			if(i==16)
//			Log.e("Dx", movementItem.getDx() + "");
//		}
	}
}
