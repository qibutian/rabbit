package com.means.rabbit.activity.merchants;

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
import com.means.rabbit.views.TabView;

public class HotelListActivity extends RabbitBaseActivity {

	LayoutInflater mLayoutInflater;

	RefreshListViewAndMore listV;

	ListView contentListV;

	NetJSONAdapter adapter;
	
	TabView tabV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hotel_list);
	}

	@Override
	public void initView() {
		setTitle("酒店");
		listV = (RefreshListViewAndMore) findViewById(R.id.my_listview);
		contentListV = listV.getListView();
		adapter = new NetJSONAdapter(API.hotelList, self, R.layout.item_hotel_list);
		adapter.fromWhat("list");
		listV.setAdapter(adapter);
		contentListV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				Intent it = new Intent(self, HotelDetailActivity.class);
				startActivity(it);

			}
		});
		
	}

}
