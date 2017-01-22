package com.example.try_shoot_game.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class MovementActionFactory {
	protected MovementAction action;
	
	public abstract MovementAction createMovementAction();
	
	public MovementAction actionInitTimer(){
		action.initTimer();
		return action;
	}
	
	public MovementAction createMovementAction(List<MovementActionInfo> infos){
		throw new UnsupportedOperationException();
	}

	public MovementAction createMovementAction(List<MovementActionInfo> infos, List<Class<? extends MovementDecorator>> decoratorClassList){
		throw new UnsupportedOperationException();
	}
	
	public MovementAction createMovementActionByDecorator(List<Class<? extends MovementDecorator>> decoratorClassList){
		action = new MovementActionSet();
		for(Class<? extends MovementDecorator> decoratorClass : decoratorClassList){
			try {
				action = decoratorClass.getConstructor(MovementAction.class).newInstance(action);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return createMovementAction();
	} 
	
	public MovementAction createMovementActionByMerge(MovementAction beginnerAction, MovementAction endAction){
		action = new MovementActionSet();
//		action.addMovementAction(new RLMovementActionFactory().createMovementAction());
		action.addMovementAction(beginnerAction);
		action.addMovementAction(endAction);
		
//		beginnerAction.addMovementAction(endAction);
		
		return action;
	}
	
	public MovementAction createMovementActionByAttachDecorators(MovementAction action, List<Class<? extends MovementDecorator>> decoratorClassList){
//		action = new MovementActionSet();
		for(Class<? extends MovementDecorator> decoratorClass : decoratorClassList){
			try {
				action = decoratorClass.getConstructor(MovementAction.class).newInstance(action);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return action;
	}
}
