package com.example.try_shoot_game.controller;

public class RemoteControl {
	Command slot;
	
	Command[] onCommands;
	Command[] offCommands;
	
	public RemoteControl() {
		onCommands = new Command[7];
		offCommands = new Command[7];
		Command noCommand = new NoCommand();
		for(int i=0; i<7; i++){
			onCommands[i] = noCommand;
			offCommands[i] = noCommand;
		}
	}
	
	public void setCommand(Command command){
		slot = command;
	}
	
	public String buttonWasPressed(){
		return slot.execute();
	}
	
	public void setCommand(int slot, Command onCommand, Command offCommand){
		onCommands[slot] = onCommand;
		offCommands[slot] = offCommand;
	}
	
	public String onButtonWasPushed(int slot){
		return onCommands[slot].execute();
	}
	
	public String offButtonWasPushed(int slot){
		return offCommands[slot].execute();
	}
}
