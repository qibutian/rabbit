package com.means.rabbit.activity.my;

import net.duohuo.dhroid.ioc.IocContainer;
import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.means.rabbit.R;
import com.means.rabbit.activity.my.edit.ForgetPswdActivity;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;
import com.means.rabbit.utils.RabbitPerference;

public class LoginActivity extends RabbitBaseActivity implements
		OnClickListener {

	private EditText nicknameEt, passwordEt;
	private Button loginBtn;
	private TextView forgetpswdT;
	
	RabbitPerference per;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public void initView() {
		setTitle(getString(R.string.login));

		nicknameEt = (EditText) findViewById(R.id.nickname);
		passwordEt = (EditText) findViewById(R.id.password);
		loginBtn = (Button) findViewById(R.id.login);
		forgetpswdT = (TextView) findViewById(R.id.forgetpswd);

		loginBtn.setOnClickListener(this);
		forgetpswdT.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//登录
		case R.id.login:
			login();
			break;
		//忘记密码
		case R.id.forgetpswd:
			Intent it = new Intent(self,ForgetPswdActivity.class);
			startActivity(it);
			break;

		}
	}
	
	private void login() {
//		 final String nickname = nicknameEt.getText().toString();
//		 final String password = passwordEt.getText().toString();

		final String nickname = "qqq";
		final String password = "q111111";
		if (TextUtils.isEmpty(nickname)) {
			showToast("请输入昵称/用户名");
			return;
		}
		if (TextUtils.isEmpty(password)) {
			showToast("请输入密码");
			return;
		}
		if (password.length() < 6 || password.length() > 15) {
			showToast("密码为6-15位字母或数字的组合");
			return;
		}
		DhNet smsNet = new DhNet(API.login);
		smsNet.addParam("pswd", password);
		smsNet.addParam("name", nickname);

		smsNet.doPostInDialog(new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {
				// TODO Auto-generated method stub
				if (response.isSuccess()) {
					JSONObject jo = response.jSONFromData();
					per = IocContainer.getShare().get(RabbitPerference.class);
					per.load();
					per.setLogin(true);
					per.setName(JSONUtil.getString(jo, "name"));
					per.setNickname(JSONUtil.getString(jo, "nickname"));
					per.setPhone(JSONUtil.getString(jo, "phone"));
					per.setSex(JSONUtil.getString(jo, "sex"));
					per.setFaceimg_s(JSONUtil.getString(jo, "faceimg_s"));
					per.setMsgcount(JSONUtil.getString(jo, "msgcount"));
					per.setOrdercount(JSONUtil.getString(jo, "ordercount"));
					per.setGroupname(JSONUtil.getString(jo, "groupname"));
					per.setPassword(password);
					per.commit();
					finish();
				}
			}
		});
	}

}
