package com.example.try_shoot_game.action;

import com.example.try_shoot_game.BitmapUtil;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;

public class Circle33Controller implements ICircleController{
	float rotation;
	float origineDx;
	float origineDy;
	boolean firstExecute = true;
	float initspeedX;
	private float x, y ,mx, my;
	private MathUtil mathUtil;
	ICircleController rotationController;
	float angle;
	Mediator mediator;
	
	public Circle33Controller(float rotation, float x, float y, float mx, float my) {
		this.rotation = rotation;
		this.x = x;
		this.y =y;
		this.mx = mx;
		this.my = my;
		mathUtil = new MathUtil(mx-x, my-y);
		initspeedX = (float) Math.sqrt((mx-x)*(mx-x) + (my-y)*(my-y));
		mathUtil.setINITSPEEDX(initspeedX);
//		this.mediator = mediator;
		mathUtil.genAngle();
	}
	
	public Circle33Controller(float rotation, float x, float y, float mx, float my, ICircleController rotationController) {
		this.rotation = rotation;
		this.x = x;
		this.y =y;
		this.mx = mx;
		this.my = my;
		mathUtil = new MathUtil(mx-x, my-y);
		initspeedX = (float) Math.sqrt((mx-x)*(mx-x) + (my-y)*(my-y));
		mathUtil.setINITSPEEDX(initspeedX);
		mathUtil.genAngle();
		this.rotationController = rotationController;
	}
	
	public void setCircleController(ICircleController rotationController){
		this.rotationController = rotationController;
	}
	
	public void setMediator(Mediator mediator){
		this.mediator = mediator;
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
				
				angle = -10;
//				if(rotationController!=null){
//					synchronized (rotationController) {
						rotationController.setX(mx);
						rotationController.setY(my);
						rotationController.setAngle(angle);
						float oldmx = rotationController.getmX();
						float oldmxy = rotationController.getmY();
						rotationController.genSpeed();
						
//						rotationController.setmX(oldmx+speedx);
//						rotationController.setmY(oldmxy+speedy);
						
//						info.setDx(rotationController.getmX() - oldmx);
//						info.setDy(rotationController.getmY() - oldmxy);
						
						info.setDx(rotationController.getmX() - oldmx);
						Log.e("mx", rotationController.getmX() - oldmx+"");
						info.setDy(rotationController.getmY() - oldmxy);
						Log.e("my", rotationController.getmY() - oldmxy+"");
//					}
			}
		}else{
			synchronized (Circle33Controller.this) {
//			mathUtil.setXY(mx-x, my-y);
			mathUtil.genAngle();
	//		float f = mathUtil.getAngle();
	//		mathUtil.setAngle(f+10);
			mathUtil.genSpeedByRotate(rotation);
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
			
//			info.setDx(speedx);
//			info.setDy(speedy);	
			
//			info.setDx(dx);
//			info.setDy(dy);	
			
//			info.setDx(rotationController.mx - oldmx);
//			info.setDy(rotationController.my - oldmxy);
			
			angle = rotation;
//			rotationController.setX(mx);
//			rotationController.setY(my);
//			rotationController.setAngle(angle);
//			float oldmx = rotationController.getmX();
//			float oldmxy = rotationController.getmY();
//			rotationController.genSpeed();
			
			PointF oldPointF;
			oldPointF = mediator.getLastPointF(this);
			PointF pointF;
			pointF = mediator.noty(this, mx, my, angle);
			
			if(pointF==null){
				info.setDx(speedx);
				Log.e("mx1", speedx+"");
				info.setDy(speedy);
				Log.e("my1", speedy+"");
			}else{

				info.setDx(pointF.x - oldPointF.x);
				Log.e("mx2", pointF.x - oldPointF.x+"");
				info.setDy(pointF.y - oldPointF.y);
				Log.e("my2", pointF.y - oldPointF.y+"");
			}

			
			}
		}
//		Log.e("x", speedx+"");
//		Log.e("y", speedy+"");
		


//			rotationController.execute(info);
//		}
	}
	
	float dx, dy;
	@Override
	public PointF action(float mx, float my, float angle){
		synchronized (this) {
//			x = mx;
//			y = my;
//			float oldmx = this.mx;
//			float oldmy = this.my;
			
			float oldx = x;
			float oldy = y;
			x = mx;
			y = my;
//			this.mx = this.mx + (x - oldx);
//			this.my = this.my + (y - oldy);

			setAngle(angle);
			float oldmx = this.mx;
			float oldmxy = this.my;
			genSpeed();
			
			return new PointF(this.mx, this.my);
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
		return new Circle33Controller(rotation, x, y ,mx, my);
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

	@Override
	public float getmX() {
		// TODO Auto-generated method stub
		return mx;
	}

	@Override
	public float getmY() {
		// TODO Auto-generated method stub
		return my;
	}
}
