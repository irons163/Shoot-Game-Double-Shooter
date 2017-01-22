package com.example.try_shoot_game.action;


public class MovementAtionController {
	MovementAction action;
	
	public void setMovementAction(MovementAction action){
		this.action = action;
	}
	
	public void cancelCurrentMove(){
		action.cancelMove();
	}
	
	public void cancelAllMove(){
		action.cancelAllMove();
	}
	
	public void pause(){
		action.pause();
	}
	
	public void resume(){
		action.pause();
	}
	
	public void pause(long ms){
		action.pause();
	}
	
	public void restart(){
		action.cancelMove();
		action.start();
	}
}
