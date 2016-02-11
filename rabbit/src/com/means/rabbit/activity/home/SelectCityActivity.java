package com.means.rabbit.activity.home;

import android.os.Bundle;
import android.widget.ListView;

import com.means.rabbit.R;
import com.means.rabbit.adapter.CityAdapter;
import com.means.rabbit.adapter.CountryAdapter;
import com.means.rabbit.base.RabbitBaseActivity;

public class SelectCityActivity extends RabbitBaseActivity {

	ListView countrylist,citylist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_city);
	}

	@Override
	public void initView() {
		setTitle("选择城市");
		
		countrylist = (ListView) findViewById(R.id.countrylist);
		citylist = (ListView) findViewById(R.id.citylist);
		
		countrylist.setAdapter(new CountryAdapter(self));
		citylist.setAdapter(new CityAdapter(self));
		
	}
}
