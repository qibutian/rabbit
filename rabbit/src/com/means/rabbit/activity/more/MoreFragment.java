package com.means.rabbit.activity.more;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.means.rabbit.R;
import com.means.rabbit.activity.my.AboutUsActivity;

/**
 * 更多
 * @author Administrator
 *
 */
public class MoreFragment extends Fragment implements OnClickListener {
	static MoreFragment instance;

	View mainV;

	LayoutInflater mLayoutInflater;
	
	View aboutV,langswitcherV,feedbackV,richscanV,versionV,helpV,wipe_cacheV;

	public static MoreFragment getInstance() {
		if (instance == null) {
			instance = new MoreFragment();
		}

		return instance;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 mainV = inflater.inflate(R.layout.fragment_more, null);
		mLayoutInflater = inflater;
		initView();
		// TODO Auto-generated method stub
		return mainV;
	}

	private void initView() {
		
		aboutV = mainV.findViewById(R.id.about);
		langswitcherV = mainV.findViewById(R.id.langswitcher);
		feedbackV = mainV.findViewById(R.id.feedback);
		richscanV = mainV.findViewById(R.id.richscan);
		versionV = mainV.findViewById(R.id.version);
		helpV = mainV.findViewById(R.id.help);
		wipe_cacheV = mainV.findViewById(R.id.wipe_cache);
		
		aboutV.setOnClickListener(this);
		langswitcherV.setOnClickListener(this);
		feedbackV.setOnClickListener(this);
		richscanV.setOnClickListener(this);
		versionV.setOnClickListener(this);
		helpV.setOnClickListener(this);
		wipe_cacheV.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent it;
		switch (v.getId()) {
		//关于我们
		case R.id.about:
			it = new Intent(getActivity(),AboutUsActivity.class);
			startActivity(it);
			break;
			//语言选择
		case R.id.langswitcher:
			
			break;
			//反馈
		case R.id.feedback:
			
			break;
			//扫一扫
		case R.id.richscan:
			
			break;
			//版本更新
		case R.id.version:
			
			break;
			//帮助
		case R.id.help:
			
			break;
			//清楚缓存
		case R.id.wipe_cache:
			
			break;

		default:
			break;
		}
	}
}
