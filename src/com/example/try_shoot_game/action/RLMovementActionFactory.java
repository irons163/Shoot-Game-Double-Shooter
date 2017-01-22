package com.example.try_shoot_game.action;

import android.util.Log;

public class RLMovementActionFactory extends MovementActionFactory{

	@Override
	public MovementAction createMovementAction() {
		// TODO Auto-generated method stub
//		MovementAction action = new MovementActionSet();
		
		if(action==null)
			action = new DoubleDecorator(new MovementActionSet());
		else
			action = new DoubleDecorator(action);
//		if(action==null)
//			action = new MovementActionSet();
		
//		action.addMovementAction(new MovementActionItem(1000, 200, 10, 0));
//		action.addMovementAction(new CopyMoveDecorator(new DoubleDecorator(new MovementActionItem(1000, 200, 10, 0, "R")) ));
		action.addMovementAction(new DoubleDecorator(new MovementActionItem(1000, 200, 10, 0, "R")) );
//		action.addMovementAction(new MovementActionItem(1000, 200, -10, 0));
		action.addMovementAction(new DoubleDecorator(new MovementActionItem(1000, 200, -10, 0, "L")) );
		
		
//		MovementAction action = new MovementActionSet();
//		MovementAction innerAction = new MovementActionSet();
//		innerAction.addMovementAction(new MovementActionItem(1000, 200, 10, 0));
//		
//		action.addMovementAction(new DoubleDecorator(innerAction));
//		action = new DoubleDecorator(innerAction);
		
//		action.initTimer();
		
		Log.i("MovementDescription", action.getDescription());
		
		return action;
	}

}
