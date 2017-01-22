package com.example.try_shoot_game.action;

public class MovementActionInfo {
	private long total;
	private long delay;
	private int dx, dy;
	private String description;
	
	public MovementActionInfo(long total, long delay, int dx, int dy){
		this(total, delay, dx, dy, null);
	}
	
	public MovementActionInfo(long total, long delay, int dx, int dy, String description){
		this.total = total;
		this.delay = delay;
		this.dx = dx;
		this.dy = dy;
		this.description = description;
	}
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public long getDelay() {
		return delay;
	}
	public void setDelay(long delay) {
		this.delay = delay;
	}
	public int getDx() {
		return dx;
	}
	public void setDx(int dx) {
		this.dx = dx;
	}
	public int getDy() {
		return dy;
	}
	public void setDy(int dy) {
		this.dy = dy;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
