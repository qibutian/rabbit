package com.means.rabbit.views;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.means.rabbit.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class KeyVauleView extends LinearLayout {
	Context mContext;

	LayoutInflater mLayoutInflater;

	public KeyVauleView(Context context) {
		super(context);
	}

	public KeyVauleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOrientation(LinearLayout.VERTICAL);
		this.mContext = context;
	}

	public void setData() {
		mLayoutInflater = LayoutInflater.from(mContext);
		for (int i = 0; i < 3; i++) {

			if (i == 2) {
				View v = mLayoutInflater.inflate(R.layout.model_look_detail,
						null);
				this.addView(v);
			} else {
				View v = mLayoutInflater
						.inflate(R.layout.model_key_value, null);
				this.addView(v);
			}
		}

	}

}
