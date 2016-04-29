package com.means.rabbit.views.dialog;

import java.util.Locale;

import net.duohuo.dhroid.ioc.IocContainer;

import com.means.rabbit.R;
import com.means.rabbit.RabbitApplication;
import com.means.rabbit.utils.RabbitPerference;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

public class ChangLanguageDialog extends BaseAlertDialog {

	Context context;

	public ChangLanguageDialog(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_language);

		findViewById(R.id.bg).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
			}
		});

		findViewById(R.id.malaixiya).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						changeLanguage(3);
						dismiss();
					}
				});

		findViewById(R.id.english).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						changeLanguage(2);
						dismiss();
					}
				});

		findViewById(R.id.chinese).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						changeLanguage(1);
						dismiss();
					}
				});
	}

	private void changeLanguage(int type) {
		RabbitPerference per = IocContainer.getShare().get(
				RabbitPerference.class);
		per.load();
		Resources resources = context.getResources();// 获得res资源对象
		Configuration config = resources.getConfiguration();// 获得设置对象
		DisplayMetrics dm = resources.getDisplayMetrics();// 获得屏幕参数：主要是分辨率，像素等。
		if (type == 1) {
			config.locale = Locale.CHINA; // 简体中文
			RabbitApplication.getInstance().setBaseUrl(1);
		} else if (type == 2) {
			config.locale = Locale.ENGLISH; // 简体中文
			RabbitApplication.getInstance().setBaseUrl(2);
		} else if (type == 3) {
			config.locale = new Locale("my");
			RabbitApplication.getInstance().setBaseUrl(3);
		}

		resources.updateConfiguration(config, dm);
		per.langType = type;
		per.commit();
	}

}
