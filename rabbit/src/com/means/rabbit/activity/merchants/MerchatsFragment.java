package com.means.rabbit.activity.merchants;

import org.json.JSONObject;

import net.duohuo.dhroid.adapter.FieldMap;
import net.duohuo.dhroid.adapter.NetJSONAdapter;
import net.duohuo.dhroid.net.JSONUtil;

import com.means.rabbit.R;
import com.means.rabbit.activity.home.HomePageFragment;
import com.means.rabbit.api.API;
import com.means.rabbit.views.RefreshListViewAndMore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MerchatsFragment extends Fragment {

	static MerchatsFragment instance;

	View mainV;

	LayoutInflater mLayoutInflater;

	View headV;

	RefreshListViewAndMore listV;

	ListView contentListV;

	NetJSONAdapter adapter;

	public static MerchatsFragment getInstance() {
		if (instance == null) {
			instance = new MerchatsFragment();
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
		mLayoutInflater = LayoutInflater.from(getActivity());
		headV = mLayoutInflater.inflate(R.layout.head_food_list, null);
		listV.addHeadView(headV);
		contentListV = listV.getListView();
		adapter = new NetJSONAdapter(API.text, getActivity(),
				R.layout.item_food_list);
		adapter.fromWhat("list");
		adapter.addField("title", R.id.title);
		adapter.addField("price", R.id.price);
		adapter.addField("pic", R.id.pic);
		adapter.addField("tuangoudes", R.id.des);
		adapter.addField("tuidingdes", R.id.order_des);
		adapter.addField("areaname", R.id.area);
		adapter.addField(new FieldMap("price", R.id.price) {

			@Override
			public Object fix(View itemV, Integer position, Object o, Object jo) {
				JSONObject data = (JSONObject) jo;
				TextView comment_desT = (TextView) itemV
						.findViewById(R.id.comment_des);
				comment_desT.setText("评分   "
						+ JSONUtil.getString(data, "score") + "/"
						+ JSONUtil.getString(data, "views"));
				// TODO Auto-generated method stub
				return o;
			}
		});
		listV.setAdapter(adapter);
		contentListV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				Intent it = new Intent(getActivity(), ShopDetailActivity.class);
				startActivity(it);

			}
		});
	}

}
