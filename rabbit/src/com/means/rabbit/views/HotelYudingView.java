package com.means.rabbit.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.means.rabbit.R;

public class HotelYudingView extends LinearLayout {
	Context mContext;

	LayoutInflater mLayoutInflater;

	public HotelYudingView(Context context) {
		super(context);
	}

	public HotelYudingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOrientation(LinearLayout.VERTICAL);
		this.mContext = context;
	}

	public void setData() {
		mLayoutInflater = LayoutInflater.from(mContext);
		for (int i = 0; i < 3; i++) {

			View v = mLayoutInflater.inflate(R.layout.item_hotel_yuding, null);
			this.addView(v);
		}

	}

}
