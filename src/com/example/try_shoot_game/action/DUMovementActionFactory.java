package com.example.try_shoot_game.action;

public class DUMovementActionFactory extends MovementActionFactory{

	@Override
	public MovementAction createMovementAction() {
		// TODO Auto-generated method stub
		MovementAction action = new MovementActionSet();
		action.addMovementAction(new MovementActionItem(30000, 1000, 0, 10));
		action.addMovementAction(new MovementActionItem(30000, 1000, 0, -10));
		return action;
	}

}
