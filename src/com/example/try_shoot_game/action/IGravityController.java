package com.example.try_shoot_game.action;

public interface IGravityController {
	public void execute(MovementActionInfo info);
	public void reset(MovementActionInfo info);
	public void isInverseAngel();
	public void isCyclePath();
	public void isInversePath();
	public void isWavePath();
	public void isSlopeWavePath();
	public MathUtil getMathUtil();
	public void setMathUtil(MathUtil mathUtil);
}