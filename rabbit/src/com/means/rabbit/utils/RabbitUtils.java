package com.means.rabbit.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.content.Intent;

import com.means.rabbit.activity.merchants.GoodDetailActivity;
import com.means.rabbit.activity.merchants.HotelDetailActivity;
import com.means.rabbit.activity.merchants.ShopDetailActivity;
import com.means.rabbit.activity.merchants.TuangouDetailActivity;
import com.means.rabbit.activity.travel.TravelDetailActivity;

public class RabbitUtils {
	// 是否包含字母
	public static boolean isLetter(String str) {
		for (int i = 0; i < str.length(); i++) { // 循环遍历字符串
			if (Character.isLetter(str.charAt(i))) { // 用char包装类中的判断字母的方法判断每一个字符
				return true;
			}
		}
		return false;
	}

	public static int daysBetween(String smdate, String bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	public static void ImgIntent(Context context, int type, String id) {

		Intent it = null;

		switch (type) {
		case 1:
			it = new Intent(context, TravelDetailActivity.class);
			it.putExtra("id", Integer.parseInt(id));
			break;

		case 2:
			it = new Intent(context, HotelDetailActivity.class);
			it.putExtra("hotelId", id);
			break;

		case 3:
			it = new Intent(context, ShopDetailActivity.class);
			it.putExtra("shopId", id);
			break;

		case 4:
			it = new Intent(context, TuangouDetailActivity.class);
			it.putExtra("tuangouId", id);
			break;

		case 5:
			it = new Intent(context, GoodDetailActivity.class);
			it.putExtra("daigouId", id);
			break;

		default:
			break;
		}

		context.startActivity(it);

	}
}
