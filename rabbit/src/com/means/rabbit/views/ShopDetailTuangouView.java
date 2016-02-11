package com.means.rabbit.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.means.rabbit.R;

public class ShopDetailTuangouView extends LinearLayout {
	Context mContext;

	LayoutInflater mLayoutInflater;

	public ShopDetailTuangouView(Context context) {
		super(context);
	}

	public ShopDetailTuangouView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOrientation(LinearLayout.VERTICAL);
		this.mContext = context;
	}

	public void setData() {
		mLayoutInflater = LayoutInflater.from(mContext);
		for (int i = 0; i < 3; i++) {

			if (i == 2) {
				View v = mLayoutInflater.inflate(R.layout.model_more,
						null);
				this.addView(v);
			} else {
				View v = mLayoutInflater
						.inflate(R.layout.item_shop_detail_tuangou, null);
				this.addView(v);
			}
		}

	}

}

