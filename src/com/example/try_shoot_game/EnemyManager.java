package com.example.try_shoot_game;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.util.Log;

import com.example.try_gameengine.action.CircleController;
import com.example.try_gameengine.action.CopyMoveDecorator;
import com.example.try_gameengine.action.EaseInDecorator;
import com.example.try_gameengine.action.GravityController;
import com.example.try_gameengine.action.JumpController;
import com.example.try_gameengine.action.JumpMoveLimitCondition;
import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.action.MovementActionInfo;
import com.example.try_gameengine.action.MovementActionItem;
import com.example.try_gameengine.action.MovementActionItemCountDownTimer;
import com.example.try_gameengine.action.MovementActionItemMoveByCurve;
import com.example.try_gameengine.action.MovementActionItemMoveByGravity;
import com.example.try_gameengine.action.MovementActionSet;
import com.example.try_gameengine.action.MovementActionSetWithOutThread;
import com.example.try_gameengine.action.MovementActionSetWithThread;
import com.example.try_gameengine.action.MovementAtionController;
import com.example.try_gameengine.action.MovementInfoFactory;
import com.example.try_gameengine.action.SimultaneouslyMovementActionSet;
import com.example.try_gameengine.action.SpecialMovementActionFactory;
import com.example.try_gameengine.test.RLMovementActionFactory;

public class EnemyManager {
	private List<Enemy> enemies = new ArrayList<Enemy>(); 
	
	public void createEnemy(){
		EnemyFactory enemyFactory = new EnemyFactory();
		enemies.add(enemyFactory.createRedEnemy());
		enemies.add(enemyFactory.createBlueEnemy());
	}
	
	public void createLevel1Enemy(){
		EnemyFactory enemyFactory = new EnemyFactory();
		/*
		MovementAction action = new CopyMoveDecorator(new DoubleDecorator(new CopyMoveDecorator(new RLMovementActionFactory().createMovementAction()))); //*4((80,-80)*5)
		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{0, 600}, action));
		
		RLMovementActionFactory factory = new RLMovementActionFactory();
		MovementAction innerAction = factory.createMovementAction(); //*5 40,-40
		action = new MovementActionSet(); 
		action.addMovementAction(new DoubleDecorator(innerAction)); //*5 80,-20
		MovementAction actionD = new DoubleDecorator(action); //*5 160,-40
		actionD = new DoubleDecorator(new DoubleDecorator(actionD)); //*5 640,-160
		MovementAction actionDD = new DoubleDecorator(actionD); //*5(1280,-320)
		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{0, 630}, actionDD));
		*/
//		action = new MovementActionSet();
//		action = new CopyMoveDecorator(new CopyMoveDecorator(new RLMovementActionFactory().createMovementAction())); //*5*3 10,-10
//		MovementAction newaction2 = new MovementActionSet();
//		newaction2.addMovementAction(actionDD);
//		action = new CopyMoveDecorator(new DoubleDecorator(new CopyMoveDecorator(new RLMovementActionFactory().createMovementAction()))); //*4((80,-80)*5)
//		newaction2.addMovementAction(action);
//		newaction2 = new DoubleDecorator(new CopyMoveDecorator(newaction2));
//		
//		MovementAction newaction3 = new MovementActionSet();
//		newaction3.addMovementAction(newaction2);
//		newaction3.addMovementAction(new RLMovementActionFactory().createMovementAction());
//		newaction3.addMovementAction(new MovementActionSet().addMovementAction(new MovementActionSet().addMovementAction(new MovementActionSet().addMovementAction(new RLMovementActionFactory().createMovementAction()))));
//		newaction3 = new DoubleDecorator(new CopyMoveDecorator(newaction3));
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{0, 630}, actionDD));
				
