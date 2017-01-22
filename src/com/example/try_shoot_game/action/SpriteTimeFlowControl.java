package com.example.try_shoot_game.action;

import java.util.ArrayList;
import java.util.List;

import com.example.try_shoot_game.enemy.Enemy;

public class SpriteTimeFlowControl {
	List<Enemy> enemies = new ArrayList<Enemy>();
	Enemy enemy;
	MovementActionFrameItem.FrameTrigger frameTrigger = new MovementActionFrameItem.FrameTrigger() {
		
		@Override
		public void trigger() {
			// TODO Auto-generated method stub
			
		}
	};
	
	IActionListener actionListener = new IActionListener() {
		
		@Override
		public void beforeChangeFrame(int nextFrameId) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterChangeFrame(int periousFrameId) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void actionFinish() {
			// TODO Auto-generated method stub
			
		}
	};
	
	public void addToFlowQueue(Enemy enemy){
//		enemy.getAction().settrigger;
//		enemy.;
		enemies.add(enemy);
	}
	
	public void flow(){
		frameTrigger.trigger();
//		enemy.trigger;
		enemy.getAction().start();
	}
	
	public void allFinishObjectRestart(){
//		if(enemy.getAction().isfinish)
//			enemy.getC().restart();
		
	}
	
	public void allFinishAndNotFinishObjectRestart(){
		enemy.getC().restart();
	}
	
}
