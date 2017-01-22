package com.example.try_shoot_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class BitmapUtil {
	
	static Context context;
	
	public static Bitmap redPoint;
	public static Bitmap greenPoint;
	public static Bitmap blackPoint;
	public static Bitmap whitePoint;
	public static Bitmap bluePoint;
	
	public static void initBitmap(Context context){
		BitmapUtil.context = context;
		initBitmap();
	}
	
	private static void initBitmap(){
		redPoint = BitmapFactory.decodeResource(context.getResources(), R.drawable.red_point, null);
		greenPoint = BitmapFactory.decodeResource(context.getResources(), 
				R.drawable.green_point);
		blackPoint = BitmapFactory.decodeResource(context.getResources(), 
				R.drawable.black_point);
		whitePoint = BitmapFactory.decodeResource(context.getResources(), 
				R.drawable.white_point);
		bluePoint = createSpecificSizeBitmap(context.getResources().getDrawable(R.drawable.blue_point), 200, 200);
	}
	
	// ��l�Ʀn������I
	public static Bitmap createSpecificSizeBitmap(Drawable drawable, int width, int height) {
		// �s�ؤ@��bitmap�A���e20�A�ϥ�ARGB_8888�]�w�A��bitmap�{�b�ť�bitmap���Dnull�C
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap); // �s�صe���A�Ϊť�bitmap��e��
		drawable.setBounds(0, 0, width, height);// �]�wdrawable�����(��Ϥ����ۤv�����e)
		drawable.draw(canvas); // �b�e���W�e�W��drawable(����bitmap�w�g�Q�e�W�F��A���O�ťդF)
		return bitmap;
	}
	
}
