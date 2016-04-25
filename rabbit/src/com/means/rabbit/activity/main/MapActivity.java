package com.means.rabbit.activity.main;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.means.rabbit.R;
import com.means.rabbit.base.RabbitBaseActivity;

public class MapActivity extends RabbitBaseActivity {

	WebView webV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
	}

	@Override
	public void initView() {

		setTitle("地图");
		webV = (WebView) findViewById(R.id.content);
		WebSettings webSettings = webV.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webV.loadUrl(getIntent().getStringExtra("url"));
	}

}
