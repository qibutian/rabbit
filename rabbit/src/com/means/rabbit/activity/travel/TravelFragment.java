package com.means.rabbit.activity.travel;

import net.duohuo.dhroid.adapter.NetJSONAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.means.rabbit.R;
import com.means.rabbit.activity.my.MyIndexFragment;
import com.means.rabbit.api.API;
import com.means.rabbit.views.RefreshListViewAndMore;

public class TravelFragment extends Fragment implements OnClickListener {
	static TravelFragment instance;

	View mainV;

	LayoutInflater mLayoutInflater;

	RefreshListViewAndMore listV;

	ListView contentListV;

	NetJSONAdapter adapter;

	public static TravelFragment getInstance() {
		if (instance == null) {
			instance = new TravelFragment();
		}

		return instance;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mainV = inflater.inflate(R.layout.fragment_travel, null);
		mLayoutInflater = inflater;
		initView();
		// TODO Auto-generated method stub
		return mainV;
	}

	private void initView() {
		listV = (RefreshListViewAndMore) mainV.findViewById(R.id.my_listview);
		contentListV = listV.getListView();
		adapter = new NetJSONAdapter(API.text, getActivity(),
				R.layout.item_travel_list);
		adapter.fromWhat("list");
		listV.setAdapter(adapter);
		contentListV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

			}
		});
	}

	@Override
	public void onClick(View v) {
	}
}
