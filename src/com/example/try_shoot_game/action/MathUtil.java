package com.example.try_shoot_game.action;

import android.graphics.Point;
import android.graphics.Rect;

public class MathUtil {
//	private float imageX = -50.0f, imageY = -50.0f;
	private float fAngle;
	private float speedY = -15;
	private float speedX = -15;
	float BALL_INIT_SPEEDX = 50;
	
	public MathUtil() {

	}
	
	public MathUtil(float speedX, float speedY) {
		this.speedX = speedX;
		this.speedY = speedY;
//		BALL_INIT_SPEEDX = 10;
//		BALL_INIT_SPEEDX;
	}
	
	public void setXY(float speedX, float speedY){
		this.speedX = speedX;
		this.speedY = speedY;
	}
	
	private CollisionLoc isCollisionDetected(Rect rect, float fX, float fY,
			float fR) {
		// 站在圓心的立場，計算與矩形邊之間的最短 x 距離(不含四個角落點)，且跼限於 rect 的 top 與 bottom 之間
		float dx1 = Math.abs(fX - rect.left);
		float dx2 = Math.abs(fX - rect.right);
		float dx = dx1 < dx2 ? dx1 : dx2;

		if (fY >= rect.top && fY <= rect.bottom && dx <= fR) {
			if (dx1 < dx2)
				return CollisionLoc.Left;
			else
				return CollisionLoc.Right;
		}
		// 站在圓心的立場，計算與矩形邊之間的最短 y 距離(不含四個角落點)，且跼限於 rect 的 left 與 right 之間
		float dy1 = Math.abs(fY - rect.top);
		float dy2 = Math.abs(fY - rect.bottom);
		float dy = dy1 < dy2 ? dy1 : dy2;
		if (fX >= rect.left && fX <= rect.right && dy <= fR) {
			if (dy1 < dy2)
				return CollisionLoc.Top;
			else
				return CollisionLoc.Bottom;
		}
		// 計算四個角落點是否落在圓內
		Point[] pts = { new Point(rect.left, rect.top),
				new Point(rect.right, rect.top),
				new Point(rect.left, rect.bottom),
				new Point(rect.right, rect.bottom) };
		for (int i = 0; i < pts.length; i++)
			if ((pts[i].x - fX) * (pts[i].x - fX) + (pts[i].y - fY)
					* (pts[i].y - fY) <= fR * fR) {
				if (i == 0)
					return CollisionLoc.CornerLT;
				else if (i == 1)
					return CollisionLoc.CornerRT;
				else if (i == 2)
					return CollisionLoc.CornerLB;
				else
					return CollisionLoc.CornerRB;
			}
		return CollisionLoc.None;
	}

	public void initAngle() {
		// fAngle = Math.round(360 * Math.random());
		// setTitle(fAngle+"度");
		fAngle = 90;
	}

	public void genSpeed() {
		this.speedX = (float) Math.cos(Math.toRadians(this.fAngle))
				* BALL_INIT_SPEEDX;
		this.speedY = (float) Math.sin(Math.toRadians(this.fAngle))
				* BALL_INIT_SPEEDX * (-1);
	}
	
	public void genSpeedByRotate(float rotation) {
		this.fAngle += rotation;
		this.speedX = (float) Math.cos(Math.toRadians(this.fAngle))
				* BALL_INIT_SPEEDX;
		this.speedY = (float) Math.sin(Math.toRadians(this.fAngle))
				* BALL_INIT_SPEEDX * (-1) ;
	}
	
	public float getSpeedX() {
		return speedX;
	}

	public float getSpeedY() {
		return speedY;
	}

	public float getSpeedX(float fAngle) {
		return (float) Math.cos(Math.toRadians(fAngle)) * BALL_INIT_SPEEDX;
	}

	public float getSpeedY(float fAngle) {
		return (float) Math.sin(Math.toRadians(fAngle)) * BALL_INIT_SPEEDX
				* (-1);
	}

	public float getSpeedXBySpeedY(float fAngle) {
		return (float) Math.cos(Math.toRadians(fAngle)) * BALL_INIT_SPEEDX;
	}

	public float getSpeedYBySpeedX(float speedX) {
		double speedTotalPOW = (float) (Math.pow(this.speedX, 2) + Math.pow(
				this.speedY, 2));
		float speedTotal = (float) Math.sqrt(speedTotalPOW);
		double newSpeedYPOW = Math.pow(speedTotal * 3, 2) - Math.pow(speedX, 2);
		float newSpeedY = (float) Math.sqrt(newSpeedYPOW);
		return newSpeedY / 3;
	}

	public void getNewSpeedAfterHitCoener(float newAngleAfterHitCoener) {
		this.speedX = (float) Math.cos(Math.toRadians(newAngleAfterHitCoener))
				* (BALL_INIT_SPEEDX);
		this.speedY = (float) Math.sin(Math.toRadians(newAngleAfterHitCoener))
				* (BALL_INIT_SPEEDX) * (-1);
	}

