package com.means.rabbit.activity.my;

import android.os.Bundle;

import com.means.rabbit.R;
import com.means.rabbit.base.RabbitBaseActivity;

public class RegisterActivity extends RabbitBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	}

	@Override
	public void initView() {
		setTitle(getString(R.string.register));

	}

}
