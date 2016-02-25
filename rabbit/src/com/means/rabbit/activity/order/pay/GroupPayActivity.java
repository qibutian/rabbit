package com.means.rabbit.activity.order.pay;

import com.means.rabbit.R;
import com.means.rabbit.R.layout;
import com.means.rabbit.base.RabbitBaseActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * 团购支付
 * @author Administrator
 *
 */
public class GroupPayActivity extends RabbitBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_pay);
	}

	@Override
	public void initView() {
		setTitle("团购订单");
	}
}
