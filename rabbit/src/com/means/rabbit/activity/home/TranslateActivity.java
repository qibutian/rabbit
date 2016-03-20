package com.means.rabbit.activity.home;

import org.json.JSONObject;

import net.duohuo.dhroid.net.DhNet;
import net.duohuo.dhroid.net.JSONUtil;
import net.duohuo.dhroid.net.NetTask;
import net.duohuo.dhroid.net.Response;
import net.duohuo.dhroid.util.ViewUtil;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.means.rabbit.R;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;
import com.means.rabbit.views.TranslatePop;

/**
 * 翻译助手
 * 
 * @author dell
 * 
 */
public class TranslateActivity extends RabbitBaseActivity implements
		OnClickListener {

	LinearLayout languageLl;

	ImageView voiceI;

	View my_titlebarV;

	TranslatePop pop;

	String slang = "en";

	EditText contentE;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_translate);
	}

	@Override
	public void initView() {
		setTitle(getString(R.string.translate));
		my_titlebarV = findViewById(R.id.my_titlebar);
		// TODO Auto-generated method stub
		languageLl = (LinearLayout) findViewById(R.id.language);

		languageLl.setOnClickListener(this);

		voiceI = (ImageView) findViewById(R.id.voice);
		voiceI.setOnClickListener(this);

		contentE = (EditText) findViewById(R.id.content);

		findViewById(R.id.translate).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				translate();
			}
		});

	}

	@Override
	public void onClick(View v) {
		Intent it;
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.language:
			it = new Intent(self, SelectLanguageActivity.class);
			startActivity(it);
			break;

		case R.id.voice:
			pop = new TranslatePop(self);
			pop.show(my_titlebarV);
			break;

		default:
			break;
		}
	}

	private void translate() {
		if (TextUtils.isEmpty(contentE.getText().toString())) {
			showToast("请输入翻译内容!");
			return;
		}

		DhNet net = new DhNet(API.translation);
		net.addParam("slang", slang);
		net.addParam("q", contentE.getText().toString());
		net.doGetInDialog("翻译中...", new NetTask(self) {

			@Override
			public void doInUI(Response response, Integer transfer) {

				if (response.isSuccess()) {
					JSONObject jo = response.jSON();
					ViewUtil.bindView(findViewById(R.id.result),
							JSONUtil.getString(jo, "data"));

				}

			}
		});
	}

}
