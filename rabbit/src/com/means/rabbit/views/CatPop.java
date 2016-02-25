package com.means.rabbit.views;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.means.rabbit.R;
import com.means.rabbit.adapter.CatLeftAdapter;
import com.means.rabbit.adapter.CatRightAdapter;

public class CatPop {

	Context context;

	View contentV;

	PopupWindow pop;

	ListView leftListV, rightListV;

	CatLeftAdapter leftAapter;

	CatRightAdapter rightAdapter;

	List<String> leftData, rightData;

	public CatPop(Context context) {
		this.context = context;
		contentV = LayoutInflater.from(context).inflate(R.layout.pop_cat, null);
		pop = new PopupWindow(contentV, ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.FILL_PARENT, true);
		// 需要设置一下此参数，点击外边可消失
		pop.setBackgroundDrawable(new BitmapDrawable());
		// 设置点击窗口外边窗口消失
		pop.setOutsideTouchable(true);
		// 设置此参数获得焦点，否则无法点击
		pop.setFocusable(true);
		// pop.setAnimationStyle(R.style.mystyle);
		pop.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		initView();

	}

	private void initView() {
		leftListV = (ListView) contentV.findViewById(R.id.lrft_list);
		rightListV = (ListView) contentV.findViewById(R.id.right_list);

		leftAapter = new CatLeftAdapter(context);
		leftListV.setAdapter(leftAapter);

		rightAdapter = new CatRightAdapter(context);
		rightListV.setAdapter(rightAdapter);

		leftData = new ArrayList<String>();
		leftData.add("美食");
		leftData.add("酒店");
		leftData.add("货币兑换");
		leftData.add("出行服务");
		leftData.add("旅行小秘");
		leftData.add("休闲娱乐");
		leftData.add("专享特色");
		leftData.add("随身翻译");
		leftData.add("紧急求助");
		leftAapter.setData(leftData);

		rightData = new ArrayList<String>();
		rightData.add("全部");

		leftListV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				leftAapter.setCurrentPosition(position);
				rightAdapter.setData(rightData);
			}
		});

		contentV.findViewById(R.id.bg).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						System.out.println("wwwwwww");
						pop.dismiss();
					}
				});
	}

	public void show(View v) {
		pop.showAsDropDown(v);
	}

}
