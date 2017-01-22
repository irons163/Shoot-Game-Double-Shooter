package com.example.try_shoot_game.action;

import java.util.List;

public class SpecialMovementActionFactory extends MovementActionFactory{

	@Override
	public MovementAction createMovementAction() {
		// TODO Auto-generated method stub
		MovementAction action = new MovementActionSet();
		action.addMovementAction(new MovementActionItem(1000, 200, 10, 0));
		action.addMovementAction(new MovementActionItem(1000, 200, -10, 0));
		action.addMovementAction(new MovementActionItem(1000, 200, -10, 0));
		action.addMovementAction(new MovementActionItem(1000, 200, -10, 0));
		action.addMovementAction(new MovementActionItem(1000, 200, -10, 0));
		action.addMovementAction(new MovementActionItem(1000, 200, -10, 0));
		return action;
	}

	@Override
	public MovementAction createMovementAction(List<MovementActionInfo> infos) {
		// TODO Auto-generated method stub
		MovementAction action = new MovementActionSet();
		for(MovementActionInfo info : infos){
//			action.addMovementAction(new MovementActionItem(info.getTotal(), info.getDelay(), info.getDx(), info.getDy()));
			action.addMovementAction(new MovementActionItem(info));
			action.initTimer();
		}
		return action;
	}
	
	

}
