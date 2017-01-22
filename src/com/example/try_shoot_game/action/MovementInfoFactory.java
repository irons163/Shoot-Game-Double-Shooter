package com.example.try_shoot_game.action;

import java.util.ArrayList;
import java.util.List;

public class MovementInfoFactory {
	private static List<MovementActionInfo> infos = new ArrayList<MovementActionInfo>();
	
	public static List<MovementActionInfo> createSquareMovementInfo(){
		infos.clear();
		MovementActionInfo right = new MovementActionInfo(1000, 200, 10, 0, "R");
		infos.add(right);
		MovementActionInfo down = new MovementActionInfo(1000, 200, 0, 10, "D");
		infos.add(down);
		MovementActionInfo left = new MovementActionInfo(1000, 200, -10, 0, "L");
		infos.add(left);
		MovementActionInfo up = new MovementActionInfo(1000, 200, 0, -10, "U");
		infos.add(up);
		
		return infos;
	}
	
	public static MovementActionInfo createSingleRMovementInfo(){
		MovementActionInfo right = new MovementActionInfo(1000, 200, 10, 0, "R");
		return right;
	}
	
	public static MovementActionInfo createCurveSingleRMovementInfo(){
		MovementActionInfo right = new MovementActionInfo(1000, 200, 10, 0, "R", new RotationCurveController(30));
		return right;
	}
	
	public static MovementActionInfo createGravitySingleRMovementInfo(){
		MovementActionInfo right = new MovementActionInfo(1000, 200, 10, 0, "R", true);
		return right;
	}
	
	public static MovementActionInfo createRotation45GravitySingleRMovementInfo(){
		MovementActionInfo right = new MovementActionInfo(1000, 200, 30, 0, "R", new RotationOnceController(45), true);
		return right;
	}
}
