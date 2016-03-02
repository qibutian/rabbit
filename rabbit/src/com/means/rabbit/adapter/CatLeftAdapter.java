package com.means.rabbit.adapter;

import java.util.List;

import net.duohuo.dhroid.util.ViewUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.means.rabbit.R;

public class CatLeftAdapter extends BaseAdapter {
	Context mContex;
	LayoutInflater inflater;

	List<String> list;

	int currentPosition = -1;

	public CatLeftAdapter(Context mContex) {
		this.mContex = mContex;
		inflater = LayoutInflater.from(mContex);
	}

	public void setData(List<String> list) {
		this.list = list;
		notifyDataSetChanged();

	}

	@Override
	public int getCount() {
		if (list == null) {
			return 0;
		}
		return list.size();
	}

	public void setCurrentPosition(int position) {
		this.currentPosition = position;
		notifyDataSetChanged();
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

		ViewUtil.bindView(convertView.findViewById(R.id.name),
				list.get(position));

		if (currentPosition == position) {
			convertView.setBackgroundColor(mContex.getResources().getColor(
					R.color.white));
		} else {
			convertView.setBackgroundColor(mContex.getResources().getColor(
					R.color.app_bg_color));
		}
		// TODO Auto-generated method stub
		return convertView;
	}

}