package com.means.rabbit.views;

import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.util.ViewUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
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

	public void setData(JSONArray jsa) {
		mLayoutInflater = LayoutInflater.from(mContext);
		if (jsa != null) {
			for (int i = 0; i < jsa.length(); i++) {
				JSONObject jo = JSONUtil.getJSONObjectAt(jsa, i);
				View v = mLayoutInflater.inflate(R.layout.item_hotel_yuding,
						null);
				ViewUtil.bindView(v.findViewById(R.id.title),
						JSONUtil.getString(jo, "name"));
				ViewUtil.bindView(v.findViewById(R.id.price),
						JSONUtil.getString(jo, "price"));
				ViewUtil.bindNetImage((ImageView) v.findViewById(R.id.pic),
						JSONUtil.getString(jo, "pic"), "default");
				ViewUtil.bindView(v.findViewById(R.id.des),
						JSONUtil.getString(jo, "des"));
//				ViewUtil.bindView(v.findViewById(R.id.title),
//						JSONUtil.getString(jo, "name"));
				this.addView(v);
			}
		}

	}

}
