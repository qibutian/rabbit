package com.means.rabbit.activity.my.order;

import com.means.rabbit.R;
import com.means.rabbit.R.layout;
import com.means.rabbit.base.RabbitBaseActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * 商家订单详情
 * @author dell
 *
 */
public class BusinessOrderDetailsActivity extends RabbitBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_business_order_details);
	}

	@Override
	public void initView() {
		setTitle(getString(R.string.business_order_details));
	}
}
