package com.example.try_shoot_game.action;

import android.util.Log;

public class RLMovementActionFactory extends MovementActionFactory{

	@Override
	public MovementAction createMovementAction() {
		// TODO Auto-generated method stub
//		MovementAction action = new MovementActionSet();
		MovementAction action = new DoubleDecorator(new MovementActionSet());
//		action.addMovementAction(new MovementActionItem(1000, 200, 10, 0));
		action.addMovementAction(new DoubleDecorator(new MovementActionItem(1000, 200, 10, 0, "R")) );
//		action.addMovementAction(new MovementActionItem(1000, 200, -10, 0));
		action.addMovementAction(new DoubleDecorator(new MovementActionItem(1000, 200, -10, 0, "L")) );
		action.initTimer();
		
		Log.i("MovementDescription", action.getDescription());
		
		return action;
	}

}