//		enemies.add(enemyFactory.createSpecialEnemy(RedEnemy.class, RLMovementActionFactory.class, new int[]{0, 0}));
//		enemies.add(enemyFactory.createSpecialEnemy4(RedEnemy.class, RLMovementActionFactory.class, new int[]{0, 0}, MovementActionDecoratorFactory.createCopyMovementDecorator()));
//		enemies.add(enemyFactory.createRLRedEnemy(new int[]{50, 50}));
//		enemies.add(enemyFactory.createRLBlueEnemy(new int[]{100, 100}));
//		enemies.add(enemyFactory.createSpecialEnemy(BlueEnemy.class, RLMovementActionFactory.class, new int[]{150, 150}));
//		enemies.add(enemyFactory.createSpecialEnemy2(BlueEnemy.class, SpecialMovementActionFactory.class, new int[]{300, 300}, MovementInfoFactory.createSquareMovementInfo()));
//		enemies.add(enemyFactory.createSpecialEnemy3(BlueEnemy.class, SpecialMovementActionFactory.class, new int[]{450, 450}, MovementInfoFactory.createSquareMovementInfo(), MovementActionDecoratorFactory.createDHDMovementDecorator()));
//		enemies.add(enemyFactory.createSpecialEnemy4(RedEnemy.class, RLMovementActionFactory.class, new int[]{600, 600}, MovementActionDecoratorFactory.createDHDMovementDecorator()));
//		enemies.add(enemyFactory.createSpecialEnemy3(BlueEnemy.class, SpecialMovementActionFactory.class, new int[]{750, 750}, MovementInfoFactory.createSquareMovementInfo(), MovementActionDecoratorFactory.createDCMovementDecorator()));
//		enemies.add(enemyFactory.createSpecialEnemy3(BlueEnemy.class, SpecialMovementActionFactory.class, new int[]{750, 750}, MovementInfoFactory.createSquareMovementInfo(), MovementActionDecoratorFactory.createCDMovementDecorator()));
//		enemies.add(enemyFactory.createSpecialEnemy3(RedEnemy.class, SpecialMovementActionFactory.class, new int[]{750, 950}, MovementInfoFactory.createSquareMovementInfo(), MovementActionDecoratorFactory.createCCMovementDecorator()));
//		enemies.add(enemyFactory.createSpecialEnemy3(RedEnemy.class, SpecialMovementActionFactory.class, new int[]{750, 1050}, MovementInfoFactory.createSquareMovementInfo(), MovementActionDecoratorFactory.createCDCMovementDecorator()));
//	
//		enemies.add(enemyFactory.createSpecialEnemy(RedEnemy.class, RLMovementActionFactory.class, new int[]{0, 0}));
		
//		enemies.add(enemyFactory.createSpecialEnemy3(BlueEnemy.class, SpecialMovementActionFactory.class, new int[]{450, 450}, MovementInfoFactory.createSquareMovementInfo(), MovementActionDecoratorFactory.createDDMovementDecorator()));
		
//		enemies.add(enemyFactory.createSpecialEnemy4(RedEnemy.class, RLMovementActionFactory.class, new int[]{450, 450}, MovementActionDecoratorFactory.createCCMovementDecorator()));

		/*
		RLMovementActionFactory factory = new RLMovementActionFactory();
		MovementAction innerAction = factory.createMovementAction();
//		MovementAction action = new DoubleDecorator(new DoubleDecorator(new DoubleDecorator(innerAction)));
		MovementAction action = new MovementActionSet();
		action.addMovementAction(new DoubleDecorator(innerAction));
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{0, 1050}, action));
		MovementAction actionD = new DoubleDecorator(action);
//		MovementAction actionD = new DoubleDecorator(new DoubleDecorator(action));
		actionD = new DoubleDecorator(new DoubleDecorator(actionD));
//		MovementAction actionDD = new DoubleDecorator(new DoubleDecorator(actionD));
		MovementAction actionDD = new DoubleDecorator(actionD);
		MovementAction newaction = new MovementActionSet();
//		newaction.addMovementAction(new DoubleDecorator(actionDD));
//		
//		newaction = new CopyMoveDecorator(newaction);
		
//		newaction = new CopyMoveDecorator(new DoubleDecorator(new RLMovementActionFactory().createMovementAction()));
//		newaction = new DoubleDecorator(new DoubleDecorator(new CopyMoveDecorator(new RLMovementActionFactory().createMovementAction())));
//		newaction = new CopyMoveDecorator(new CopyMoveDecorator(new CopyMoveDecorator(new RLMovementActionFactory().createMovementAction())));
		newaction = new CopyMoveDecorator(new CopyMoveDecorator(new RLMovementActionFactory().createMovementAction()));
		MovementAction newaction2 = new MovementActionSet();
		newaction2.addMovementAction(newaction);
		newaction2.addMovementAction(actionDD);
//		MovementAction action2 = new MovementActionSet();
		newaction = new CopyMoveDecorator(new DoubleDecorator(new CopyMoveDecorator(new RLMovementActionFactory().createMovementAction())));
//		newaction2 = new MovementActionSet();
		newaction2.addMovementAction(newaction);
//		newaction2 = new CopyMoveDecorator(new CopyMoveDecorator(newaction2));
		newaction2 = new DoubleDecorator(new CopyMoveDecorator(newaction2));
		
		MovementAction newaction3 = new MovementActionSet();
		
		newaction3.addMovementAction(newaction2);
		newaction3.addMovementAction(new RLMovementActionFactory().createMovementAction());
		newaction3.addMovementAction(new MovementActionSet().addMovementAction(new MovementActionSet().addMovementAction(new MovementActionSet().addMovementAction(new RLMovementActionFactory().createMovementAction()))));
		newaction3 = new DoubleDecorator(new CopyMoveDecorator(newaction3));
		
		
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{0, 1100}, actionD));
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{0, 1100}, actionDD));
				enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{0, 500}, newaction));
//				enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{0, 1100}, newaction2));
//				enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{0, 1100}, newaction3));
		*/
		
