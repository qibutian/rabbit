package com.means.rabbit.activity.order;

import com.means.rabbit.R;
import com.means.rabbit.R.layout;
import com.means.rabbit.base.RabbitBaseActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * 
 * 代购订单
 * @author Administrator
 *
 */
public class InsteadShoppingActivity extends RabbitBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instead_shopping);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setTitle("代购订单");
	}
}
