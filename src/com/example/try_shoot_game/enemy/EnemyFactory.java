package com.example.try_shoot_game.enemy;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import android.app.ListActivity;

import com.example.try_shoot_game.action.MovementActionFactory;
import com.example.try_shoot_game.action.LRMovementActionFactory;
import com.example.try_shoot_game.action.MovementAction;
import com.example.try_shoot_game.action.MovementActionInfo;
import com.example.try_shoot_game.action.RLMovementActionFactory;

public class EnemyFactory {
	public Enemy createRedEnemy() {
		return new RedEnemy(0, 0);
	}

	public Enemy createBlueEnemy() {
		return new BlueEnemy(10, 10);
	}

	public Enemy createRedEnemy(int[] enemyInfo){
		return new RedEnemy(enemyInfo[0], enemyInfo[1]);
	}

	public Enemy createBlueEnemy(int[] enemyInfo) {
		return new BlueEnemy(10, 10);
	}
	
	public Enemy createRLRedEnemy(int[] enemyInfo){
		return new RedEnemy(enemyInfo[0], enemyInfo[1], new RLMovementActionFactory().createMovementAction());
	}
	
	public Enemy createLRRedEnemy(int[] enemyInfo){
		return new RedEnemy(enemyInfo[0], enemyInfo[1], new LRMovementActionFactory().createMovementAction());
	}
	
	public Enemy createRLBlueEnemy(int[] enemyInfo){
		return new BlueEnemy(enemyInfo[0], enemyInfo[1], new RLMovementActionFactory().createMovementAction());
	}
	
	public Enemy createLRBlueEnemy(int[] enemyInfo){
		return new BlueEnemy(enemyInfo[0], enemyInfo[1], new LRMovementActionFactory().createMovementAction());
	}
	
	public Enemy createSpecialEnemy(Class<? extends Enemy> enemyClass, Class<? extends MovementActionFactory> actionFactoryClass,int[] enemyInfo){
		Enemy enemy = null;
		MovementAction action = null;
		try {
			if(actionFactoryClass!=null)
				action = actionFactoryClass.newInstance().createMovementAction();
			enemy = enemyClass.getConstructor(int.class, int.class, MovementAction.class).newInstance(enemyInfo[0], enemyInfo[1], action);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return enemy;
	}
	
	public Enemy createSpecialEnemy2(Class<? extends Enemy> enemyClass, Class<? extends MovementActionFactory> actionFactoryClass,int[] enemyInfo, List<MovementActionInfo> infos){
		Enemy enemy = null;
		MovementAction action = null;
		try {
			if(actionFactoryClass!=null)
				action = actionFactoryClass.newInstance().createMovementAction(infos);
			enemy = enemyClass.getConstructor(int.class, int.class, MovementAction.class).newInstance(enemyInfo[0], enemyInfo[1], action);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return enemy;
	}
}
