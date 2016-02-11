package com.means.rabbit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.means.rabbit.R;

public class CountryAdapter extends BaseAdapter{
	Context mContex;
	LayoutInflater inflater;
	public CountryAdapter (Context mContex){
		this.mContex = mContex;
		inflater = LayoutInflater.from(mContex);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		convertView = inflater.inflate(R.layout.item_country_list, null);
		// TODO Auto-generated method stub
		return convertView;
	}

}
