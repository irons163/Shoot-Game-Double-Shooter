package com.example.try_gameengine.framework;

import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.Settings.Global;

public class Utils {

	/**
	 * * ?�断两个?�形?�否?�交?�方�?* *
	 * 
	 * @param x
	 *            * @param y * @param w * @param h * @param x2 * @param y2 * @param
	 *            w2 * @param h2 * @return
	 */
	public static boolean colliseWidth(float x, float y, float w, float h,
			float x2, float y2, float w2, float h2) {
		if (x > x2 + w2 || x2 > x + w || y > y2 + h2 || y2 > y + h) {
			return false;
		}
		return true;
	}

	/**
	 * * ?�断 �?��?�是?�在?�形?��??��? * * @param x * @param y * @param w * @param h * @param
	 * px * @param py * @return
	 */
	public static boolean inRect(float x, float y, float w, float h, float px,
			float py) {
		if (px > x && px < x + w && py > y && py < y + h) {
			return true;
		}
		return false;
	}
}