package com.means.rabbit.activity.my.edit;

import com.means.rabbit.R;
import com.means.rabbit.R.layout;
import com.means.rabbit.base.RabbitBaseActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * 编辑资料
 * @author dell
 *
 */
public class EditInfoActivity extends RabbitBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_info);
	}

	@Override
	public void initView() {
		// TODO Auto-generated method stub
		setTitle(getString(R.string.editinfo));
	}
}
