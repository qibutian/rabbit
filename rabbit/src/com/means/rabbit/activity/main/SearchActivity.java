package com.means.rabbit.activity.main;

import com.means.rabbit.R;
import com.means.rabbit.R.layout;
import com.means.rabbit.base.RabbitBaseActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * 
 * 搜索
 * @author Administrator
 *
 */
public class SearchActivity extends RabbitBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setTitle(getString(R.string.search));
	}
}