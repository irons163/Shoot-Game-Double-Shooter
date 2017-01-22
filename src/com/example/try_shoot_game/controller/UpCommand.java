package com.example.try_shoot_game.controller;

public class UpCommand implements Command{
	Player upKey;
	
	public UpCommand(Player upKey){
		this.upKey = upKey;
	}
	
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return upKey.pressUp();
	}

}
