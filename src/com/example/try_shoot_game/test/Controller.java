package com.example.try_shoot_game.test;

import java.util.ArrayList;
import java.util.List;

import com.example.try_shoot_game.action.MovementAction;
import com.example.try_shoot_game.action.MovementActionInfo;
import com.example.try_shoot_game.action.RLMovementActionFactory;
import com.example.try_shoot_game.controller.DownCommand;
import com.example.try_shoot_game.controller.Player;
import com.example.try_shoot_game.controller.RemoteControl;
import com.example.try_shoot_game.controller.UpCommand;
import com.example.try_shoot_game.controller.UpKey;
import com.example.try_shoot_game.enemy.Enemy;
import com.example.try_shoot_game.enemy.EnemyFactory;
import com.example.try_shoot_game.enemy.RedEnemy;

import android.test.AndroidTestCase;

public class Controller extends AndroidTestCase{

	public void testAction() throws Exception{
//		UpKey upKey = new UpKey();
//		UpCommand upCommand = new UpCommand(upKey);
//		RemoteControl remoteControl = new RemoteControl();
//		remoteControl.setCommand(upCommand);
//		String s = remoteControl.buttonWasPressed();
//		
//		assertEquals(true, s.equals("Up"));
		
		Player upKey = new Player();
		UpCommand upCommand = new UpCommand(upKey);
		DownCommand downCommand = new DownCommand(upKey);
		RemoteControl remoteControl = new RemoteControl();
		remoteControl.setCommand(0, upCommand, downCommand);
		String s = remoteControl.onButtonWasPushed(0);
		String s1 = remoteControl.offButtonWasPushed(0);
		
		assertEquals(true, s.equals("Up"));
		assertEquals(true, s1.equals("Down"));
	}

}