//		SpecialMovementActionFactory actionFactory = new SpecialMovementActionFactory();
//		MovementAction RLDRL = actionFactory.createMovementActionByMerge(new RLMovementActionFactory().createMovementAction(), action);
//		RLDRL = actionFactory.createMovementActionByMerge(new RLMovementActionFactory().createMovementAction(), RLDRL);
//		RLDRL = new DoubleDecorator(RLDRL);
//		RLDRL.doInfo();
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{0, 1150}, RLDRL));
	
//		MovementAction newaction4 = new CopyMoveDecorator(new MovementActionSet().addMovementAction(new MovementActionItem(MovementInfoFactory.createSingleRMovementInfo())));
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{100, 500}, newaction4));
		
//		MovementAction newaction4 = new MovementActionSet();
//		newaction4.addMovementAction(new MovementActionItem(MovementInfoFactory.createSingleRMovementInfo()));
//		MovementAction newaction4 = new MovementActionItem(MovementInfoFactory.createSingleRMovementInfo());
//		newaction4 = new MovementActionSet().addMovementAction(newaction4.addMovementAction(new MovementActionItem(MovementInfoFactory.createSingleRMovementInfo())));
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{100, 500}, newaction4));
//		MovementAction newaction4 = new DoubleDecorator(new MovementActionItem(MovementInfoFactory.createSingleRMovementInfo()));
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{100, 500}, newaction4));
//		MovementAction newaction4 = new ReturnBackMoveDecorator(new MovementActionItem(MovementInfoFactory.createSingleRMovementInfo()));
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{100, 500}, newaction4));
//		MovementAction newaction4 = new InverseMovementInfoDecorator(new SpecialMovementActionFactory().createMovementAction(MovementInfoFactory.createSquareMovementInfo()));
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{100, 500}, newaction4));
//		MovementAction newaction4 = new InverseMoveOrderDecorator(new MovementActionSet());
//		newaction4.addMovementAction(new SpecialMovementActionFactory().createMovementAction(MovementInfoFactory.createSquareMovementInfo()));
//		newaction4.addMovementAction(new MovementActionItem(MovementInfoFactory.createSingleRMovementInfo()));
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{100, 500}, newaction4));
//		MovementAction newaction4 = new ReturnBackDecorator(new SpecialMovementActionFactory().createMovementAction(MovementInfoFactory.createSquareMovementInfo()));
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{100, 500}, newaction4));
//		MovementAction newaction4 = new ReturnBackDecorator(new MovementActionSet());
//		newaction4.addMovementAction(new SpecialMovementActionFactory().createMovementAction(MovementInfoFactory.createSquareMovementInfo()));
//		newaction4.addMovementAction(new MovementActionItem(MovementInfoFactory.createSingleRMovementInfo()));
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{100, 500}, newaction4));
//		MovementAction newaction4 = new InverseMovementInfoAppendDecorator(new MovementActionSet());
//		newaction4.addMovementAction(new SpecialMovementActionFactory().createMovementAction(MovementInfoFactory.createSquareMovementInfo()));
//		newaction4.addMovementAction(new MovementActionItem(MovementInfoFactory.createSingleRMovementInfo()));
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{100, 500}, newaction4));
//		MovementAction newaction4 = new CopyMoveDecorator(new DoubleDecorator(new AffterHalfPartDecorator(new InverseMovementInfoAppendDecorator(new MovementActionSet()))));
//		newaction4.addMovementAction(new SpecialMovementActionFactory().createMovementAction(MovementInfoFactory.createSquareMovementInfo()));
//		newaction4.addMovementAction(new MovementActionItem(MovementInfoFactory.createSingleRMovementInfo()));
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{100, 500}, newaction4));
//		MovementAction newaction4 = new DoubleDecorator(new CopyMoveDecorator(new DoubleDecorator(new AffterHalfPartDecorator(new InverseMovementInfoAppendDecorator(new MovementActionSet())))));
//		newaction4.addMovementAction(new SpecialMovementActionFactory().createMovementAction(MovementInfoFactory.createSquareMovementInfo()));
//		newaction4.addMovementAction(new MovementActionItem(MovementInfoFactory.createSingleRMovementInfo()));
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{300, 500}, newaction4));
//		MovementAction newaction4 = new DoubleDecorator(new PartOfAppendDecorator(new CopyMoveDecorator(new DoubleDecorator(new PartOfAppendDecorator(new InverseMovementInfoAppendDecorator(new MovementActionSet()))))));
//		newaction4.addMovementAction(new SpecialMovementActionFactory().createMovementAction(MovementInfoFactory.createSquareMovementInfo()));
//		newaction4.addMovementAction(new MovementActionItem(MovementInfoFactory.createSingleRMovementInfo()));
//		newaction4 = new DoubleDecorator(new MovementActionSet().addMovementAction(newaction4));
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{300, 500}, newaction4));
//		MovementAction newaction4 = new DoubleDecorator(new PartOfOrigizalDecorator(new CopyMoveDecorator(new CopyMoveDecorator(new DoubleDecorator(new PartOfAppendDecorator(new InverseMovementInfoAppendDecorator(new MovementActionSet())))))));
//		newaction4.addMovementAction(new SpecialMovementActionFactory().createMovementAction(MovementInfoFactory.createSquareMovementInfo()));
//		newaction4.addMovementAction(new MovementActionItem(MovementInfoFactory.createSingleRMovementInfo()));
//		newaction4 = new DoubleDecorator(new MovementActionSet().addMovementAction(newaction4));
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{800, 500}, newaction4));
//		MovementAction newaction4 = new DoubleDecorator(new PartOfOrigizalDecorator(new CopyMoveDecorator(new PartOfAppendDecorator(new CopyMoveDecorator(new DoubleDecorator(new PartOfAppendDecorator(new InverseMovementInfoAppendDecorator(new MovementActionSet()))))))));
//		newaction4.addMovementAction(new SpecialMovementActionFactory().createMovementAction(MovementInfoFactory.createSquareMovementInfo()));
//		newaction4.addMovementAction(new MovementActionItem(MovementInfoFactory.createSingleRMovementInfo()));
//		newaction4 = new DoubleDecorator(new MovementActionSet().addMovementAction(newaction4));
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{800, 500}, newaction4));
	
