package com.means.rabbit.activity.finance;

import com.means.rabbit.R;
import com.means.rabbit.R.layout;
import com.means.rabbit.base.RabbitBaseActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
/**
 * 
 * 财务管理
 * @author Administrator
 *
 */
public class FinancialManagementActivity extends RabbitBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_financial_management);
	}

	@Override
	public void initView() {
		setTitle(getString(R.string.financial_management));
	}
}
