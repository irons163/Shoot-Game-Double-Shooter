package com.example.try_shoot_game.enemy;

import java.util.ArrayList;
import java.util.List;

import com.example.try_shoot_game.action.CopyMoveDecorator;
import com.example.try_shoot_game.action.DoubleDecorator;
import com.example.try_shoot_game.action.LRMovementActionFactory;
import com.example.try_shoot_game.action.MovementAction;
import com.example.try_shoot_game.action.MovementActionDecoratorFactory;
import com.example.try_shoot_game.action.MovementActionFactory;
import com.example.try_shoot_game.action.MovementActionSet;
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
//		enemies.add(enemyFactory.createSpecialEnemy(RedEnemy.class, RLMovementActionFactory.class, new int[]{0, 0}));
//		enemies.add(enemyFactory.createSpecialEnemy4(RedEnemy.class, RLMovementActionFactory.class, new int[]{0, 0}, MovementActionDecoratorFactory.createCopyMovementDecorator()));
//		enemies.add(enemyFactory.createRLRedEnemy(new int[]{50, 50}));
//		enemies.add(enemyFactory.createRLBlueEnemy(new int[]{100, 100}));
//		enemies.add(enemyFactory.createSpecialEnemy(BlueEnemy.class, RLMovementActionFactory.class, new int[]{150, 150}));
//		enemies.add(enemyFactory.createSpecialEnemy2(BlueEnemy.class, SpecialMovementActionFactory.class, new int[]{300, 300}, MovementInfoFactory.createSquareMovementInfo()));
//		enemies.add(enemyFactory.createSpecialEnemy3(BlueEnemy.class, SpecialMovementActionFactory.class, new int[]{450, 450}, MovementInfoFactory.createSquareMovementInfo(), MovementActionDecoratorFactory.createDHDMovementDecorator()));
//		enemies.add(enemyFactory.createSpecialEnemy4(RedEnemy.class, RLMovementActionFactory.class, new int[]{600, 600}, MovementActionDecoratorFactory.createDHDMovementDecorator()));
//		enemies.add(enemyFactory.createSpecialEnemy3(BlueEnemy.class, SpecialMovementActionFactory.class, new int[]{750, 750}, MovementInfoFactory.createSquareMovementInfo(), MovementActionDecoratorFactory.createDCMovementDecorator()));
//		enemies.add(enemyFactory.createSpecialEnemy3(RedEnemy.class, SpecialMovementActionFactory.class, new int[]{750, 950}, MovementInfoFactory.createSquareMovementInfo(), MovementActionDecoratorFactory.createCCMovementDecorator()));
//		enemies.add(enemyFactory.createSpecialEnemy3(RedEnemy.class, SpecialMovementActionFactory.class, new int[]{750, 1050}, MovementInfoFactory.createSquareMovementInfo(), MovementActionDecoratorFactory.createCDCMovementDecorator()));
//	
//		enemies.add(enemyFactory.createSpecialEnemy(RedEnemy.class, RLMovementActionFactory.class, new int[]{0, 0}));
		
//		RLMovementActionFactory factory = new RLMovementActionFactory();
//		MovementAction innerAction = factory.createMovementAction();
//		MovementAction action = new DoubleDecorator(innerAction);
////		MovementAction action = new MovementActionSet();
////		action.addMovementAction(new DoubleDecorator(innerAction));
//////		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{0, 1050}, action));
////		MovementAction actionD = new DoubleDecorator(action);
////		actionD = new DoubleDecorator(new DoubleDecorator(actionD));
////		MovementAction newaction = new MovementActionSet();
////		newaction.addMovementAction(new DoubleDecorator(actionD));
//////		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{0, 1100}, actionD));
////		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{0, 1100}, newaction));
//		
//		SpecialMovementActionFactory actionFactory = new SpecialMovementActionFactory();
//		MovementAction RLDRL = actionFactory.createMovementActionByMerge(new RLMovementActionFactory().createMovementAction(), action);
//		RLDRL = actionFactory.createMovementActionByMerge(new RLMovementActionFactory().createMovementAction(), RLDRL);
//		RLDRL = new DoubleDecorator(RLDRL);
////		RLDRL.doInfo();
////		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{0, 1150}, RLDRL));
	
		
		RLMovementActionFactory factory = new RLMovementActionFactory();
		MovementAction innerAction = factory.createMovementAction();
//		MovementAction action = new DoubleDecorator(innerAction);
		MovementAction action = new MovementActionSet();
		action.addMovementAction(new DoubleDecorator(innerAction));
////		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{0, 1050}, action));
//		MovementAction actionD = new DoubleDecorator(action);
		MovementAction actionD = new DoubleDecorator(new DoubleDecorator(action));
//		actionD = new DoubleDecorator(new DoubleDecorator(actionD));
//		MovementAction actionDD = new DoubleDecorator(new DoubleDecorator(actionD));
//		MovementAction actionDD = new DoubleDecorator(actionD);
//		MovementAction newaction = new MovementActionSet();
//		newaction.addMovementAction(new DoubleDecorator(actionDD));
		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{0, 1100}, actionD));
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
