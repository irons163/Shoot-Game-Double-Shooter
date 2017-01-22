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
	
	// 初始化好紅綠兩點
	public static Bitmap createSpecificSizeBitmap(Drawable drawable, int width, int height) {
		// 新建一個bitmap，長寬20，使用ARGB_8888設定，此bitmap現在空白bitmap但非null。
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap); // 新建畫布，用空白bitmap當畫布
		drawable.setBounds(0, 0, width, height);// 設定drawable的邊界(原圖片有自己的長寬)
		drawable.draw(canvas); // 在畫布上畫上此drawable(此時bitmap已經被畫上東西，不是空白了)
		return bitmap;
	}
	
}
