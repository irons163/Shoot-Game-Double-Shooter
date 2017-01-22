package com.example.try_shoot_game.action;

import com.example.try_shoot_game.BitmapUtil;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Circle2Controller implements IRotationController{
	float rotation;
	float origineDx;
	float origineDy;
	boolean firstExecute = true;
	float initspeedX;
	private float x, y ,mx, my;
	private MathUtil mathUtil;
	Circle2Controller rotationController;
	float angle;
	
	public Circle2Controller(float rotation, float x, float y, float mx, float my) {
		this.rotation = rotation;
		this.x = x;
		this.y =y;
		this.mx = mx;
		this.my = my;
		mathUtil = new MathUtil(mx-x, my-y);
		initspeedX = (float) Math.sqrt((mx-x)*(mx-x) + (my-y)*(my-y));
		mathUtil.setINITSPEEDX(initspeedX);
	}
	
	public Circle2Controller(float rotation, float x, float y, float mx, float my, Circle2Controller rotationController) {
		this.rotation = rotation;
		this.x = x;
		this.y =y;
		this.mx = mx;
		this.my = my;
		mathUtil = new MathUtil(mx-x, my-y);
		initspeedX = (float) Math.sqrt((mx-x)*(mx-x) + (my-y)*(my-y));
		mathUtil.setINITSPEEDX(initspeedX);
		this.rotationController = rotationController;
	}
	
	public void setCircleController(Circle2Controller rotationController){
		this.rotationController = rotationController;
	}
	
	@Override
	public void execute(MovementActionInfo info) {
		// TODO Auto-generated method stub
		if(firstExecute){
			long millisTotal = info.getTotal();
			long millisDelay = info.getDelay();
			origineDx = info.getDx();
			origineDy = info.getDy();
			
			float x = millisDelay / millisTotal;
			
			float tx = origineDx * x;
			float ty = origineDy * x;
			
			
			
			firstExecute = false;
		}
		
//		float dx = info.getDx();
//		float dy = info.getDy();
//		MathUtil mathUtil = new MathUtil(dx, dy);
//		mathUtil.setINITSPEEDX(initspeedX);
//		mathUtil.genAngle();
//		mathUtil.genSpeedByRotate(rotation);
//		dx = mathUtil.getSpeedX();
//		dy = mathUtil.getSpeedY();
//		
//		info.setDx(dx);
//		info.setDy(dy);
		
//		mathUtil.getStartAngle((int)mx, (int)my, x, y);
		if(rotationController!=null){
			synchronized (rotationController) {
//				mathUtil.setXY(mx-x, my-y);
				mathUtil.genAngle();
		//		float f = mathUtil.getAngle();
		//		mathUtil.setAngle(f+10);
				mathUtil.genSpeedByRotate(-10);
				float speedx = mathUtil.getSpeedX();
				float speedy = mathUtil.getSpeedY();
				float newMx = x+speedx;
				float newMy = y+speedy;
				speedx = newMx - mx;
				speedy = newMy - my;
				mx = newMx;
				my = newMy;
		//		info.setTotal(info.getTotal());
		//		info.setDelay(info.getDelay());
				
//				info.setDx(0);
//				info.setDy(0);
				info.setDx(speedx);
				info.setDy(speedy);	
				
				angle = -10;
			}
		}else{
//			mathUtil.setXY(mx-x, my-y);
			mathUtil.genAngle();
	//		float f = mathUtil.getAngle();
	//		mathUtil.setAngle(f+10);
			mathUtil.genSpeedByRotate(-10);
			float speedx = mathUtil.getSpeedX();
			float speedy = mathUtil.getSpeedY();
			float newMx = x+speedx;
			float newMy = y+speedy;
			speedx = newMx - mx;
			speedy = newMy - my;
			mx = newMx;
			my = newMy;
	//		info.setTotal(info.getTotal());
	//		info.setDelay(info.getDelay());
			info.setDx(speedx);
			info.setDy(speedy);		
			
		}
//		Log.e("x", speedx+"");
//		Log.e("y", speedy+"");
		
		if(rotationController!=null){
			synchronized (rotationController) {
				rotationController.setX(mx);
				rotationController.setY(my);
//				rotationController.setAngle(angle);
				float oldmx = rotationController.mx;
				float oldmxy = rotationController.my;
//				rotationController.genSpeed();
				
				rotationController.mx = oldmx+info.getDx();
				rotationController.my = oldmxy+info.getDy();
				
//				info.setDx(rotationController.mx - oldmx);
//				Log.e("mx", rotationController.mx - oldmx+"");
//				info.setDy(rotationController.my - oldmxy);
//				Log.e("my", rotationController.my - oldmxy+"");
			}

//			rotationController.execute(info);
		}
	}

	@Override
	public void reset(MovementActionInfo info) {
		// TODO Auto-generated method stub
		info.setDx(origineDx);
		info.setDy(origineDy);
		firstExecute = true;
	}

	@Override
	public void setRotation(float rotation) {
		// TODO Auto-generated method stub
		this.rotation = rotation;
	}

	@Override
	public float getRotation() {
		// TODO Auto-generated method stub
		return rotation;
	}

	@Override
	public IRotationController copyNewRotationController() {
		// TODO Auto-generated method stub
		return new Circle2Controller(rotation, x, y ,mx, my);
	}
	
	public float getX(){
		return mx;
	}
	
	public float getY(){
		return my;
	}

	public void setX(float mx){
		this.x = mx;
	}
	
	public void setY(float my){
		this.y = my;
	}
	
	public void setAngle(float angle){
//		mathUtil.setAngle(angle);
		mathUtil.setAngle(mathUtil.getAngle()+angle);
	}
	
	public void genSpeed(){
		mathUtil.genSpeed();
		mx = x + mathUtil.getSpeedX();
		my = y + mathUtil.getSpeedY();
	}
	
	public void setmX(float mx){
		this.mx = mx;
	}
	
	public void setmY(float my){
		this.my = my;
	}
	
	public void draw(Canvas canvas){
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(20);
		paint.setStrokeWidth(20);
		canvas.drawPoint(x+BitmapUtil.redPoint.getWidth()/2, y+BitmapUtil.redPoint.getHeight()/2, paint);
		canvas.drawPoint(mx+BitmapUtil.redPoint.getWidth()/2, my+BitmapUtil.redPoint.getHeight()/2, paint);
	}
}
