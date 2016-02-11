package com.means.rabbit.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
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

	public void setData() {
		mLayoutInflater = LayoutInflater.from(mContext);
		for (int i = 0; i < 3; i++) {

			if (i == 2) {
				View v = mLayoutInflater.inflate(R.layout.model_comment_text,
						null);
				this.addView(v);
			} else {
				View v = mLayoutInflater.inflate(R.layout.item_comment, null);
				this.addView(v);
			}
		}

	}

}
