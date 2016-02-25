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
 * 酒店支付
 * @author Administrator
 *
 */
public class GrogshopPayActivity extends RabbitBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grogshop_pay);
	}

	@Override
	public void initView() {
		setTitle("酒店订单");
	}
}
