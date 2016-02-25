package com.means.rabbit.activity.home;

import com.means.rabbit.R;
import com.means.rabbit.R.layout;
import com.means.rabbit.base.RabbitBaseActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * 全部分类
 * 
 * @author Administrator
 * 
 */
public class AllItemActivity extends RabbitBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_item);
	}

	@Override
	public void initView() {
		setTitle(getString(R.string.all_item));
	}
}
