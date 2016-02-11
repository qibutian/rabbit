package com.means.rabbit.activity.travel;

import net.duohuo.dhroid.adapter.NetJSONAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.means.rabbit.R;
import com.means.rabbit.api.API;
import com.means.rabbit.base.RabbitBaseActivity;
import com.means.rabbit.views.RefreshListViewAndMore;

public class TravelActivity extends RabbitBaseActivity {

	LayoutInflater mLayoutInflater;

	RefreshListViewAndMore listV;

	ListView contentListV;

	NetJSONAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_travel);
	}

	@Override
	public void initView() {
		setTitle("旅行小秘");
		listV = (RefreshListViewAndMore) findViewById(R.id.my_listview);
		contentListV = listV.getListView();
		adapter = new NetJSONAdapter(API.text, self, R.layout.item_travel_list);
		adapter.fromWhat("list");
		listV.setAdapter(adapter);
		contentListV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				Intent it = new Intent(self, TravelDetailActivity.class);
				startActivity(it);
			}
		});
	}

}