//		MovementAction newaction4 = new SimultaneouslyMovementActionSet();
//		newaction4.addMovementAction(new SpecialMovementActionFactory().createMovementAction(MovementInfoFactory.createSquareMovementInfo()));
//		newaction4.addMovementAction(new MovementActionItem(MovementInfoFactory.createSingleRMovementInfo()));
//		newaction4.setMovementActionController(new MovementAtionController());
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{100, 500}, newaction4));
		
//		MovementAction newaction4 = new SimultaneouslyMovementActionSet();
//		newaction4.addMovementAction(new MovementActionItem(MovementInfoFactory.createCurveSingleR12MovementInfo()));
//		newaction4.addMovementAction(new MovementActionItem(MovementInfoFactory.createSingleR12MovementInfo()));
//		newaction4.setMovementActionController(new MovementAtionController());
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{100, 500}, newaction4));
		
//		MovementAction newaction4 = new SimultaneouslyMovementActionSet();
//		newaction4.addMovementAction(new MovementActionItem(MovementInfoFactory.createCurveSingleR121MovementInfo()));
//		newaction4.addMovementAction(new MovementActionItem(MovementInfoFactory.createCurveSingleR122MovementInfo()));
//		newaction4.setMovementActionController(new MovementAtionController());
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{500, 800}, newaction4));
		