	public void genAngle() {
		this.fAngle = (float) ((Math.atan2(this.speedY, (-1) * this.speedX) + Math.PI)
				/ Math.PI * 180);
		// setTitle(fAngle+"度");
	}

	public float getHitCornerAngle(int cornerX, int cornerY, float ballCenterX,
			float ballCenterY) {
		// this.fAngle = (float) ((Math.atan2(this.speedY, (-1) * this.speedX) +
		// Math.PI)
		// / Math.PI * 180);
		// float hitCornerAngle = ((float)(Math.atan((-1)*(cornerY-ballCenterY)
		// / (cornerX-ballCenterX))/Math.PI *180));
		float hitCornerAngle = ((float) ((Math.atan2((-1)
				* (cornerY - ballCenterY), (cornerX - ballCenterX)))
				/ Math.PI * 180));
		if (hitCornerAngle < 0) {
			hitCornerAngle = 360 + hitCornerAngle;
		}

		// float x =
		// (float)(Math.atan(((-1)*(cornerY-ballCenterY)/(cornerX-ballCenterX))));
		// float hitCornerAngle = (float)
		// (((float)((Math.atan2((-1)*(cornerY-ballCenterY),
		// (cornerX-ballCenterX)))))*(180/Math.PI));
		return hitCornerAngle;
	}

	public float getStartAngle(int startTriangleX, int startTriangleY,
			float ballCenterX, float ballCenterY) {
		// this.fAngle = (float) ((Math.atan2(this.speedY, (-1) * this.speedX) +
		// Math.PI)
		// / Math.PI * 180);
		// float hitCornerAngle = ((float)(Math.atan((-1)*(cornerY-ballCenterY)
		// / (cornerX-ballCenterX))/Math.PI *180));
		float startAngle = ((float) ((Math.atan2((-1)
				* (startTriangleY - ballCenterY),
				(startTriangleX - ballCenterX)))
				/ Math.PI * 180));
		if (startAngle < 0) {
			startAngle = 360 + startAngle;
		}

		// float x =
		// (float)(Math.atan(((-1)*(cornerY-ballCenterY)/(cornerX-ballCenterX))));
		// float hitCornerAngle = (float)
		// (((float)((Math.atan2((-1)*(cornerY-ballCenterY),
		// (cornerX-ballCenterX)))))*(180/Math.PI));
		return startAngle;
	}

	public float getNewAngleAfterHitCoener(float hitCornerAngle) {
		float newAngleAfterHitCoener = 0;
		if (this.fAngle - hitCornerAngle <= 45) {
			newAngleAfterHitCoener = this.fAngle - 180
					- (this.fAngle - hitCornerAngle);
		} else {
			newAngleAfterHitCoener = this.fAngle
					+ (this.fAngle - hitCornerAngle);
		}
		return newAngleAfterHitCoener;
	}

	public float getNewAngleTowardsPoint(int targetX, int targetY,
			float ballCenterX, float ballCenterY) {
		float hitCornerAngle = ((float) ((Math.atan2((-1)
				* (targetY - ballCenterY), (targetX - ballCenterX)))
				/ Math.PI * 180));
		if (hitCornerAngle < 0) {
			hitCornerAngle = 360 + hitCornerAngle;
		}
		return hitCornerAngle;
	}

	public enum CollisionLoc { // 碰撞情形以 enum 宣告
		None, Left, Top, Right, Bottom, CornerLT, CornerRT, CornerLB, CornerRB
	}

	private CollisionLoc hitBoardCheck(Rect rect, float fX, float fY, float fR) {
		// 站在圓心的立場，計算與矩形邊之間的最短 x 距離(不含四個角落點)，且跼限於 rect 的 top 與 bottom 之間
		float dx1 = Math.abs(fX - rect.left);
		float dx2 = Math.abs(fX - rect.right);
		float dx = dx1 < dx2 ? dx1 : dx2;

		if (fY >= rect.top && fY <= rect.bottom && dx <= fR) {
			if (dx1 < dx2)
				return CollisionLoc.Left;
			else
				return CollisionLoc.Right;
		}
		// 站在圓心的立場，計算與矩形邊之間的最短 y 距離(不含四個角落點)，且跼限於 rect 的 left 與 right 之間
		float dy1 = Math.abs(fY - rect.top);
		float dy2 = Math.abs(fY - rect.bottom);
		float dy = dy1 < dy2 ? dy1 : dy2;
		if (fX >= rect.left && fX <= rect.right && dy <= fR) {
			if (dy1 < dy2)
				return CollisionLoc.Top;
			else
				return CollisionLoc.Bottom;
		}
		// 計算四個角落點是否落在圓內
		Point[] pts = { new Point(rect.left, rect.top),
				new Point(rect.right, rect.top),
				new Point(rect.left, rect.bottom),
				new Point(rect.right, rect.bottom) };
		for (int i = 0; i < pts.length; i++)
			if ((pts[i].x - fX) * (pts[i].x - fX) + (pts[i].y - fY)
					* (pts[i].y - fY) <= fR * fR) {
				if (i == 0)
					return CollisionLoc.CornerLT;
				else if (i == 1)
					return CollisionLoc.CornerRT;
				else if (i == 2)
					return CollisionLoc.CornerLB;
				else
					return CollisionLoc.CornerRB;
			}
		return CollisionLoc.None;
	}
	
