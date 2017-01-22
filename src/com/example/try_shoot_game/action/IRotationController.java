package com.example.try_shoot_game.action;

public interface IRotationController {

	public float getRotation();
	public void setRotation(float rotation);
	public void execute(MovementActionInfo info);
	public void reset(MovementActionInfo info);
	public IRotationController copyNewRotationController();
}