//		MovementAction newaction4 = new SimultaneouslyMovementActionSet();
//		newaction4.addMovementAction(new CopyMoveDecorator(new SpecialMovementActionFactory().createMovementAction(MovementInfoFactory.createSquare12MovementInfo())));
//		newaction4.addMovementAction(new SpecialMovementActionFactory().createMovementAction(MovementInfoFactory.createSingle4RMovementInfo()));
//		newaction4.setMovementActionController(new MovementAtionController());
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{0, 800}, newaction4));
		
//		MovementAction newaction4 = new CopyMoveDecorator(new MovementActionSet());
//		newaction4.addMovementAction(new MovementActionItem(MovementInfoFactory.createCurveSingleRMovementInfo()));
////		newaction4.setMovementActionController(new MovementAtionController());
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{100, 500}, newaction4));
		
//		MovementAction newaction4 = new CopyMoveDecorator(new MovementActionSetWithThread());
//		newaction4.addMovementAction(new MovementActionItemMoveByGravity(new MovementActionInfo(1000, 200, 30, 0, "R"), new JumpController(50, 200, 0), ""));
//		newaction4 = new MovementActionSetWithThread().addMovementAction(newaction4);
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{100, 500}, newaction4));
		
		
//		MovementAction newaction4 = new MovementActionSetWithThread();
//		newaction4.addMovementAction(new MovementActionItemMoveByGravity(new MovementActionInfo(1000, 200, 30, 0, "R"), new JumpController(50, 200, 0), ""));
//		newaction4 = new MovementActionSetWithThread().addMovementAction(newaction4);
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{100, 500}, newaction4));
		
		MovementAction newaction4 = new MovementActionSetWithThread();
		newaction4.addMovementAction(new MovementActionItemMoveByGravity(new MovementActionInfo(1000, 200, 30, 0, "R"), new GravityController(-30, 100), ""));
		newaction4 = new MovementActionSetWithThread().addMovementAction(newaction4);
		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{100, 500}, newaction4));
//		
//		MovementAction newaction4 = new GravityInverseAngelMovementInfoAppendDecorator(new MovementActionSet());
//		newaction4.addMovementAction(new MovementActionItemCountDownTimer(MovementInfoFactory.createRotation45GravitySingleRMovementInfo()));
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{100, 500}, newaction4));
//		MovementAction newaction4 = new GravityCyclePathMovementInfoAppendDecorator(new MovementActionSet());
//		newaction4.addMovementAction(new MovementActionItemCountDownTimer(MovementInfoFactory.createRotation45GravitySingleRMovementInfo()));
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{100, 500}, newaction4));
//		MovementAction newaction4 = new GravityInversePathMovementInfoAppendDecorator(new MovementActionSet());
//		newaction4.addMovementAction(new MovementActionItemCountDownTimer(MovementInfoFactory.createRotation45GravitySingleRMovementInfo()));
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{100, 500}, newaction4));
//		MovementAction newaction4 = new GravityWavePathMovementInfoAppendDecorator(new MovementActionSet());
//		newaction4.addMovementAction(new MovementActionItemCountDownTimer(MovementInfoFactory.createRotation45GravitySingleRMovementInfo()));
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{100, 500}, newaction4));
//		MovementAction newaction4 = new GravitySlopeWavePathMovementInfoAppendDecorator(new MovementActionSet());
//		newaction4.addMovementAction(new MovementActionItemCountDownTimer(MovementInfoFactory.createRotation45GravitySingleRMovementInfo()));
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{100, 500}, newaction4));
		
//		MovementAction newaction4 = new GravitySlopeWavePathMovementInfoAppendDecorator(new MovementActionSet());
//		newaction4.addMovementAction(new MovementActionFrameItem(MovementInfoFactory.createFrameRotation45GravitySingleRMovementInfo()));
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{100, 500}, newaction4));
		
