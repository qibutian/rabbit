package com.means.rabbit.activity.travel;

import android.os.Bundle;

import com.means.rabbit.R;
import com.means.rabbit.base.RabbitBaseActivity;

public class TravelDetailActivity extends RabbitBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_travel_detail);
	}

	@Override
	public void initView() {
		setTitle("旅游详情");
	}

}
