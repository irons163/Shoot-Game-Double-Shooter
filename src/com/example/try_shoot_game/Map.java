package com.example.try_shoot_game;

import com.example.try_shoot_game.action.RLMovementActionFactory;

public class Map {
	public static int[][] map1 = new int[][] { { 0, 10, 30000, 1000, 10, 10 },
			{ 0, 10, 30000, 1000, 10, 50 }, { 1, 500, 30000, 1000, 10, -10 } };
	
	public static Object[][] level1 = new Object[][] { { RLMovementActionFactory.class, 0, 10, 30000, 1000, 10, 10 },
		{ 0, 10, 30000, 1000, 10, 50 }, { 1, 500, 30000, 1000, 10, -10 } };
	
	public static void createLevel1Enemy(){
		
	}
}