////		MovementAction newaction4 = new SimultaneouslyMovementActionSet();
//		MovementAction newaction4 = new MovementActionSetWithOutThread();
////		newaction4.addMovementAction(new MovementActionItemCountDownTimer(MovementInfoFactory.createCircleMovementInfo()));
////		newaction4.addMovementAction(new MovementActionItemCountDownTimer(new MovementActionInfo(24000, 100, 10, 0, "R", new CircleController(-10, 400, 700, 500, 800))));
//		MovementActionInfo info = new MovementActionInfo(24000, 100, 10, 0, "R");
//		newaction4.addMovementAction(new MovementActionItemMoveByCurve(info,  new CircleController(-2400, 400, 700, 500, 800), ""));
////		newaction4.addMovementAction(new EaseInDecorator(new MovementActionItemMoveByCurve(info,  new CircleController(-2400, 400, 700, 500, 800), ""), 0.5f));
//		newaction4.setMovementActionController(new MovementAtionController());
//		newaction4 = new EaseInDecorator(newaction4, 0.5f);
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{500, 800}, newaction4));
		
//		MovementAction newaction4 = new SimultaneouslyMultiCircleMovementActionSet();
//		newaction4.addMovementAction(new MovementActionItemCountDownTimer(MovementInfoFactory.create3CircleMovementInfo()));
//		newaction4.addMovementAction(new MovementActionItemCountDownTimer(MovementInfoFactory.create3SubCircleMovementInfo()));
//		((SimultaneouslyMultiCircleMovementActionSet)newaction4).setMediator();
//		newaction4.setMovementActionController(new MovementAtionController());
//		Enemy enemy = enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{500, 800}, newaction4);
//		enemies.add(enemy);
//		
//		MovementAction newaction5 = new SimultaneouslyMultiCircleMovementActionSet();
//		newaction5.addMovementAction(new MovementActionItem(MovementInfoFactory.create32CircleMovementInfo()));
//		newaction5.addMovementAction(new MovementActionItem(MovementInfoFactory.create3Sub2CircleMovementInfo()));
//		((SimultaneouslyMultiCircleMovementActionSet)newaction5).setMediator();
//		newaction5.setMovementActionController(new MovementAtionController());
//		Enemy layer = enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{600, 900}, newaction5);
//		enemies.add((Enemy)layer);
//		
//		enemy.registerObserver(layer);
		
		
		
	}
	
	public void createLevel2Enemy(){
		EnemyFactory enemyFactory = new EnemyFactory();
		enemies.add(enemyFactory.createRLRedEnemy(new int[]{50, 50}));
		enemies.add(enemyFactory.createRLBlueEnemy(new int[]{100, 100}));
		enemies.add(enemyFactory.createSpecialEnemy(BlueEnemy.class, RLMovementActionFactory.class, new int[]{150, 150}));
		enemies.add(enemyFactory.createSpecialEnemy2(BlueEnemy.class, SpecialMovementActionFactory.class, new int[]{300, 300}, MovementInfoFactory.createSquareMovementInfo()));
	}
	
	public void createLevel3Enemy(){
		EnemyFactory enemyFactory = new EnemyFactory();
//		enemies.add(enemyFactory.createRLRedEnemy(new int[]{50, 50}));
//		MovementActionItemBaseFPS movementAction = new MovementActionItemBaseFPS(new long[]{100, 50, 100 , 50, 100, 50, 100 , 50}, 10, 0, "frame");
//		movementAction.setMovementActionController(new MovementAtionController());
//		movementAction.setActionListener(new DefaultActionListener());
//		movementAction.setNextFrameTrigger(new MovementActionItemBaseFPS.FrameTrigger() {
//			
//			@Override
//			public void trigger() {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		enemies.add(enemyFactory.createSpecialEnemy5(RedEnemy.class, new int[]{50, 50}, movementAction));
	}
	
	public List<Enemy> getEnemies(){
		return enemies;
	}
	
	public void drawEnemies(Canvas canvas){
		for(Enemy enemy : enemies){
//			enemy.draw(canvas);
			enemy.drawSelf(canvas, null);
		}
	}
	
	public void frameTriger(){
		for(Enemy enemy : enemies){
			enemy.frameTrig();
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
			enemy.getMovementAction().start();
		}
	}
	
	public void showEnemiesMovementDescriptioins(){
		for(Enemy enemy : enemies){
			String description = enemy.getMovementActionDescriptions();
			Log.e("description", description);
		}
	}
}
