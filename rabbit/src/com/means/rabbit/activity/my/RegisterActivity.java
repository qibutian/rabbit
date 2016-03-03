package com.means.rabbit.activity.my;

import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.means.rabbit.R;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;
import com.means.rabbit.utils.RabbitUtils;

/**
 * 
 * 注册
 * @author Administrator
 *
 */
public class RegisterActivity extends RabbitBaseActivity implements OnClickListener{
	
	private EditText phoneEt,verificationEt,nicknameEt,passwordEt;
	private TextView getverificationBtn;
	private Button registerBtn;
	
	private TimeCount time = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	}

	@Override
	public void initView() {
		setTitle(getString(R.string.register));
		time = new TimeCount(60000, 1000);
		
		phoneEt = (EditText) findViewById(R.id.phone);
		verificationEt = (EditText) findViewById(R.id.verification);
		nicknameEt = (EditText) findViewById(R.id.nickname);
		passwordEt = (EditText) findViewById(R.id.password);
		getverificationBtn = (TextView) findViewById(R.id.getverification);
		registerBtn = (Button) findViewById(R.id.register);
		
		getverificationBtn.setOnClickListener(this);
		registerBtn.setOnClickListener(this);

	}
	
	private void register() {
		String tel = phoneEt.getText().toString();
		String password = passwordEt.getText().toString();
		String code = verificationEt.getText().toString();
		String nickname = nicknameEt.getText().toString();
		if (TextUtils.isEmpty(tel)) {
			showToast("请输入手机号");
			return;
		}
		if (tel.length() != 11) {
			showToast("手机号格式不正确");
			return;
		}
		if (TextUtils.isEmpty(code)) {
			showToast("请输入验证码");
			return;
		}
		if (TextUtils.isEmpty(password)) {
			showToast("请输入密码");
			return;
		}
		if (password.length() < 6 || password.length() > 15) {
			showToast("密码为6-15位字母和数字");
			return;
		}
		if (!RabbitUtils.isLetter(password)) {
			showToast("密码中必须包含字母");
			return;
		}
		if (TextUtils.isEmpty(nickname)) {
			showToast("请输入用户名");
			return;
		}
		if (nickname.length()>8) {
			showToast("请输入8位以内用户名");
			return;
		}

		DhNet smsNet = new DhNet(API.register);
		smsNet.addParam("name", nickname);
		smsNet.addParam("pswd", password);
		smsNet.addParam("phone", tel);
		smsNet.addParam("mobilecode", code);
		smsNet.doPostInDialog(new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {
				// TODO Auto-generated method stub
				if (response.isSuccess()) {
					showToast("注册成功");
					finish();
				}
			}
		});

	}

	// 获取验证码
	private void getMobileCode() {
		String tel = phoneEt.getText().toString();
		if (TextUtils.isEmpty(tel)) {
			showToast("请输入手机号");
			return;
		}
		if (tel.length() != 11) {
			showToast("手机号格式不正确");
			return;
		}
		DhNet smsNet = new DhNet(API.mobilecode);
		smsNet.addParam("phone", tel);
		smsNet.addParam("type", "1"); // 1为注册
		smsNet.doGet(new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {
				// TODO Auto-generated method stub
				if (response.isSuccess()) {
					time.start();
				}
			}
		});
	}

	class TimeCount extends CountDownTimer {
		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			getverificationBtn.setText("重新发送");
			getverificationBtn.setEnabled(true);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			getverificationBtn.setEnabled(false);
			getverificationBtn.setText(millisUntilFinished / 1000 + "秒");

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.getverification:
			getMobileCode();
			break;
		case R.id.register:
			register();
			break;
		}
	}

}
