package com.means.rabbit.activity.travel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

import com.means.rabbit.activity.my.MyIndexFragment;

public class TravelFragment extends Fragment implements OnClickListener {
	static TravelFragment instance;

	View mainV;

	LayoutInflater mLayoutInflater;

	public static TravelFragment getInstance() {
		if (instance == null) {
			instance = new TravelFragment();
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