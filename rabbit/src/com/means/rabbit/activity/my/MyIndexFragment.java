package com.means.rabbit.activity.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.means.rabbit.R;

public class MyIndexFragment extends Fragment implements OnClickListener {
	static MyIndexFragment instance;

	View mainV;

	LayoutInflater mLayoutInflater;
	
	LinearLayout editinfoLl,systemmsgLl;

	public static MyIndexFragment getInstance() {
		if (instance == null) {
			instance = new MyIndexFragment();
		}

		return instance;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 mainV = inflater.inflate(R.layout.fragment_my, null);
		mLayoutInflater = inflater;
		initView();
		// TODO Auto-generated method stub
		return mainV;
	}

	private void initView() {
		editinfoLl = (LinearLayout) mainV.findViewById(R.id.editinfo);
		systemmsgLl = (LinearLayout) mainV.findViewById(R.id.systemmsg);
		
		editinfoLl.setOnClickListener(this);
		systemmsgLl.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent it;
		switch (v.getId()) {
		//编辑资料
		case R.id.editinfo:
			it = new Intent(getActivity(),EditInfoActivity.class);
			startActivity(it);
			break;
		//系统消息
		case R.id.systemmsg:
			it = new Intent(getActivity(),SystemMsgActivity.class);
			startActivity(it);
			break;

		default:
			break;
		}
	}
}

