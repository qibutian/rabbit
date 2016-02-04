package com.means.rabbit.activity.more;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class MoreFragment extends Fragment implements OnClickListener {
	static MoreFragment instance;

	View mainV;

	LayoutInflater mLayoutInflater;

	public static MoreFragment getInstance() {
		if (instance == null) {
			instance = new MoreFragment();
		}

		return instance;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// mainV = inflater.inflate(R.layout.fragment_home_page, null);
		mLayoutInflater = inflater;
		initView();
		// TODO Auto-generated method stub
		return mainV;
	}

	private void initView() {
	}

	@Override
	public void onClick(View v) {
	}
}