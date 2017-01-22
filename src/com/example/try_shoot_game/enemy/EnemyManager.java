package com.example.try_shoot_game.enemy;

import java.util.ArrayList;
import java.util.List;

import com.example.try_shoot_game.action.LRMovementActionFactory;
import com.example.try_shoot_game.action.MovementInfoFactory;
import com.example.try_shoot_game.action.RLMovementActionFactory;
import com.example.try_shoot_game.action.SpecialMovementActionFactory;

import android.graphics.Canvas;
import android.util.Log;

public class EnemyManager {
	private List<Enemy> enemies = new ArrayList<Enemy>(); 
	
	public void createEnemy(){
		EnemyFactory enemyFactory = new EnemyFactory();
		enemies.add(enemyFactory.createRedEnemy());
		enemies.add(enemyFactory.createBlueEnemy());
	}
	
	public void createLevel1Enemy(){
		EnemyFactory enemyFactory = new EnemyFactory();
		enemies.add(enemyFactory.createSpecialEnemy(RedEnemy.class, RLMovementActionFactory.class, new int[]{0, 0}));
		enemies.add(enemyFactory.createRLRedEnemy(new int[]{50, 50}));
		enemies.add(enemyFactory.createRLBlueEnemy(new int[]{100, 100}));
		enemies.add(enemyFactory.createSpecialEnemy(BlueEnemy.class, RLMovementActionFactory.class, new int[]{150, 150}));
		enemies.add(enemyFactory.createSpecialEnemy2(BlueEnemy.class, SpecialMovementActionFactory.class, new int[]{300, 300}, MovementInfoFactory.createSquareMovementInfo()));
	}
	
	public List<Enemy> getEnemies(){
		return enemies;
	}
	
	public void drawEnemies(Canvas canvas){
		for(Enemy enemy : enemies){
			enemy.draw(canvas);
		}
	}
	
	public void moveEnemies(int dx, int dy){
		for(Enemy enemy : enemies){
			enemy.move(dx, dy);
		}
	}
	
	public void moveEnemiesUpAndDown(int dy){
		for(Enemy enemy : enemies){
			enemy.moveUpAndDown(dy);
		}
	}
	
	public void moveEnemiesLeftAndRight(int dx){
		for(Enemy enemy : enemies){
			enemy.moveLeftAndRight(dx);
		}
	}
	
	public void startMoveEnemies(){
		for(Enemy enemy : enemies){
			enemy.action.start();
		}
	}
	
	public void showEnemiesMovementDescriptioins(){
		for(Enemy enemy : enemies){
			String description = enemy.getMovementActionDescriptions();
			Log.e("description", description);
		}
	}
}