	float vx ;
	float vy ;
	float deltaTime = 1.0f;
	float ay = 9.8f;
	float ax = 0;
	
	public void initGravity(){
//		speedX = 30;
		vx = (float) (Math.abs(this.speedX) * Math.cos(this.fAngle*(Math.PI/180.0)));
		vy = (float) (Math.abs(this.speedX) * Math.sin(this.fAngle*(Math.PI/180.0)))*-1;
	}
	
	public void initGravity(float fAngle){
//		this.speed = 60.0;
//		this.deltaTime = 0.0001;
		
//		this.endTime = 10.0;
//		this.x = 1.0;
//		this.y = 10;
		
//		this.angle = 45;
		this.fAngle = fAngle;
		vx = (float) (this.speedX * Math.cos(this.fAngle*(Math.PI/180.0)));
		vy = (float) (this.speedX * Math.sin(this.fAngle*(Math.PI/180.0))) * -1;

		double time = 0.0;
//		while (time < this.endTime){
//		    time += this.deltaTime;
//		speedX += vx*deltaTime;
//		speedY += vy*deltaTime;
//
//		    vx += ax*deltaTime;
//		    vy += ay*deltaTime;
//		}
	}
	
	public void genGravity(){
		speedX = vx*deltaTime;
		speedY = vy*deltaTime;

		    vx += ax*deltaTime;
		    vy += ay*deltaTime;
	}
	
	public float getAngle(){
		return fAngle;
	}
	
	public void setAngle(float fAngle){
		this.fAngle = fAngle;
	}
	
	public void inverseAngel(){
//		speedX = -speedX;
		float ovy = vy;
		genAngle();
//		fAngle %= 360;
		if(fAngle >= 0 && fAngle<90){
			fAngle = 0 - fAngle + 180; 
		}else if(fAngle >= 90 && fAngle<180){
			fAngle = 180 - fAngle + 0;
		}else if(fAngle >= 180 && fAngle<270){
			fAngle = (180 - fAngle) + 360;
		}else if(fAngle >= 270 && fAngle<360){
			fAngle = 360 - fAngle + 180;
		}
		
//		speedX = -speedX;
//		speedY = -speedY;
		ay = - ay;	
		vy = ovy;
	}
	
	public void cyclePath(){
//		speedX = -speedX;
//		genAngle();
//		fAngle %= 360;
//		if(fAngle >= 0 && fAngle<90){
//			fAngle = 0 - fAngle + 180; 
//		}else if(fAngle >= 90 && fAngle<180){
//			fAngle = 180 - fAngle + 0;
//		}else if(fAngle >= 180 && fAngle<270){
//			fAngle = (180 - fAngle) + 360;
//		}else if(fAngle >= 270 && fAngle<360){
//			fAngle = 360 - fAngle + 180;
//		}
		
//		speedX = -speedX;
//		speedY = -speedY;
		ay = - ay;	
		
	}
	
	public void inversePath(){
//		speedX = -speedX;
		float ovy = vy;
		genAngle();
		fAngle += 180;
		
//		speedX = -speedX;
//		speedY = -speedY;
//		ay = - ay;	
		vy = ovy;
	}
	
	public void wavePath(){
		float ovy = vy;
//		genAngle();
//		fAngle %= 360;
		if(fAngle >= 0 && fAngle<90){
			fAngle = 0 - fAngle + 360; 
		}else if(fAngle >= 90 && fAngle<180){
			fAngle = 360 - fAngle + 0;
		}else if(fAngle >= 180 && fAngle<270){
			fAngle = (180 - fAngle) + 180;
		}else if(fAngle >= 270 && fAngle<360){
			fAngle = 360 - fAngle + 0;
		}
		
//		speedX = -speedX;
//		speedY = -speedY;
		ay = - ay;	
		vy = ovy;
	}
	
	public void slopeWavePath(){
//		speedX = -speedX;
		float ovy = vy;
		genAngle();
		
//		speedX = -speedX;
//		speedY = -speedY;
		ay = - ay;	
		vy = ovy;
	}

	public void reset(){
		ay = 9.8f;
	}
}
