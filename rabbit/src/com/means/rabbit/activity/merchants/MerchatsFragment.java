package com.means.rabbit.activity.merchants;

import com.means.rabbit.activity.home.HomePageFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MerchatsFragment extends Fragment {

	static MerchatsFragment instance;

	View mainV;

	LayoutInflater mLayoutInflater;

	public static MerchatsFragment getInstance() {
		if (instance == null) {
			instance = new MerchatsFragment();
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

}
