package com.example.try_shoot_game.action;

import android.util.Log;

public class RLMovementActionFactory extends MovementActionFactory{

	@Override
	public MovementAction createMovementAction() {
		// TODO Auto-generated method stub
//		MovementAction action = new MovementActionSet();
		MovementAction newAction;
		
		if(action==null){
			newAction = new DoubleDecorator(new MovementActionSet());
		}else
			newAction = new DoubleDecorator(new MovementActionSet());
			
			
//			action.addMovementAction(new DoubleDecorator(new MovementActionSet()));
		
		
//		if(action==null)
//			action = new MovementActionSet();
		
//		action.addMovementAction(new MovementActionItem(1000, 200, 10, 0));
//		action.addMovementAction(new CopyMoveDecorator(new DoubleDecorator(new MovementActionItem(1000, 200, 10, 0, "R")) ));
			newAction.addMovementAction(new DoubleDecorator(new MovementActionItem(1000, 200, 10, 0, "R")) );
//		action.addMovementAction(new MovementActionItem(1000, 200, -10, 0));
			newAction.addMovementAction(new DoubleDecorator(new MovementActionItem(1000, 200, -10, 0, "L")) );
		
			if(action!=null){
				action.addMovementAction(newAction);
				newAction =	action;
			}
			
//		MovementAction action = new MovementActionSet();
//		MovementAction innerAction = new MovementActionSet();
//		innerAction.addMovementAction(new MovementActionItem(1000, 200, 10, 0));
//		
//		action.addMovementAction(new DoubleDecorator(innerAction));
//		action = new DoubleDecorator(innerAction);
		
//		action.initTimer();
		
		Log.i("MovementDescription", newAction.getDescription());
		
		return newAction;
	}

}
