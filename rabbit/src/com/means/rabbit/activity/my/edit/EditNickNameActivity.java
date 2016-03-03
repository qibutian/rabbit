package com.means.rabbit.activity.my.edit;

import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.means.rabbit.R;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;

/**
 * 修改昵称
 * @author Administrator
 *
 */
public class EditNickNameActivity extends RabbitBaseActivity {
	
	EditText nicknameEt;
	Button saveBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_nick_name);
	}

	@Override
	public void initView() {
		setTitle(getString(R.string.editinfo));
		
		nicknameEt = (EditText) findViewById(R.id.nickname);
		saveBtn = (Button) findViewById(R.id.save);
		
		saveBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				editNickName();
			}
		});
	}
	
	public void editNickName(){
		String name = nicknameEt.getText().toString();
		if (TextUtils.isEmpty(name)) {
			showToast("昵称不能为空");
			return;
		}
		if (name.length()>14) {
			showToast("昵称位数不能超过14位");
			return;
		}
		
		DhNet net = new DhNet(API.editaction);
		net.addParam("nickname", name);
		net.doPostInDialog(new NetTask(self) {
			
			@Override
			public void doInUI(Response response, Integer transfer) {
				// TODO Auto-generated method stub
				if (response.isSuccess()) {
					showToast("修改成功");
				}
			}
		});
	}
}
