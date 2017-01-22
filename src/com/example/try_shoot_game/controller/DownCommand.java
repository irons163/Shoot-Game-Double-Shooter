package com.example.try_shoot_game.controller;

public class DownCommand implements Command{
	Player upKey;
	
	public DownCommand(Player upKey){
		this.upKey = upKey;
	}
	
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		return upKey.pressDown();
	}

}
