package com.example.try_shoot_game.enemy;

import com.example.try_shoot_game.BitmapUtil;
import com.example.try_shoot_game.action.MovementAction;

public class BlueEnemy extends Enemy{

	public BlueEnemy(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public BlueEnemy(int x, int y, MovementAction action) {
		super(x, y, action);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initBitmap() {
		// TODO Auto-generated method stub
		bitmap = BitmapUtil.bluePoint;
	}

}
