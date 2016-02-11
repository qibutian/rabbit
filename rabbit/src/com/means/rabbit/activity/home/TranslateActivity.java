package com.means.rabbit.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.means.rabbit.R;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_translate);
	}

	@Override
	public void initView() {
		setTitle("文件助手");
		my_titlebarV = findViewById(R.id.my_titlebar);
		// TODO Auto-generated method stub
		languageLl = (LinearLayout) findViewById(R.id.language);

		languageLl.setOnClickListener(this);

		voiceI = (ImageView) findViewById(R.id.voice);
		voiceI.setOnClickListener(this);

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

}
