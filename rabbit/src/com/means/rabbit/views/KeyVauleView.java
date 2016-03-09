package com.means.rabbit.views;

import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.util.ViewUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.means.rabbit.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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

	public void setData(JSONArray jsa) {
		mLayoutInflater = LayoutInflater.from(mContext);
		for (int i = 0; i < jsa.length() + 1; i++) {
			if (i == jsa.length()) {
				View v = mLayoutInflater.inflate(R.layout.model_look_detail,
						null);
				this.addView(v);
			} else {
				JSONObject jo = JSONUtil.getJSONObjectAt(jsa, i);
				View v = mLayoutInflater
						.inflate(R.layout.model_key_value, null);

				ViewUtil.bindView(v.findViewById(R.id.key),
						JSONUtil.getString(jo, "label"));
				ViewUtil.bindView(v.findViewById(R.id.value),
						JSONUtil.getString(jo, "value"));
				this.addView(v);
			}
		}

	}

}
