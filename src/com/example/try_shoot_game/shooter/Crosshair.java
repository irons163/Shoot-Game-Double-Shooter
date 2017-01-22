package com.example.try_shoot_game.shooter;

import com.example.try_shoot_game.BitmapUtil;
import com.example.try_shoot_game.CommonUtil;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Crosshair {
	private Bitmap bitmap;
	private float x = CommonUtil.screenWidth/2;
	private float y = CommonUtil.screenHeight/2;
	
	public Crosshair(){
		this.bitmap = BitmapUtil.greenPoint;
	}
	
	public void draw(Canvas canvas){
		canvas.drawBitmap(bitmap, x, y, null);
	}
	
	public void move(float dx, float dy){
		x += dx;
		y += dy;
	}
	
}
