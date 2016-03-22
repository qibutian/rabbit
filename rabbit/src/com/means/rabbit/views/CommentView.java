package com.means.rabbit.views;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.util.ViewUtil;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.means.rabbit.R;

public class CommentView extends LinearLayout {
	Context mContext;

	LayoutInflater mLayoutInflater;

	public CommentView(Context context) {
		super(context);
	}

	public CommentView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOrientation(LinearLayout.VERTICAL);
		this.mContext = context;
	}

	public void setData(JSONArray jsa) {
		mLayoutInflater = LayoutInflater.from(mContext);
		for (int i = 0; i < jsa.length(); i++) {

			JSONObject jo = JSONUtil.getJSONObjectAt(jsa, i);
			View v = mLayoutInflater.inflate(R.layout.item_comment, null);

			ViewUtil.bindView(v.findViewById(R.id.content),
					JSONUtil.getString(jo, "content"));
			ViewUtil.bindView(v.findViewById(R.id.name),
					JSONUtil.getString(jo, "username"));
			ViewUtil.bindView(v.findViewById(R.id.time),
					JSONUtil.getString(jo, "dateline"), "neartime");
			ViewUtil.bindNetImage((RoundImageView) v.findViewById(R.id.head),
					JSONUtil.getString(jo, "faceimg_s"), "head");

			JSONArray picJsa = JSONUtil.getJSONArray(jo, "image_data");

			if (picJsa != null && picJsa.length() != 0) {
				LinearLayout picV = (LinearLayout) v
						.findViewById(R.id.pic_layout);
				for (int j = 0; j < picJsa.length(); j++) {

					if (j < 4) {
						ImageView picI = (ImageView) picV.getChildAt(j);
						picI.setVisibility(View.VISIBLE);
						try {
							ViewUtil.bindNetImage(picI, picJsa.getJSONObject(j).getString("img_m "),
									"head");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			}

			// ViewUtil.bindView(v.findViewById(R.id.content),
			// JSONUtil.getString(jo, "content"));

			this.addView(v);
		}

		if (jsa.length() >= 2) {
			View v = mLayoutInflater.inflate(R.layout.model_comment_text, null);
			this.addView(v);
		}

	}
}
