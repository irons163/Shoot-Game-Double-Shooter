package com.example.try_shoot_game.action;

import java.util.List;

public abstract class MovementActionFactory {
	public abstract MovementAction createMovementAction();
	
	public MovementAction createMovementAction(List<MovementActionInfo> infos){
		throw new UnsupportedOperationException();
	}
}
