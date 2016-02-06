package com.means.rabbit.activity.my;

import com.means.rabbit.R;
import com.means.rabbit.R.layout;
import com.means.rabbit.base.RabbitBaseActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * 新增配送地址
 * @author dell
 *
 */
public class AddShippingAddressActivity extends RabbitBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_shipping_address);
	}

	@Override
	public void initView() {
		setTitle("配送地址");
	}
}
