package com.means.rabbit.views;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.AdapterView.OnItemClickListener;

import com.means.rabbit.R;
import com.means.rabbit.adapter.CatLeftAdapter;
import com.means.rabbit.adapter.CatRightAdapter;

public class SortPop {
	Context context;

	View contentV;

	PopupWindow pop;

	public SortPop(Context context) {
		this.context = context;
		contentV = LayoutInflater.from(context)
				.inflate(R.layout.pop_sort, null);
		pop = new PopupWindow(contentV, ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.FILL_PARENT, true);
		// 需要设置一下此参数，点击外边可消失
		pop.setBackgroundDrawable(new BitmapDrawable());
		// 设置点击窗口外边窗口消失
		pop.setOutsideTouchable(true);
		// 设置此参数获得焦点，否则无法点击
		pop.setFocusable(true);
//		pop.setAnimationStyle(R.style.mystyle);
		pop.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		initView();

	}

	private void initView() {
		contentV.findViewById(R.id.bg).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						pop.dismiss();
					}
				});
	}

	public void show(View v) {
		pop.showAsDropDown(v);
	}

}
