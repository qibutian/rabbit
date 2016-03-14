package com.means.rabbit.activity.order.pay;

import com.means.rabbit.R;
import com.means.rabbit.R.layout;
import com.means.rabbit.base.RabbitBaseActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * 
 * 代购支付
 * @author Administrator
 *
 */
public class InsteadShoppingPayActivity extends RabbitBaseActivity {
	String daigouId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_instead_shopping_pay);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setTitle("代购订单");
		daigouId = getIntent().getStringExtra("orderid");
	}
}
